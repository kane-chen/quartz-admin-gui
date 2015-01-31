package cn.kane.quartz.admin.mapper;

import cn.kane.quartz.admin.domain.AdminJob;
import cn.kane.quartz.admin.domain.AdminJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int countByExample(AdminJobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int deleteByExample(AdminJobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int deleteByPrimaryKey(Long jobid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int insert(AdminJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int insertSelective(AdminJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    List<AdminJob> selectByExample(AdminJobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    AdminJob selectByPrimaryKey(Long jobid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByExampleSelective(@Param("record") AdminJob record, @Param("example") AdminJobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByExample(@Param("record") AdminJob record, @Param("example") AdminJobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByPrimaryKeySelective(AdminJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUARTZ_ADMIN_JOB
     *
     * @mbggenerated Wed Jan 21 14:33:05 CST 2015
     */
    int updateByPrimaryKey(AdminJob record);
}