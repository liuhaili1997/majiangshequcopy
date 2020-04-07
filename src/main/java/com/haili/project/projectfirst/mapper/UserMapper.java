package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 通过id集合获取用户信息
     * @param idList id集合
     * @return 用户集合信息
     */
    List<User> findByIdList(@Param("idList") List<String> idList);

    /**
     * 通过id集合获取用户信息
     * @param accountId id集合
     * @return 用户集合信息
     */
    @Select("select * from user where account_id = #{accountId} limit 0, 1")
    User findByAccountId(@Param("accountId") String accountId);

    /**
     * 更新表中已有的数据
     * @param user 对象
     */
    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar = #{avatar} where account_id = #{accountId}")
    void updateUser(User user);

    /**
     * 如果是传输过来对象，则自动根据 #{gmtModified} 内部的值来找对应的get方法
     * 如果是单独的值 @Param("accountId") String accountId 则通过value来找对应的值
     * 无论传输过来的是对象还是变量：要和其保持一致，才能找到对应的值
     * */
}
