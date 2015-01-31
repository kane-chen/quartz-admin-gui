package cn.kane.quartz.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminJobExample;
import cn.kane.quartz.admin.mapper.AdminJobMapper;
import cn.kane.quartz.admin.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private AdminJobMapper adminJobMapper ;
	
	public List<AdminJob> getALLJobs(Long schedulerId){
		AdminJobExample condition = new AdminJobExample() ;
		condition.createCriteria().andScheduleridEqualTo(schedulerId) ;
		return adminJobMapper.selectByExample(condition);
	}

	public void addJob(AdminJob job)   {
		adminJobMapper.insertSelective(job);
	}

	public void updateJob(AdminJob job)   {
		adminJobMapper.updateByPrimaryKeySelective(job);
	}

	public void deleteJob(Long jobId)   {
		adminJobMapper.deleteByPrimaryKey(jobId) ;
	}

	@Override
	public AdminJob getJob(Long jobId) {
		return adminJobMapper.selectByPrimaryKey(jobId);
	}

}
