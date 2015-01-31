package cn.kane.quartz.jmx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminTrigger;

public class QuartzUtil {

	private static Logger log = Logger.getLogger(QuartzUtil.class);

	public static Date getNextFireTimeForJob(List<AdminTrigger> triggers) {
		log.info("get Next FIre Time......");
		Date theNext = null;
		if (triggers != null && triggers.size() > 0) {
			for (int i = 0; i < triggers.size(); i++) {
				//TODO
//				AdminTrigger trigger = triggers.get(i);
//				if (trigger.getNextFireTime() == null) {
//					continue;
//				} else {
//					theNext = trigger.getNextFireTime();
//				}
//				if (theNext != null && trigger.getNextFireTime().before(theNext)) {
//					theNext = trigger.getNextFireTime();
//				}
			}
		}
		return theNext;
	}

	public static Map<String, Object> convertJob2Map(AdminJob job) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", job.getJobname());
		map.put("group", job.getGroup());
		map.put("description", job.getDescription());
		map.put("jobClass", job.getJobclass());
		map.put("durability", job.getDurability());
		map.put("shouldRecover", job.getShouldrecover());
		map.put("jobDataMap", new HashMap<String, String>());
		return map;
	}
}