package cn.kane.quartz.admin.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.service.JobService;
import cn.kane.quartz.admin.service.SchedulerService;
import cn.kane.quartz.jmx.QuartzMbeanConnFactory;
import cn.kane.quartz.vo.ResponseVO;

@Controller
@RequestMapping("/scheduler")
public class SchedulerAction {

	private static Logger logger = LoggerFactory.getLogger(SchedulerAction.class);
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private JobService jobService ;

	@RequestMapping("listall.do")
	@ResponseBody
	public ResponseVO listAllScheduler() {
		logger.info("listAllSchedulers");
		ResponseVO resp = new ResponseVO();
		List<AdminScheduler> schedulers = schedulerService.getALLSchedulers();
		resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
		resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		resp.setRespObj(schedulers);
		return resp ;
	}

	@RequestMapping("add.do")
	@ResponseBody
	public ResponseVO addScheduler(
			@RequestBody AdminScheduler scheduler) {
		logger.info("try to addScheduler:{}", scheduler);
		ResponseVO resp = new ResponseVO();
		try {
			schedulerService.addScheduler(scheduler);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("addScheduler:{} Error:{}", scheduler, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}

	@RequestMapping("connect.do")
	@ResponseBody
	public ResponseVO connectScheduler(
			@RequestParam(value = "scheduler", required = true) Long schedulerId) {
		logger.info("try to connectScheduler:{}", schedulerId);
		ResponseVO resp = new ResponseVO();
		try {
			AdminScheduler scheduler = schedulerService.getScheduler(schedulerId);
			//check conn
			boolean isConnect = QuartzMbeanConnFactory.isConnect(schedulerId) ;
			if(!isConnect){
				QuartzMbeanConnFactory.connQuartzScheduler(
						scheduler.getSchedulerid(), scheduler.getHost(),
						scheduler.getPort(), scheduler.getUsername(),
						scheduler.getPassword(),null);
			}
			//TODO scheduler status
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("addScheduler:{} Error:{}", schedulerId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public ResponseVO deleteScheduler(
			@RequestParam(value = "scheduler", required = true) Long schedulerId) {
		logger.info("try to deleteScheduler:{}", schedulerId);
		ResponseVO resp = new ResponseVO();
		try {
			List<AdminJob> jobs = jobService.getALLJobs(schedulerId) ;
			if(null == jobs || jobs.isEmpty()){
				schedulerService.deleteScheduler(schedulerId);
				resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
				resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
			}else{
				resp.setRetCode(301);
				resp.setMessage("当前scheduler中有定时任务");
			}
		} catch (Exception e) {
			logger.error("deleteScheduler:{} Error:{}", schedulerId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
}
