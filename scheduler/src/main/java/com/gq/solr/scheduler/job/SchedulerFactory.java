package com.gq.solr.scheduler.job;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;


public class SchedulerFactory implements ApplicationContextAware, InitializingBean, DisposableBean {

    private List<MyJob> jobs;

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setJobs(List<MyJob> jobs) {
        this.jobs = jobs;
    }

    public List<MyJob> getJobs() {
        return jobs;
    }

    public void afterPropertiesSet() throws Exception {
        if (jobs != null && jobs.size() > 0) {

            SchedulerManager.initManager(new MyJobFactory(applicationContext));

            JobInfoModel[] jobInfoModels = new JobInfoModel[jobs.size()];
            for (int i = 0; i < jobs.size(); i++) {
                MyJob job = jobs.get(i);
                jobInfoModels[i] = SchedulerManager.createJobInfoModel(job.getJobName(), job.getClass(), job.getCronExpression(), job.getParams());
            }
            SchedulerManager.createJobModels(jobInfoModels);
        }
    }

    public void destroy() throws Exception {
        SchedulerManager.destoryManager();
    }

}