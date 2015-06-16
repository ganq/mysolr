package com.gq.solr.models;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by GanQuan on 2015/6/10 0010.
 */

public class SolrBean {

    @Field
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
