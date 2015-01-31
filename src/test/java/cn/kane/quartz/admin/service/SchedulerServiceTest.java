/*
 * @(#)TestCodeService.java	2014年1月6日
 *
 * @Company <Opportune Technology Development Company LTD.>
 */
package cn.kane.quartz.admin.service;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.service.SchedulerService;

/**
 * @Project <CL-Allocation tool>
 * @version <1.0>
 * @Author <Administrator>
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext_test.xml")
public class SchedulerServiceTest extends TestCase {

	protected static Logger logger = LoggerFactory.getLogger(SchedulerServiceTest.class);

	@Autowired
	private SchedulerService schedulerService;

	private Long schedulerId = 1L;
	private String host = "127.0.0.1";
	private int port = 8099;
	private String schedulerName = "SCH-" + schedulerId;

	@Before
	// should run once before every test-case
	// @BeforeClass should run once with multi-testcase,but setup-method must be
	// static
	public void setup() {
		logger.debug("----------setup----------");
		AdminScheduler scheduler = new AdminScheduler();
		scheduler.setSchedulerid(schedulerId);
		scheduler.setName(schedulerName);
		scheduler.setHost(host);
		scheduler.setPort(port);
		scheduler.setPassword("password");
		scheduler.setUsername("uname");
		schedulerService.addScheduler(scheduler);
	}

	@Test
	public void testSave() {
		logger.debug("--------------save-------------");
	}

	@Test
	public void testSelect() {
		AdminScheduler scheduler = schedulerService.getSchedulerByHost(host, port, schedulerName);
		Assert.assertNotNull(scheduler);
	}

	@Test
	public void testUpdate() {
		AdminScheduler scheduler = schedulerService.getSchedulerByHost(host, port, schedulerName);
		String uname = "ccccccccccccccccc";
		scheduler.setUsername(uname);
		schedulerService.updateScheduler(scheduler);
		scheduler = schedulerService.getSchedulerByHost(host, port, schedulerName);
		Assert.assertEquals(uname, scheduler.getUsername());
	}

	@After
	// should run once after every test-case
	// @AfterClass //should run once within multi-testcase,but teardown-method
	// must be static
	public void teardown() {
		logger.debug("----------teardown----------");
		schedulerService.deleteScheduler(schedulerId);
	}

}
