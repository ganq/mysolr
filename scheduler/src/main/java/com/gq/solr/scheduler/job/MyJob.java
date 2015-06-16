package com.gq.solr.scheduler.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * Created by GanQuan on 2015/6/9 0009.
 */
public abstract class MyJob implements Job {

    //
    protected Logger logger = Logger.getLogger(this.getClass());

    protected String jobName;
    protected String cronExpression;
    protected Map<String, Object> params;
    //
    protected Lock lock = null;
    protected JobExecutionContext context;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.context = context;
        this.lock = (Lock)context.getJobDetail().getJobDataMap().get(SchedulerManager.LOCK_OBJECT_KEY);
        if (lock.tryLock()) {
            logger.info("start job**********" + lock);
            try {
                logger.info("================开始执行调度任务================" + context.getJobDetail().getName());
                // Start job task
                run();
                // End job task
                logger.info("================执行调度任务结束================" + context.getJobDetail().getName());
            } catch (Exception ex) {
                logger.error("Failed to excute job in scheduler, " + ex.toString(), ex);
            } finally {
                lock.unlock();
            }
        } else {
            logger.info("================定时调度任务正在运行，请不需要重复尝试================" + context.getJobDetail().getName());
        }
    }

    public abstract void run();

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCronExpression(){
        return cronExpression;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}