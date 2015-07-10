package com.gq.solr.scheduler.helper;

import com.gq.solr.models.Java51JobBean;
import com.gq.solr.utils.CommonUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by GanQuan on 2015/6/10 0010.
 */
public class Java51JobSpider {


    private static final String url = "http://search.51job.com/jobsearch/search_result.php?" +
            "fromJs=1&" +
            "jobarea=040000%2C00&" +
            "district=000000&" +
            "funtype=0000&" +
            "industrytype=00&" +
            "issuedate=9&" +
            "providesalary=99&" +
            "keyword=java&" +
            "keywordtype=1&" +
            "curr_page={0}&" +
            "lang=c&" +
            "stype=1&" +
            "postchannel=0000&" +
            "workyear=99&" +
            "cotype=99&" +
            "degreefrom=99&" +
            "jobterm=01&" +
            "companysize=99&" +
            "lonlat=0%2C0&" +
            "radius=-1&" +
            "ord_field=0&" +
            "list_type=1&" +
            "fromType=12";


    public static List<Java51JobBean> getResources() {
        List<Java51JobBean> list = new ArrayList<Java51JobBean>();
        for (int i = 1; i <= 30; i++) {

            list.addAll(getResourcesByUrl(MessageFormat.format(url, i)));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("已获取记录：" + list.size() + "条.");
        }
        return list;
    }

    private static List<Java51JobBean> getResourcesByUrl(String resourceUrl) {

        Document doc = null;
        try {
            doc = Jsoup.connect(resourceUrl).timeout(50000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Java51JobBean> list = new ArrayList<Java51JobBean>();
        Elements resultList = doc.select("#resultList tbody");


        Elements elementsTr0 = resultList.select("tr.tr0");
        Elements elementsTr1 = resultList.select("tr.tr1");
        Elements elementsTr2 = resultList.select("tr.tr2");


        int size = Math.min(Math.min(elementsTr0.size(),elementsTr1.size()),elementsTr2.size());

        for (int i = 0; i < size; i++) {

            Element tr0 = elementsTr0.get(i);
            Element tr1 = elementsTr1.get(i);
            Element tr2 = elementsTr2.get(i);


            Java51JobBean java51JobBean = new Java51JobBean();
            java51JobBean.setId(tr0.select(".td0 input").val());
            if (tr0.select(".td1 a").hasAttr("title")) {
                java51JobBean.setTitle(tr0.select(".td1 a").attr("title"));
            } else {
                java51JobBean.setTitle(tr0.select(".td1 a").text());
            }
            java51JobBean.setJobUrl(tr0.select(".td1 a").attr("href"));
            java51JobBean.setCompany(tr0.select(".td2 a").text());
            java51JobBean.setCompanyUrl(tr0.select(".td2 a").attr("href"));
            java51JobBean.setLocation(tr0.select(".td3 span").text());
            try {
                java51JobBean.setUpdateDate(CommonUtil.convertToBeijingTime(DateUtils.parseDate(tr0.select(".td4 span").text(),"yyyy-MM-dd")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String text = tr1.select(".td1234").html();
            String textArrays[] = text.replaceAll("&nbsp;", "")
                    // .replaceAll("学历要求：", "").replaceAll("工作经验：", "").replaceAll("公司性质：", "").replaceAll("公司规模","")
                    .split("\\|");

            for (String t : textArrays) {
                if (t.contains("学历要求：")) {
                    java51JobBean.setEdu(t.replaceAll("学历要求：", "").trim());
                }
                if (t.contains("工作经验：")) {
                    java51JobBean.setWorkingExp(t.replaceAll("工作经验：", "").trim());
                }
                if (t.contains("公司性质：")) {
                    java51JobBean.setCompanyNature(t.replaceAll("公司性质：", "").trim());
                }
                if (t.contains("公司规模")) {
                    java51JobBean.setCompanySize(t.replaceAll("公司规模", "").trim());
                }

            }

            java51JobBean.setDesc(tr2.select(".td1234 span").text());

            list.add(java51JobBean);

        }

        return list;
    }


}
