package cn.kane.quartz.jmx;

import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.domain.AdminTrigger;

public interface QuartzRemoteService {

	public String getVersion(ObjectName objectName) throws Exception;

	public void attachListener(String scheduleID) throws Exception;
	
	public AdminScheduler getAdminSchedulerById(String scheduleID) throws Exception;

	public void addAdminJob(Long schedulerId,Map<String, Object> jobMap) throws Exception;

	public void updateAdminJob(Long schedulerId, Map<String, Object> jobMap) throws Exception;

	public void startAdminJobNow(AdminJob job)throws Exception;
	
	public void pauseAdminJob(AdminJob job)throws Exception;

	public void resumeAdminJob(AdminJob job)	throws Exception;
	
	public void deleteAdminJob(AdminJob job)	throws Exception;

	public List<AdminJob> getAdminJobDetails(AdminScheduler adminScheduler)	throws Exception;

	public List<AdminTrigger> getAdminTriggersForAdminJob(
			Long schedulerId, String jobName, String groupName) throws Exception;

	public AdminScheduler getAdminSchedulerByJmx(
			MBeanServerConnection quartzInstance, ObjectName objectName)throws Exception;

	public void addAdminTriggerForAdminJob(AdminJob job, Map<String, Object> triggerMap) throws Exception;
	
	public void pauseAdminTrigger(Long schedulerId,AdminTrigger trigger) throws Exception;

	public void resumeAdminTrigger(Long schedulerId,AdminTrigger trigger) throws Exception;
	
	public void deleteAdminTrigger(Long schedulerId, AdminTrigger trigger) throws Exception;
	
	public String getAdminTriggerState(Long schedulerId,AdminTrigger trigger) throws Exception;
	
}
