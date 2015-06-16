package com.gq.solr.service;

import com.gq.solr.model.SolrQueryEnhanced;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("suggestService")
public class SuggestService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SolrQueryEnhanced announcementsSolr;

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Long> getSearchSuggestion(String keyword, String module) {
		Map<String,Long> resultMap = new HashMap<String, Long>();
        if (StringUtils.isBlank(module)){
            return null;
        }
		if (module.contains(",")){
            String [] modules = module.split(",");
            for (String m : modules){
                resultMap.putAll(getResultByModule(keyword,m));
            }
        }else{
            return getResultByModule(keyword,module);
        }

		return BaseUtil.deleteMapItemByCount(BaseUtil.sortByValue(resultMap,true), 10);
	}*/


    /*private Map<String, Long> getResultByModule(String keyword, String module) {
        Map<String,Long> resultMap = new HashMap<String, Long>();

        if ("announcement".equals(module)) {
            //招标预告标题中文和拼音
            resultMap.putAll(BaseUtil.getChineseSuggest(announcementsSolr.getSolrServer(), keyword, "titleSuggest", 10));
            resultMap.putAll(BaseUtil.getPinyinSuggest(announcementsSolr.getSolrServer(), keyword, "titlePinyin","titleSuggest", 10));
        }


        return BaseUtil.deleteMapItemByCount(BaseUtil.sortByValue(resultMap,true), 10);
    }*/
	

}
