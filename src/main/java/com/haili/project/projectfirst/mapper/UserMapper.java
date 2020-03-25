package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用于用户的查询和删除
 * @author Created by hailitortoise on 2020-03-21
 */
@Mapper
public interface UserMapper {

    /**
     * 将登录用户的信息保存到数据库中
     *
     * @param user 用户信息实体类
     */
    @Insert("Insert into user (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    /**
     * 通过token查询用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
