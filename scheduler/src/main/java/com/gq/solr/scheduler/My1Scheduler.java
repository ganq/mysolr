package com.gq.solr.scheduler;

import com.gq.solr.models.ZOLBean;
import com.gq.solr.models.SolrBean;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

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
        List<ZOLBean> indexList = new ArrayList<ZOLBean>();
        ZOLBean zolBean = new ZOLBean();
        zolBean.setId("xxx");
        zolBean.setTitle("北京奔达康集团");

        ZOLBean zolBean2 = new ZOLBean();
        zolBean2.setId("xxx2");
        zolBean2.setTitle("上海奔达康有限公司");

        ZOLBean zolBean3 = new ZOLBean();
        zolBean3.setId("xxx3");
        zolBean3.setTitle("广东奔达康金融控股公司");

        indexList.add(zolBean);
        indexList.add(zolBean2);
        indexList.add(zolBean3);
        return indexList;
    }

}