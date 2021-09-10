package com.jobscehdular.jobesceduler;

import com.jobscehdular.jobesceduler.jobs.TestJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
public class tesring {
    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();
            scheduluJob(scheduler);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
private  static void  scheduluJob( Scheduler scheduler){
    JobDetail job = newJob(TestJob.class)
            .withIdentity("job1", "group1")
            .build();

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

    }
}

}
