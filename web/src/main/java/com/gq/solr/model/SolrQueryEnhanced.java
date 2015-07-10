package com.gq.solr.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class SolrQueryEnhanced {

	private Logger logger =  Logger.getLogger(this.getClass());

	private String url;

	private Integer soTimeOut;

	private Integer connectionTimeOut;

	private Integer maxConnectionsPerHost;

	private Integer maxTotalConnections;

	private Integer maxRetries;

	private HttpSolrClient httpSolrClient = null;

	public HttpSolrClient getHttpSolrClient() {
		return httpSolrClient;
	}

	public void setHttpSolrClient(HttpSolrClient httpSolrClient) {
		this.httpSolrClient = httpSolrClient;
	}

	public SolrQueryEnhanced() {
	}

	/**
	 * 初始化Solr服务配置
	 * 
	 * @throws MalformedURLException
	 */
	public void init() throws MalformedURLException {

		httpSolrClient = new HttpSolrClient(url);
		httpSolrClient.setSoTimeout(soTimeOut);
		httpSolrClient.setConnectionTimeout(connectionTimeOut);
		httpSolrClient.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
		httpSolrClient.setMaxTotalConnections(maxTotalConnections);
		httpSolrClient.setFollowRedirects(false);
		httpSolrClient.setAllowCompression(true);
		//httpSolrClient.setMaxRetries(maxRetries);
	}

	/**
	 * 得到Solr查询响应对象
	 *
	 * @param startIndex
	 *            分页起始索引
	 * @param pageSize
	 *            分页每页记录数
	 */
	public QueryResponse query(List<SolrQueryBO> dos, int startIndex, int pageSize) throws SolrServerException {
		SolrQuery query = new SolrQuery();

		if (null == dos) {
			logger.info("search field not found!");
		} else {
			StringBuilder sb = new StringBuilder();
			for (SolrQueryBO s : dos) {

				// 设置查询字段
				if (s.isQueryField()) {
					if (StringUtils.isBlank(s.getCustomQueryStr())) {
						if (s.isRequiredCondition()) {
							sb.append("+");
						}
						sb.append(s.getfN()).append(":");
						sb.append(s.getfV()).append(" ");
					}else{
						sb.append(s.getCustomQueryStr()).append(" ");
					}
				}

				// 设置排序字段
				if (s.isSortField()) {
					query.addSort(s.getfN(), s.getSort());
				}

				// 设置facet字段
				if (s.isFacetField()) {
					query.setFacet(true).addFacetField(s.getfN()).setFacetMinCount(1).setFacetLimit(-1);
					if (s.getFacetLimit() > 0) {
						query.setFacetLimit(s.getFacetLimit());
					}
					
				}
				// 设置高亮字段
				if (s.isHighlightField()) {
					query.setHighlight(true).addHighlightField(s.getfN()).
					setHighlightSimplePre("<em class='search_highlight'>").setHighlightSimplePost("</em>");
					if (s.isHighlightPreserveMulti()) {
						query.set("hl.preserveMulti", true);
					}
				}
				// 设置filter query
				if (s.isFilterQueryField()) {
					if (StringUtils.isBlank(s.getCustomQueryStr())) {
						query.addFilterQuery(s.getfN()+":"+s.getfV());
					}else{
						query.addFilterQuery(s.getCustomQueryStr());
					}
				}
				// pivot facet字段
				if (s.isFacetPivotField()) {
					query.setFacet(true).addFacetPivotField(s.getfN());
				}
				//自定义参数
				if (s.getSolrParams() != null) {
					query.add(s.getSolrParams());	
				}
				//query.set("debugQuery", true);
			}
			if (sb.length() > 0) {
				query.setQuery(sb.toString());
			}
		}

		if (startIndex >= 0) {
			query.setStart(Integer.parseInt(String.valueOf(startIndex)));
		}
		if (pageSize >= 0) {
			query.setRows(Integer.parseInt(String.valueOf(pageSize)));
		}
		try {
			return httpSolrClient.query(query);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public Integer getMaxConnectionsPerHost() {
		return maxConnectionsPerHost;
	}

	public void setMaxConnectionsPerHost(Integer maxConnectionsPerHost) {
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

	public Integer getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}


}