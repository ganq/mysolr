package com.gq.solr.controller;

import com.gq.solr.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CommonUtil commonUtil;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        try {

          /*  announcementParam.setPageSize(10);
            announcementParam.setKeyword(commonUtil.separateStringByLen(announcementParam.getKeyword(), 50).trim());
            Map<String, Object> result = announcementSearchService.getSearchResult(announcementParam);

            List<AnnouncementsVO> searchResult = (List<AnnouncementsVO>) result.get("searchResult");

            List<SearchCategoryVO> level3Category = (List<SearchCategoryVO>) result.get("level3Category");
            model.addAllAttributes(commonUtil.getLvl3MenuIsExpandAndScroll(level3Category, announcementParam.getCodelevel3()));

            model.addAllAttributes(result);

            model.addAttribute("encodeKeyword", commonUtil.replaceKeywordStr(announcementParam.getKeyword()));*/

        } catch (Exception e) {
            logger.error("Search Announcements controller error", e);
        }

        return "index";
    }


}