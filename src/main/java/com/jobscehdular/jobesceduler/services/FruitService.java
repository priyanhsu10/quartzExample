package com.jobscehdular.jobesceduler.services;

import com.jobscehdular.jobesceduler.Entities.Fruit;
import com.jobscehdular.jobesceduler.jobs.TestJob;
import com.jobscehdular.jobesceduler.jobs.TestJobData;
import com.jobscehdular.jobesceduler.repositories.FruitRepository;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class FruitService {
    private final FruitRepository fruitRepository;
    private final Scheduler scheduler;

    public FruitService(FruitRepository fruitRepository, Scheduler scheduler) {
        this.fruitRepository = fruitRepository;
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void postConstruct() throws SchedulerException {
        scheduler.start();
    }

    @PreDestroy
    public void preDestroy() throws SchedulerException {
        scheduler.shutdown();
    }

    public Fruit save(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public List<Fruit> getAll() {
        return fruitRepository.findAll();
    }

    public void deleteFruit(int fruitId) {
        Optional<Fruit> fruit = fruitRepository.findById(fruitId);
        if (fruit.isPresent()) {

            fruitRepository.deleteById(fruitId);
        }
    }

    public long countByFruitName(String name) {
        return fruitRepository.countByName(name);
    }

    public void schedule(TestJobData testJobData) {
//        String jobName = testJobData.getJobName();
//        String jobGroup = testJobData.getJobGroup();
//        ZonedDateTime zonedDateTime = ZonedDateTime.of(testJobData.getStartTime(), ZoneId.of("Asia/Kolkata"));
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.put("test", "this is test job");
//        JobDetail detail = JobBuilder.newJob(TestJob.class).withIdentity(jobName, jobGroup)
//                .usingJobData(jobDataMap).build();
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity(jobName, jobGroup)
//                .startAt(Date.from(ZonedDateTime.now().toInstant()))
//                .forJob(detail)
//                .withSchedule(SimpleScheduleBuilder
//                        .simpleSchedule()
//                        .withIntervalInMinutes(testJobData.getGapDuration()).withRepeatCount(testJobData.getCounter()))
//                .build();
//
//        try {
//            scheduler.scheduleJob(trigger);
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }
        scheduluJob();
    }
    private   void  scheduluJob( ){
        JobDataMap jobDataMap = new JobDataMap();
     jobDataMap.put("test", "this is test job");
        JobDetail job = newJob(TestJob.class)
                .withIdentity(UUID.randomUUID().toString(), "group1")
                .usingJobData(jobDataMap).build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        try {

            scheduler.scheduleJob(job, trigger);
        }catch (SchedulerException exception){
System.out.println(exception.getMessage());
        }
    }
}
