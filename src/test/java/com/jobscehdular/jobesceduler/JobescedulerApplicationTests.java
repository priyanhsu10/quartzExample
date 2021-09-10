package com.jobscehdular.jobesceduler;

import com.jobscehdular.jobesceduler.jobs.TestJob;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import static org.quartz.JobBuilder.newJob;

@SpringBootTest
class JobescedulerApplicationTests {

	@Test
	void contextLoads() {

	}

}
