package cn.kane.quartz.admin.http;

import org.junit.Assert;

import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.utils.HttpClientUtils;
import cn.kane.quartz.admin.utils.JsonUtils;
import cn.kane.quartz.vo.ResponseVO;
import junit.framework.TestCase;

public class SchedulerActionTest extends TestCase {

	private String contextUri = "http://localhost:8081/quartz" ;
	private String moduleUri = "/scheduler" ;
	
	public void testAdd() throws Exception{
		AdminScheduler scheduler = new AdminScheduler() ;
		scheduler.setName("schedule-1");
		scheduler.setHost("127.0.0.1");
		scheduler.setPort(9001);
		String jsonStr = JsonUtils.objectToJson(scheduler) ;
		String reqUri = contextUri + moduleUri + "/add.do" ;
		String respJson = HttpClientUtils.jsonCall(reqUri, jsonStr);
		ResponseVO resp = JsonUtils.json2Object(respJson, ResponseVO.class) ;
		Assert.assertEquals(ResponseVO.RetStatus.SUCCESS.getRetCode(), resp.getRetCode());
	}
	
	public void testList() throws Exception{
		String reqUri = contextUri + moduleUri + "/listall.do" ;
		String respJson = HttpClientUtils.jsonCall(reqUri, "");
		System.out.println(respJson);
	}
}
