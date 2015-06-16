package com.gq.solr.scheduler.job;

import org.quartz.*;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;

import java.text.ParseException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.locks.ReentrantLock;


public final class SchedulerManager {

    public static String CREATE_SCHEDULER_ERR = "Failed to create scheduler!";
    public static String STOP_SCHEDULER_ERR = "Failed to stop scheduler!";
    public static String CREATE_JOB_ERR = "Failed to create job!";
    public static String STOP_JOB_ERR = "Failed to stop job!";

    public static final String LOCK_OBJECT_KEY = "__lockObj";

    private static Scheduler scheduler;
    private static Map<String, JobInfoModel> jobsMap = new Hashtable<String, JobInfoModel>();

    static void createJobModels(JobInfoModel... models) throws SchedulerException {
        if (models != null && models.length > 0) {

            for (JobInfoModel jobInfoModel : models) {
                if (jobInfoModel != null) {
                    if (jobInfoModel.getTrigger() == null || jobInfoModel.getJobDetail() == null) {
                        continue;
                    }
                    jobsMap.put(jobInfoModel.getJobName(), jobInfoModel);
                    scheduler.scheduleJob(jobInfoModel.getJobDetail(), jobInfoModel.getTrigger());
                }
            }

        }
    }

    static void deleteJobModels(JobInfoModel... models) throws SchedulerException {
        if (models != null && models.length > 0) {

            String jobName = null;
            for (JobInfoModel jobInfoModel : models) {
                if (jobInfoModel != null) {
                    jobName = jobInfoModel.getJobName();
                    if (jobsMap.containsKey(jobName)) {
                        jobsMap.remove(jobName);
                        scheduler.deleteJob(jobName, JobInfoModel.JOB_GROUP_NAME);
                    }
                }
            }

        }
    }

    static void updateJobModels(JobInfoModel... models) throws SchedulerException {
        if (models != null && models.length > 0) {
            String jobName = null;
            for (JobInfoModel jobInfoModel : models) {
                if (jobInfoModel != null) {
                    jobName = jobInfoModel.getJobName();
                    if (jobsMap.containsKey(jobName)) {
                        scheduler.deleteJob(jobName, JobInfoModel.JOB_GROUP_NAME);
                        jobsMap.remove(jobName);
                    }

                    if (jobInfoModel.getTrigger() == null || jobInfoModel.getJobDetail() == null) {
                        continue;
                    }
                    jobsMap.put(jobName, jobInfoModel);
                    scheduler.scheduleJob(jobInfoModel.getJobDetail(), jobInfoModel.getTrigger());

                }

            }

        }
    }

    static Scheduler getScheduler() {
        return scheduler;
    }

    static void initManager(JobFactory jobFactory) throws SchedulerException {

        SchedulerFactory sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.start();

    }

    static void destoryManager() throws SchedulerException {
        if (jobsMap != null && !jobsMap.isEmpty()) {
            Iterator<String> iter = jobsMap.keySet().iterator();
            if (iter.hasNext()) {
                jobsMap.remove(iter.next());
            }
        }
        if (scheduler != null) {
            scheduler.shutdown();

            scheduler = null;
        }
    }

    public static int getJobNumber() throws SchedulerException {

        String[] arr = scheduler
                .getTriggerNames(JobInfoModel.JOB_GROUP_NAME);
        if (arr == null) {
            return 0;
        }
        return arr.length;

    }

    @SuppressWarnings("rawtypes")
    public static JobInfoModel createJobInfoModel(String jobName, Class clazz, String cronExpression, Map<String, Object> params) throws SchedulerException, ParseException {
        JobDetail jobDetail = new JobDetail(jobName, JobInfoModel.JOB_GROUP_NAME, clazz);
        jobDetail.getJobDataMap().put(SchedulerManager.LOCK_OBJECT_KEY, new ReentrantLock());

        CronTrigger trigger = new CronTrigger(jobName, JobInfoModel.JOB_GROUP_NAME);
        //设置使用服务器时区
        trigger.setTimeZone(TimeZone.getDefault());
        trigger.setCronExpression(cronExpression);
        return new JobInfoModel(jobName, jobDetail, trigger, params);

    }

}