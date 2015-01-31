package cn.kane.quartz.admin.action;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminTrigger;
import cn.kane.quartz.admin.service.JobService;
import cn.kane.quartz.admin.service.TriggerService;
import cn.kane.quartz.jmx.QuartzRemoteService;
import cn.kane.quartz.vo.ResponseVO;

@Controller
@RequestMapping("/trigger")
public class TriggerAction {

	private static Logger logger = LoggerFactory.getLogger(TriggerAction.class);
	
	@Autowired
	private TriggerService triggerService ;
	@Autowired
	private JobService jobService ;
	@Autowired
	private QuartzRemoteService quartzRemoteService ;
	
	@RequestMapping("listall")
	@ResponseBody
	public ResponseVO listAllTrigger(@RequestParam(value="jobId",required=true)Long jobId){
		logger.debug("listAllTriggers:{}",jobId);
		ResponseVO resp = new ResponseVO() ;
		List<AdminTrigger> triggers = triggerService.getALLTriggers(jobId) ;
		resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
		resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		resp.setRespObj(triggers);
		return resp ;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResponseVO addTrigger(@RequestParam(value="trigger",required=true)AdminTrigger trigger){
		logger.debug("addTrigger:{}",trigger);
		ResponseVO resp = new ResponseVO() ;
		triggerService.addTrigger(trigger);
		resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
		resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		return resp ;
	}
	
	@RequestMapping("sync")
	@ResponseBody
	public ResponseVO syncRemoteTrigger(@RequestParam(value="triggerId",required=true)Long triggerId){
		logger.debug("syncTrigger:{}",triggerId);
		ResponseVO resp = new ResponseVO() ;
		try{
			AdminTrigger trigger = triggerService.getTrigger(triggerId) ;
			AdminJob job = jobService.getJob(trigger.getJobid()) ;
			HashMap<String, Object> triggerMap = new HashMap<String, Object>();
			triggerMap.put("name", trigger.getName());
			triggerMap.put("group",trigger.getGroup());
			triggerMap.put("description", trigger.getDescription());
			triggerMap.put("cronExpression", trigger.getCronexpr());
			triggerMap.put("triggerClass", "org.quartz.impl.triggers.CronTriggerImpl");
			triggerMap.put("jobName", job.getJobname());
			triggerMap.put("jobGroup", trigger.getGroup());
			quartzRemoteService.addAdminTriggerForAdminJob(job, triggerMap);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		}catch(Exception e){
			logger.error("syncRemoteTrigger:{},ERROR:{}",triggerId,e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp ;
	}
	
	@RequestMapping("pause")
	@ResponseBody
	public ResponseVO pauseTrigger(@RequestParam(value="triggerId",required=true)Long triggerId){
		logger.debug("pauseTrigger:{}",triggerId);
		ResponseVO resp = new ResponseVO() ;
		try{
			AdminTrigger trigger = triggerService.getTrigger(triggerId) ;
			AdminJob job = jobService.getJob(trigger.getJobid()) ;
			Long schedulerId = job.getSchedulerid() ;
			quartzRemoteService.pauseAdminTrigger(schedulerId, trigger);;
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		}catch(Exception e){
			logger.error("pauseTrigger:{},ERROR:{}",triggerId,e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp ;
	}
	
	@RequestMapping("resume")
	@ResponseBody
	public ResponseVO resumeTrigger(@RequestParam(value="triggerId",required=true)Long triggerId){
		logger.debug("resumeTrigger:{}",triggerId);
		ResponseVO resp = new ResponseVO() ;
		try{
			AdminTrigger trigger = triggerService.getTrigger(triggerId) ;
			AdminJob job = jobService.getJob(trigger.getJobid()) ;
			Long schedulerId = job.getSchedulerid() ;
			quartzRemoteService.resumeAdminTrigger(schedulerId, trigger);;
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		}catch(Exception e){
			logger.error("resumeTrigger:{},ERROR:{}",triggerId,e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp ;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResponseVO deleteTrigger(@RequestParam(value="triggerId",required=true)Long triggerId){
		logger.debug("deleteTrigger:{}",triggerId);
		ResponseVO resp = new ResponseVO() ;
		try{
			AdminTrigger trigger = triggerService.getTrigger(triggerId) ;
			AdminJob job = jobService.getJob(trigger.getJobid()) ;
			Long schedulerId = job.getSchedulerid() ;
			quartzRemoteService.deleteAdminTrigger(schedulerId, trigger);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		}catch(Exception e){
			logger.error("deleteTrigger:{},ERROR:{}",triggerId,e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp ;
	}
	
}
