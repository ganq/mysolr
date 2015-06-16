package com.gq.solr.scheduler.job;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;


public class MyJobFactory implements JobFactory{

    private ApplicationContext applicationContext;

    public MyJobFactory(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public Job newJob(TriggerFiredBundle bundle) throws SchedulerException {
        return (Job)applicationContext.getBean(bundle.getJobDetail().getJobClass());
    }

}