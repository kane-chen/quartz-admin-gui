package cn.kane.quartz.jmx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;

import org.apache.log4j.Logger;
import org.quartz.core.jmx.JobDataMapSupport;
import org.springframework.stereotype.Service;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.domain.AdminTrigger;

@Service
public class QuartzRemoteServiceImpl implements QuartzRemoteService {

	private static Logger log = Logger.getLogger(QuartzRemoteServiceImpl.class) ;
	
	@Override
	public String getVersion(ObjectName objectName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attachListener(String scheduleID) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AdminScheduler getAdminSchedulerById(String scheduleID)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdminJob(Long schedulerId, Map<String, Object> jobMap)
			throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[] { "java.util.Map","boolean" },
				"addAdminJob", new Object[] { jobMap,false });
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void updateAdminJob(Long schedulerId, Map<String, Object> jobMap)
			throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[] { "java.util.Map",	"boolean" }, 
				"addAdminJob",new Object[] { jobMap, true });
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void startAdminJobNow(AdminJob job)throws Exception {
		log.info("call start job.......");
		HashMap<String,Object> triggerMap = new HashMap<String,Object>();
		String triggerName = job.getJobid().toString();
		log.info(" start now trigger name  is " + triggerName);
		triggerMap.put("name",triggerName);
		triggerMap.put("group","now");
		triggerMap.put("description","立即执行");
		triggerMap.put("triggerClass", "org.quartz.impl.triggers.SimpleTriggerImpl");
		triggerMap.put("jobName", job.getJobname());
		triggerMap.put("jobGroup", job.getGroup());
		triggerMap.put("jobDataMap", JobDataMapSupport.newJobDataMap(job.getJobDataMap())) ;
		
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				job.getSchedulerid(), new String[]{"java.lang.String","java.lang.String","java.util.Map"},
				"scheduleJob", new Object[]{job.getJobname(),job.getGroup(),triggerMap});
		JmxRemoteExecutor.callJMXOperation(jmxInput);

	}

	@Override
	public void pauseAdminJob(AdminJob job) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				job.getSchedulerid(), new String[]{"java.lang.String","java.lang.String"}, "pauseJob", 
				new Object[]{job.getJobname(),job.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void resumeAdminJob(AdminJob job) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				job.getSchedulerid(), new String[]{"java.lang.String","java.lang.String"}, "resumeJob", 
				new Object[]{job.getJobname(),job.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void deleteAdminJob(AdminJob job) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				job.getSchedulerid(), new String[]{"java.lang.String","java.lang.String"}, "deleteJob", 
				new Object[]{job.getJobname(),job.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public List<AdminJob> getAdminJobDetails(AdminScheduler scheduler)
			throws Exception {
		List<AdminJob> jobs = null;
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(scheduler.getSchedulerid(), null, "AllJobDetails", null);
		TabularDataSupport tdata = (TabularDataSupport) JmxRemoteExecutor.callJMXAttribute(jmxInput);
		if (tdata != null) {
			jobs = new ArrayList<AdminJob>();
			for (Iterator<Object> it = tdata.values().iterator(); it.hasNext();) {
				Object object = (Object) it.next();
				if (!(object instanceof CompositeDataSupport)) {
					continue;
				}
				CompositeDataSupport compositeDataSupport = (CompositeDataSupport) object;
				AdminJob job = new AdminJob();
				//TODO
//				job.setSchedulerName(scheduler.getName());
//				job.setQuartzInstanceId(scheduler.getQuartzInstanceUUID());
//				job.setSchedulerInstanceId(scheduler.getInstanceId());
//				job.setJobname((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "name"));
//				log.info("job name:"+job.getJobname());
//				job.setDescription((String) JmxRemoteExecutor.convertToType(compositeDataSupport,"description"));
//				job.setDurability(((Boolean) JmxRemoteExecutor.convertToType(compositeDataSupport,"durability")).booleanValue());
//				job.setShouldRecover(((Boolean) JmxRemoteExecutor.convertToType(compositeDataSupport,"shouldRecover")).booleanValue());
//				job.setGroup((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "group"));
//				job.setJobclass((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "jobClass"));
//
//				// get Next Fire Time for job
//				List<AdminTrigger> triggers = this.getAdminTriggersForAdminJob(scheduler.getSchedulerid(), 
//						job.getJobname(), job.getGroup());
//				
//				if(triggers == null || triggers.size() == 0){
//					job.setState("NONE");
//				}else{
//					job.setState(getTriggerState(quartzInstance,scheduler,triggers.get(0)));
//				}
//				
//				log.info("job state:"+job.getState());
//				try {
//					if (triggers != null && triggers.size() > 0) {
//						Date nextFireTime = QuartzUtil.getNextFireTimeForJob(triggers);
//						job.setNextFireTime(nextFireTime);
//						job.setNumTriggers(triggers.size());
//					}
//				} catch (Throwable t) {
//					t.printStackTrace();
//				}
//
//				log.debug("Loaded job: " + job);
//				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public List<AdminTrigger> getAdminTriggersForAdminJob(Long schedulerId, String jobName, String groupName)
			throws Exception {
		List<AdminTrigger> triggers = null;

		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[]{String.class.getName(), String.class.getName()}, "getTriggersOfJob", 
				new Object[]{jobName, groupName});
	      @SuppressWarnings("unchecked")
		 List<CompositeDataSupport> list = (List<CompositeDataSupport>) JmxRemoteExecutor.callJMXOperation(jmxInput);
	      if (list != null && list.size() > 0)
	      {
	    	 log.info("-------"+jobName+" trigger size:"+list.size());
	         triggers = new ArrayList<AdminTrigger>();
	         //TODO
//	         for (int i = 0; i < list.size(); i++)
//	         {
//	            CompositeDataSupport compositeDataSupport = (CompositeDataSupport) list.get(i);
//	            AdminTrigger trigger = new AdminTrigger();
//	            trigger.setCalendarName((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "calendarName"));
//	            log.info("-------"+jobName+" trigger's calendar name:"+trigger.getCalendarName());
//	            trigger.setDescription((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "description"));
//	            trigger.setEndTime((Date) JmxRemoteExecutor.convertToType(compositeDataSupport, "endTime"));
//	            trigger.setFinalFireTime((Date) JmxRemoteExecutor.convertToType(compositeDataSupport, "finalFireTime"));
//	            trigger.setFireInstanceId((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "fireInstanceId"));
//	            trigger.setGroup((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "group"));
//	            trigger.setJobGroup((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "jobGroup"));
//	            trigger.setJobName((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "jobName"));
//	            log.info("-------"+jobName+" trigger's job name:"+trigger.getJobName());
//	            trigger.setMisfireInstruction(((Integer) JmxRemoteExecutor.convertToType(compositeDataSupport, "misfireInstruction")).intValue());
//	            trigger.setName((String) JmxRemoteExecutor.convertToType(compositeDataSupport, "name"));
//	            log.info("-------"+jobName+" trigger's  name:"+trigger.getName());
//	            trigger.setNextFireTime((Date) JmxRemoteExecutor.convertToType(compositeDataSupport, "nextFireTime"));
//	            log.info("-------"+jobName+" trigger's  nextFireTime:"+trigger.getNextFireTime());
//	            trigger.setPreviousFireTime((Date) JmxRemoteExecutor.convertToType(compositeDataSupport, "previousFireTime"));
//	            trigger.setPriority(((Integer) JmxRemoteExecutor.convertToType(compositeDataSupport, "priority")).intValue());
//	            trigger.setStartTime((Date) JmxRemoteExecutor.convertToType(compositeDataSupport, "startTime"));
//
//	            
//	            try 
//	            {
//	            	QuartzRemoteCommand stateJmxInput = new QuartzRemoteCommand(
//	            			schedulerId, new String[]{String.class.getName(), String.class.getName()}, "getTriggerState", 
//	            			new Object[]{trigger.getName(), trigger.getGroup()});
//	               String state = (String) JmxRemoteExecutor.callJMXOperation(stateJmxInput);
//	               trigger.setSTriggerState(state);
//	            }
//	            catch (Throwable tt)
//	            {
//	               trigger.setSTriggerState(Trigger.STATE_GET_ERROR);
//	            }
//
//	            //删除group为"now"的trigger
//	            if(trigger.getGroup().equals("now")){
//	            	deleteTrigger(quartzInstance, scheduler, trigger);
//	            }else{
//	            	 triggers.add(trigger);
//	            }
	         }
//	      }
	      return triggers;
	}

	//TODO
	@Override
	public AdminScheduler getAdminSchedulerByJmx(
			MBeanServerConnection quartzInstance, ObjectName objectName)
			throws Exception {
		 AdminScheduler scheduler = new AdminScheduler();
		 //TODO
//	      MBeanServerConnection connection = quartzInstance;
//	      scheduler.setObjectName(objectName);
//	      scheduler.setName((String) connection.getAttribute(objectName, "SchedulerName"));
//	      scheduler.setInstanceId((String) connection.getAttribute(objectName, "SchedulerInstanceId"));
//	      scheduler.setJobStoreClassName((String) connection.getAttribute(objectName, "JobStoreClassName"));
//	      scheduler.setThreadPoolClassName((String) connection.getAttribute(objectName, "ThreadPoolClassName"));
//	      scheduler.setThreadPoolSize((Integer) connection.getAttribute(objectName, "ThreadPoolSize"));
//	      scheduler.setShutdown((Boolean) connection.getAttribute(objectName, "Shutdown"));
//	      scheduler.setStarted((Boolean) connection.getAttribute(objectName, "Started"));
//	      scheduler.setStandByMode((Boolean) connection.getAttribute(objectName, "StandbyMode"));
//	      scheduler.setQuartzInstanceUUID(quartzInstance.getUuid());
//	      scheduler.setVersion(this.getVersion(quartzInstance, objectName));
	      return scheduler;
	}

	@Override
	public void addAdminTriggerForAdminJob(AdminJob job, Map<String, Object> triggerMap) throws Exception {
		triggerMap.put("jobDataMap",  JobDataMapSupport.newJobDataMap(job.getJobDataMap())) ;
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				job.getSchedulerid(), new String[]{"java.lang.String","java.lang.String","java.util.Map"}, "scheduleJob", 
				new Object[]{job.getJobname(),job.getGroup(),triggerMap});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void pauseAdminTrigger(Long schedulerId,AdminTrigger trigger) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[]{"java.lang.String","java.lang.String"}, "pauseTrigger", 
				new Object[]{trigger.getName(), trigger.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}
	
	@Override
	public void resumeAdminTrigger(Long schedulerId,AdminTrigger trigger) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[]{"java.lang.String","java.lang.String"}, "resumeTrigger", 
				new Object[]{trigger.getName(), trigger.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput);
	}

	@Override
	public void deleteAdminTrigger(Long schedulerId,AdminTrigger trigger) throws Exception {
		QuartzRemoteCommand jmxInput1 = new QuartzRemoteCommand(
				schedulerId, new String[]{"java.lang.String","java.lang.String"}, "unscheduleJob", 
				new Object[]{trigger.getName(),trigger.getGroup()});
		JmxRemoteExecutor.callJMXOperation(jmxInput1);
	}

	@Override
	public String getAdminTriggerState(Long schedulerId,AdminTrigger trigger) throws Exception {
		QuartzRemoteCommand jmxInput = new QuartzRemoteCommand(
				schedulerId, new String[]{"java.lang.String","java.lang.String"}, "getTriggerState", 
				new Object[]{trigger.getName(),trigger.getGroup()});
		    String state = (String)JmxRemoteExecutor.callJMXOperation(jmxInput);
		    return state;
	}


}
