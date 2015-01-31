package cn.kane.quartz.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.service.JobService;
import cn.kane.quartz.admin.service.SchedulerService;
import cn.kane.quartz.jmx.QuartzRemoteService;
import cn.kane.quartz.vo.ResponseVO;

@Controller
@RequestMapping("/job")
public class JobAction {

	private static Logger logger = LoggerFactory.getLogger(JobAction.class);
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private JobService jobService ;
	@Autowired
	private QuartzRemoteService quartzRemoteService ;
	
	@RequestMapping("listall")
	@ResponseBody
	public ResponseVO listAllJobs(@RequestParam(value="schedulerId",required=true) Long schedulerId){
		logger.info("try to getAllJobs {}", schedulerId);
		ResponseVO resp = new ResponseVO();
		List<AdminJob> jobs = jobService.getALLJobs(schedulerId);
		resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
		resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		resp.setRespObj(jobs);
		return resp ;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResponseVO addJob(@RequestParam(value = "job",required=true)AdminJob job,
			@RequestParam(value="jobDataMapKey")List<String> jobDataMapKey,
			@RequestParam(value="jobDataMapValue")List<String> jobDataMapValue) {
		logger.info("try to addScheduler {}", job);
		ResponseVO resp = new ResponseVO();
		try {
			if(jobDataMapKey.size() > 0){
				Map<String, Object> parammap = new HashMap<String, Object>();
				for (int i=0; i<jobDataMapKey.size(); i++) {
					parammap.put(jobDataMapKey.get(i), jobDataMapValue.get(i));
				}
				
				job.setJobDataMap(parammap);
			}
			jobService.addJob(job);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("addJob:{} Error:{}", job, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResponseVO deleteJob(@RequestParam(value = "jobId",required=true)Long jobId) {
		logger.info("try to deleteJob {}", jobId);
		ResponseVO resp = new ResponseVO();
		try {
			//TODO transcational
			jobService.deleteJob(jobId);
			AdminJob job = jobService.getJob(jobId) ;
			quartzRemoteService.deleteAdminJob(job) ;
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("addJob:{} Error:{}", jobId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
	@RequestMapping("syncremote")
	@ResponseBody
	public ResponseVO syncRemoteJob(@RequestParam(value = "jobId",required=true)Long jobId) {
		logger.info("try to syncRemoteJob {}", jobId);
		ResponseVO resp = new ResponseVO();
		try {
			AdminJob job = jobService.getJob(jobId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", job.getJobname());
			map.put("group", job.getGroup());
			map.put("description", job.getDescription());
			map.put("jobClass", job.getJobclass());
			map.put("durability", true);
			map.put("jobDetailClass", "org.quartz.impl.JobDetailImpl");
			if(job.getJobDataMap().size() > 0){
				map.put("jobDataMap", job.getJobDataMap());   //job需要的参数
			}
			quartzRemoteService.addAdminJob(job.getSchedulerid(),map);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("syncRemoteJob:{} Error:{}", jobId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
	@RequestMapping("start")
	@ResponseBody
	public ResponseVO start(@RequestParam(value = "jobId",required=true)Long jobId) {
		logger.info("try to start {}", jobId);
		ResponseVO resp = new ResponseVO();
		try {
			AdminJob job = jobService.getJob(jobId);
			quartzRemoteService.startAdminJobNow(job);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("start:{} Error:{}", jobId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	@RequestMapping("pause")
	@ResponseBody
	public ResponseVO pause(@RequestParam(value = "jobId",required=true)Long jobId) {
		logger.info("try to pause {}", jobId);
		ResponseVO resp = new ResponseVO();
		try {
			AdminJob job = jobService.getJob(jobId);
			quartzRemoteService.pauseAdminJob(job);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("pause:{} Error:{}", jobId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	@RequestMapping("resume")
	@ResponseBody
	public ResponseVO resume(@RequestParam(value = "jobId",required=true)Long jobId) {
		logger.info("try to resume {}", jobId);
		ResponseVO resp = new ResponseVO();
		try {
			AdminJob job = jobService.getJob(jobId);
			quartzRemoteService.resumeAdminJob(job);
			resp.setRetCode(ResponseVO.RetStatus.SUCCESS.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("resume:{} Error:{}", jobId, e);
			resp.setRetCode(ResponseVO.RetStatus.FAILED.getRetCode());
			resp.setMessage(ResponseVO.RetStatus.FAILED.getMessage());
		}
		return resp;
	}
	
}
