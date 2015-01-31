package cn.kane.quartz.admin.mapper;

import cn.kane.quartz.admin.domain.AdminScheduler;
import cn.kane.quartz.admin.domain.AdminSchedulerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminSchedulerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int countByExample(AdminSchedulerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int deleteByExample(AdminSchedulerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int deleteByPrimaryKey(Long schedulerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int insert(AdminScheduler record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int insertSelective(AdminScheduler record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    List<AdminScheduler> selectByExample(AdminSchedulerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    AdminScheduler selectByPrimaryKey(Long schedulerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByExampleSelective(@Param("record") AdminScheduler record, @Param("example") AdminSchedulerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByExample(@Param("record") AdminScheduler record, @Param("example") AdminSchedulerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByPrimaryKeySelective(AdminScheduler record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_SCHEDULER
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByPrimaryKey(AdminScheduler record);
}