package cn.kane.quartz.admin.service;

import java.util.List;

import cn.kane.quartz.admin.domain.AdminScheduler;


public interface SchedulerService {
	/**
	 * 获取所有的schedulers
	 * @return
	 */
	public List<AdminScheduler> getALLSchedulers()  ;
	/**
	 * get一个scheduler
	 * @param scheduler
	 */
	public AdminScheduler getScheduler(Long schedulerId)  ;
	/**
	 * 添加一个scheduler
	 * @param scheduler
	 */
	public void addScheduler(AdminScheduler scheduler)  ;
	/**
	 * 更新一个scheduler
	 * @param scheduler
	 */
	public void updateScheduler(AdminScheduler scheduler)  ;
	/**
	 * 删除一个scheduler
	 * @param schedulerId
	 */
	public void deleteScheduler(Long schedulerId)  ;
	
	/**
	 * 根据host&port查询scheduler
	 * @param host
	 * @param port
	 * @param schedulerName
	 * @return
	 */
	public AdminScheduler getSchedulerByHost(String host,int port,String schedulerName)   ;
}
