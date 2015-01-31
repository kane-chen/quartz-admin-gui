package cn.kane.quartz.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.domain.AdminSchedulerExample;
import cn.kane.quartz.admin.domain.AdminSchedulerExample.Criteria;
import cn.kane.quartz.admin.mapper.AdminSchedulerMapper;
import cn.kane.quartz.admin.service.SchedulerService;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private AdminSchedulerMapper adminSchedulerMapper;

	public List<AdminScheduler> getALLSchedulers() {
		return adminSchedulerMapper.selectByExample(new AdminSchedulerExample());
	}

	public void addScheduler(AdminScheduler scheduler) {
		adminSchedulerMapper.insertSelective(scheduler);
	}

	public void updateScheduler(AdminScheduler scheduler) {
		adminSchedulerMapper.updateByPrimaryKey(scheduler);
	}

	public void deleteScheduler(Long schedulerId) {
		adminSchedulerMapper.deleteByPrimaryKey(schedulerId);
	}

	public AdminScheduler getSchedulerByHost(String host, int port,	String schedulerName) {
		AdminSchedulerExample condition = new AdminSchedulerExample();
		Criteria criteria = condition.createCriteria();
		criteria.andHostEqualTo(host).andPortEqualTo(port);
		List<AdminScheduler> schedulers = adminSchedulerMapper
				.selectByExample(condition);
		AdminScheduler scheduler = null;
		if (null != schedulers && !schedulers.isEmpty()) {
			scheduler = schedulers.get(0);
		}
		return scheduler;
	}

	@Override
	public AdminScheduler getScheduler(Long schedulerId) {
		return adminSchedulerMapper.selectByPrimaryKey(schedulerId) ;
	}

}
