package com.gq.solr.scheduler;

import com.gq.solr.models.SolrBean;
import com.gq.solr.scheduler.job.MyJob;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrException;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * 搜索定时任务基类
 *
 * @author ganq
 */
public abstract class BaseScheduler extends MyJob {

    private SolrClient solrClient;

    private boolean debug;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    abstract void init();

    abstract List<? extends SolrBean> buildIndexList();

    @Override
    public void run() {
        try {
            // 构建索引
            buildIndexes();
       } catch (SolrException e) {
            logger.error(getJobName() + "构建solrClient 异常:", e);
        } catch (IOException e) {
            logger.error(getJobName() + "构建IO异常:", e);
        } catch (Exception e) {
            logger.error(getJobName() + "构建异常:", e);
        }
    }


    /**
     * 构建索引
     *
     */
    void buildIndexes() throws IOException, SolrServerException {
        List<? extends SolrBean> indexList = buildIndexList();

        if (CollectionUtils.isEmpty(indexList)) {
            logger.info(getJobName() + "本次没有索引数据更新！！！");
            return;
        }

        if (debug){
            solrClient.deleteByQuery("*:*");
        }

        solrClient.addBeans(indexList);
        solrClient.commit();

        logger.info("成功更新" + getJobName() + "索引" + getJobName() + indexList.size() + "条数据！！！");

    }


}
