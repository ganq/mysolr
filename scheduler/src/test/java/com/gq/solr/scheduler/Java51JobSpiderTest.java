package com.gq.solr.scheduler;

import com.gq.solr.models.Java51JobBean;
import com.gq.solr.scheduler.helper.Java51JobSpider;
import com.gq.solr.test.BaseTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
public class Java51JobSpiderTest extends BaseTestCase {

	
	@Test
	public void testJava51JobSpider() {

		List<Java51JobBean> list = Java51JobSpider.getResources();


		System.out.print(list);


		/*String text= "学历要求：  |   工作经验：无要求  |  公司性质：民营公司  |  公司规模10000人以上";
		String x [] =text.replaceAll("\\s*", "").split("\\|");
		for (String a : x) {
			System.out.println(a);
		}*/
		Assert.assertTrue(true);
	}

}