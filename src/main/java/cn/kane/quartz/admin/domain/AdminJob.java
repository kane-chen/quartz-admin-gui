package cn.kane.quartz.admin.domain;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class AdminJob {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.JOBID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Long jobid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.SCHEDULERID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Long schedulerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.JOBNAME
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private String jobname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.GROUP
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private String group;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.JOBCLASS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private String jobclass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.JOBDATAMAP
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    @SuppressWarnings("unused")
	private String jobdatamap;

    private Map<String, Object> jobDataMap;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.DESCRIPTION
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.DURABILITY
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Integer durability;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.SHOULDRECOVER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Integer shouldrecover;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.TRIGGERCOUNT
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Integer triggercount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUARTZ_ADMIN_JOB.JOBSTATUS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    private Integer jobstatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.JOBID
     *
     * @return the value of QUARTZ_ADMIN_JOB.JOBID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Long getJobid() {
        return jobid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.JOBID
     *
     * @param jobid the value for QUARTZ_ADMIN_JOB.JOBID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.SCHEDULERID
     *
     * @return the value of QUARTZ_ADMIN_JOB.SCHEDULERID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Long getSchedulerid() {
        return schedulerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.SCHEDULERID
     *
     * @param schedulerid the value for QUARTZ_ADMIN_JOB.SCHEDULERID
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setSchedulerid(Long schedulerid) {
        this.schedulerid = schedulerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.JOBNAME
     *
     * @return the value of QUARTZ_ADMIN_JOB.JOBNAME
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.JOBNAME
     *
     * @param jobname the value for QUARTZ_ADMIN_JOB.JOBNAME
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.GROUP
     *
     * @return the value of QUARTZ_ADMIN_JOB.GROUP
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.GROUP
     *
     * @param group the value for QUARTZ_ADMIN_JOB.GROUP
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.JOBCLASS
     *
     * @return the value of QUARTZ_ADMIN_JOB.JOBCLASS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public String getJobclass() {
        return jobclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.JOBCLASS
     *
     * @param jobclass the value for QUARTZ_ADMIN_JOB.JOBCLASS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setJobclass(String jobclass) {
        this.jobclass = jobclass;
    }

    public String getJobdatamap() {
		return JSON.toJSONString(jobDataMap);
	}
	
	public void setJobdatamap(String json) {
		jobDataMap = JSON.parseObject(json);
	}
	public Map<String, Object> getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(Map<String, Object> jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.DESCRIPTION
     *
     * @return the value of QUARTZ_ADMIN_JOB.DESCRIPTION
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.DESCRIPTION
     *
     * @param description the value for QUARTZ_ADMIN_JOB.DESCRIPTION
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.DURABILITY
     *
     * @return the value of QUARTZ_ADMIN_JOB.DURABILITY
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Integer getDurability() {
        return durability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.DURABILITY
     *
     * @param durability the value for QUARTZ_ADMIN_JOB.DURABILITY
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setDurability(Integer durability) {
        this.durability = durability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.SHOULDRECOVER
     *
     * @return the value of QUARTZ_ADMIN_JOB.SHOULDRECOVER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Integer getShouldrecover() {
        return shouldrecover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.SHOULDRECOVER
     *
     * @param shouldrecover the value for QUARTZ_ADMIN_JOB.SHOULDRECOVER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setShouldrecover(Integer shouldrecover) {
        this.shouldrecover = shouldrecover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.TRIGGERCOUNT
     *
     * @return the value of QUARTZ_ADMIN_JOB.TRIGGERCOUNT
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Integer getTriggercount() {
        return triggercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.TRIGGERCOUNT
     *
     * @param triggercount the value for QUARTZ_ADMIN_JOB.TRIGGERCOUNT
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setTriggercount(Integer triggercount) {
        this.triggercount = triggercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUARTZ_ADMIN_JOB.JOBSTATUS
     *
     * @return the value of QUARTZ_ADMIN_JOB.JOBSTATUS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public Integer getJobstatus() {
        return jobstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUARTZ_ADMIN_JOB.JOBSTATUS
     *
     * @param jobstatus the value for QUARTZ_ADMIN_JOB.JOBSTATUS
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    public void setJobstatus(Integer jobstatus) {
        this.jobstatus = jobstatus;
    }
}