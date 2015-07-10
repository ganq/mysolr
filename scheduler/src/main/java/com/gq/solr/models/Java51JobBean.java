package com.gq.solr.models;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * Created by GanQuan on 2015/6/10 0010.
 */
public class Java51JobBean extends SolrBean{


    @Field
    private String title;

    @Field
    private String company;

    @Field
    private String location;

    @Field
    private Date updateDate;

    @Field
    private String edu;

    @Field
    private String workingExp;

    @Field
    private String companyNature;

    @Field
    private String companySize;

    @Field
    private String desc;

    @Field
    private String jobUrl;

    @Field
    private String companyUrl;

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getWorkingExp() {
        return workingExp;
    }

    public void setWorkingExp(String workingExp) {
        this.workingExp = workingExp;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Java51JobBean{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", edu='" + edu + '\'' +
                ", workingExp='" + workingExp + '\'' +
                ", companyNature='" + companyNature + '\'' +
                ", companySize='" + companySize + '\'' +
                ", desc='" + desc + '\'' +
                ", jobUrl='" + jobUrl + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
