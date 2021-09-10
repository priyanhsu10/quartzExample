package com.jobscehdular.jobesceduler.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext);
        JobDataMap jobDataMap= jobExecutionContext.getMergedJobDataMap();
        for (String key: jobDataMap.getKeys()){
            System.out.println(key);
        }
    }
}
