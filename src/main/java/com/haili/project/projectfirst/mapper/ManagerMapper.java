package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.model.Manager;
import com.haili.project.projectfirst.model.ManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    long countByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int deleteByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int insert(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int insertSelective(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    List<Manager> selectByExampleWithRowbounds(ManagerExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    List<Manager> selectByExample(ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    Manager selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MANAGER
     *
     * @mbg.generated Wed Jun 03 22:28:29 CST 2020
     */
    int updateByPrimaryKey(Manager record);
}