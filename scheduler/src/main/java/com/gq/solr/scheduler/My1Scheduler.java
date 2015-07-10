package com.gq.solr.scheduler;

import com.gq.solr.models.Java51JobBean;
import com.gq.solr.models.SolrBean;
import com.gq.solr.scheduler.helper.Java51JobSpider;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 *
 * @author ganq
 */
public class My1Scheduler extends BaseScheduler {

    private static final Logger logger = Logger.getLogger(My1Scheduler.class);

    @Override
    void init() {
        setSolrClient(new HttpSolrClient("http://localhost:8983/solr/my1"));
    }

    /**
     * 构建solr导入需要的数据集合
     */

	@Override
    List<? extends SolrBean> buildIndexList( ) {
        List<Java51JobBean> indexList = Java51JobSpider.getResources();
        return indexList;
    }

}