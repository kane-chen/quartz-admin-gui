package cn.kane.quartz.admin.service;

import java.util.List;

import cn.kane.quartz.admin.domain.AdminTrigger;

public interface TriggerService {
	/**
	 * 获取某个job下的所有trigger
	 * @return
	 */
	public List<AdminTrigger> getALLTriggers(Long jobId)  ;
	/**
	 * 添加一个trigger
	 * @param trigger
	 */
	public void addTrigger(AdminTrigger trigger)  ;
	/**
	 * 更新一个trigger
	 * @param trigger
	 */
	public void updateTrigger(AdminTrigger trigger)  ;
	/**
	 * 删除一个trigger
	 * @param trigger
	 */
	public void deleteTrigger(Long triggerId)  ;
	/**
	 * get一个trigger
	 * @param trigger
	 */
	public AdminTrigger getTrigger(Long triggerId)  ;
}
