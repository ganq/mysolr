package com.gq.solr.scheduler.job;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import java.util.Iterator;
import java.util.Map;

public class JobInfoModel {

    public static String JOB_GROUP_NAME = "gq";

    private String jobName;
    private String jobGroup;
    private JobDetail jobDetail;
    private CronTrigger trigger;
    private Map<String, Object> params;

    public JobInfoModel(String jobName, JobDetail jobDetail,
                        CronTrigger trigger, Map<String, Object> params) {
        this(jobName, JOB_GROUP_NAME, jobDetail, trigger, params);
    }

    public JobInfoModel(String jobName, String jobGroup, JobDetail jobDetail,
                        CronTrigger trigger, Map<String, Object> params) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobDetail = jobDetail;
        this.trigger = trigger;
        this.params = params;

        if (params != null && !params.isEmpty()) {
            JobDataMap map = this.jobDetail.getJobDataMap();
            Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator();
            if (iter.hasNext()) {
                Map.Entry<String, Object> entry = iter.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @SuppressWarnings("unused")
    private JobInfoModel() {

    }

    public String getJobName() {
        return jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public CronTrigger getTrigger() {
        return trigger;
    }

    public Map<String, Object> getParams() {
        return params;
    }

}