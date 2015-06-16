package com.gq.solr.scheduler;

import com.gq.solr.test.BaseTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class My1SchedulerTest extends BaseTestCase {
	
	@Autowired
	My1Scheduler my1Scheduler;
	
	@Test
	public void testSolrScheduler() {
		my1Scheduler.setDebug(true);
		my1Scheduler.run();
		Assert.assertTrue(true);
	}

}