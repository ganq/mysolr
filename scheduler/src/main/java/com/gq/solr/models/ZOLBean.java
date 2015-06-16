package com.gq.solr.models;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by GanQuan on 2015/6/10 0010.
 */
public class ZOLBean extends SolrBean{

    @Field
    private String title;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
