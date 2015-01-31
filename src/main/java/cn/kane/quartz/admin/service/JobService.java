package cn.kane.quartz.admin.service;

import java.util.List;

import cn.kane.quartz.admin.domain.AdminJob;

public interface JobService {
	/**
	 * 获取某个scheduler下的所有job
	 * @return
	 */
	public List<AdminJob> getALLJobs(Long schedulerId) ;
	/**
	 * 添加一个job
	 * @param job
	 */
	public void addJob(AdminJob job) ;
	/**
	 * 更新一个job
	 * @param job
	 */
	public void updateJob(AdminJob job) ;
	/**
	 * 删除一个job
	 * @param job
	 */
	public void deleteJob(Long jobId) ;
	/**
	 * get一个job
	 * @param job
	 */
	public AdminJob getJob(Long jobId) ;
}
