package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author Created by hailitortoise on 2020-03-25
 */
@Mapper
public interface QuestionMapper {

    /**
     * 插入问题数据
     * @param question 接受数据的实体类
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    /**
     * 查询问题,数据量不是很大，查的是整张数据库的表
     *
     * @param offSize  分页查询的起始数
     * @param pageSize 偏移量
     * @return 数据集合
     */
    @Select("select * from question limit #{offSize}, #{pageSize}")
    List<Question> list(@Param("offSize") Integer offSize, @Param("pageSize") Integer pageSize);

    /**
     * 查询Question表的总数
     * @return 数量
     */
    @Select("select count(1) from question")
    Integer questionTotal();

    /**
     * 查询属于当前用户写的问题
     *
     * @param accountId          id
     * @param currentPage 当前页数
     * @param pageSize    页尺寸
     * @return 查询结果
     */
    @Select("select * from question where creator = #{id} limit #{currentPage}, #{pageSize}")
    List<Question> listByCreator(@Param("id") String accountId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 根据user中account_id查询相关的问题数据
     * @param id id
     * @return 值
     */
    @Select("select count(1) from question where creator = #{id}")
    Integer questionTotalBy(@Param("id") String id);

    /**
     * 据question的id查询信息
     * @param id id
     * @return 返回question对象信息
     */
    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);
}
