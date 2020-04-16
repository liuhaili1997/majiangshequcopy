package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.dto.QuestionQueryDto;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.mapper.QuestionExtendMapper;
import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.Question;
import com.haili.project.projectfirst.model.QuestionExample;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.model.UserExample;
import com.haili.project.projectfirst.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 注解@Service 用于管理整个实现类
 * @author Created by hailitortoise on 2020-03-26
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    @Override
    public PageInformationDto list(String accountId, Integer currentPage, Integer pageSize, String search) {
        //搜索实现
        if (StringUtils.isNotBlank(search)) {
            String[] searchs = StringUtils.split(search, " ");
            search = Arrays.stream(searchs).collect(Collectors.joining("|"));
        }

        //获取分页的真实数据
        PageInformationDto<QuestionDto> pageInformationDto = new PageInformationDto<>();
        //分页数据上传
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria()
                .andCreatorEqualTo(accountId);
        QuestionQueryDto questionQueryDto = new QuestionQueryDto();
        questionQueryDto.setSearch(search);
        Integer total = questionExtendMapper.countByExample(questionQueryDto);
        //获取总页数
        int totalPage;
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        pageInformationDto.setTotalPage(totalPage);
        //健壮性
        if (currentPage < 1) {
            currentPage = 1;
        }
        //totalPage 总页数
        if (currentPage > totalPage) {
            currentPage =  totalPage;
        }
        pageInformationDto.setPageInformation(total, currentPage, pageSize);

        int offSize = pageSize * (currentPage - 1);
        questionQueryDto.setCurrantPage(offSize);
        questionQueryDto.setPageSize(pageSize);
        List<Question> questions = questionExtendMapper.selectBySearch(questionQueryDto);
        if (CollectionUtils.isEmpty(questions)) {
            return new PageInformationDto<>();
        }
        //获取对象集合中的某一个属性生成一个集合
        List<String> creatorList = questions.stream().map(Question::getCreator).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdIn(creatorList);
        List<User> userList = userMapper.selectByExample(userExample);
        //通过id获取图片地址
        Map<String, String> avatarMap = new HashMap<>(10);
        if (!CollectionUtils.isEmpty(userList)) {
            //以后防止key值相同
            //avatarMap = userList.stream().collect(Collectors.toMap(User::getId, User::getAvatar, (oldValue, newValue)->newValue));
            for (User user : userList) {
                avatarMap.put(user.getAccountId(), user.getAvatar());
            }
        }
        QuestionDto questionDto;
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions) {
            questionDto = new QuestionDto();
            //相同元素直接赋值 名称 数据类型
            BeanUtils.copyProperties(question, questionDto);
            String avatarUrl = avatarMap.get(question.getCreator());
            if (StringUtils.isNotBlank(avatarUrl)) {
                questionDto.setAvatarUrl(avatarUrl);
            }
            questionDtoList.add(questionDto);
        }

        pageInformationDto.setData(questionDtoList);

        return pageInformationDto;
    }

    @Override
    public PageInformationDto listById(String accountId, Integer currentPage, Integer pageSize) {

        //获取分页的真实数据
        PageInformationDto<QuestionDto> pageInformationDto = new PageInformationDto<>();
        //分页数据上传
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(accountId);
        Integer total = (int)questionMapper.countByExample(example);
        //获取总页数
        int totalPage;
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        pageInformationDto.setTotalPage(totalPage);
        //健壮性
        if (currentPage < 1) {
            currentPage = 1;
        }
        //totalPage 总页数
        if (currentPage > totalPage) {
            currentPage =  totalPage;
        }
        pageInformationDto.setPageInformation(total, currentPage, pageSize);

        int offSize = pageSize * (currentPage - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(accountId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(offSize, pageSize));
        if (CollectionUtils.isEmpty(questions)) {
            return new PageInformationDto<>();
        }
        //获取对象集合中的某一个属性生成一个集合
        List<String> creatorList = questions.stream().map(Question::getCreator).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdIn(creatorList);
        List<User> userList = userMapper.selectByExample(userExample);
        //通过id获取图片地址
        Map<String, String> avatarMap = new HashMap<>(10);
        if (!CollectionUtils.isEmpty(userList)) {
            //以后防止key值相同
            //avatarMap = userList.stream().collect(Collectors.toMap(User::getId, User::getAvatar, (oldValue, newValue)->newValue));
            for (User user : userList) {
                avatarMap.put(user.getAccountId(), user.getAvatar());
            }
        }
        QuestionDto questionDto;
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions) {
            questionDto = new QuestionDto();
            //相同元素直接赋值 名称 数据类型
            BeanUtils.copyProperties(question, questionDto);
            String avatarUrl = avatarMap.get(question.getCreator());
            if (StringUtils.isNotBlank(avatarUrl)) {
                questionDto.setAvatarUrl(avatarUrl);
            }
            questionDtoList.add(questionDto);
        }

        pageInformationDto.setData(questionDtoList);

        return pageInformationDto;
    }

    @Override
    public QuestionDto getById(Long id) {
        QuestionDto questionDto = new QuestionDto();
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorEnums.QUESTION_RECORD_NOT_IN_TABLE);
        }
        BeanUtils.copyProperties(question, questionDto);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(questionDto.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        if (!CollectionUtils.isEmpty(users)) {
            questionDto.setUser(users.get(0));
        }
        return questionDto;
    }

    @Override
    public void createOrUpdateQuestion(Question question) {
        Long currentTime = System.currentTimeMillis();
        if (question.getId() == null) {
            //创建新的记录
            question.setGmtCreate(currentTime);
            question.setGmtModified(currentTime);
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        } else {
            //更新新的记录
            question.setGmtModified(currentTime);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updateStatus = questionMapper.updateByExampleSelective(question, questionExample);
            if (updateStatus != 1) {
                throw new CustomizeException(CustomizeErrorEnums.QUESTION_NOT_FOUND);
            }
        }
    }

    @Override
    public void incViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        //新增浏览的数量
        question.setViewCount(1);
        questionExtendMapper.incViewCount(question);
    }

    @Override
    public List<QuestionDto> selectRelated(QuestionDto query) {
        String tag = query.getTag();
        if (StringUtils.isBlank(tag)) {
            return new ArrayList<>();
        }
        //通过插件将字符串拆分开来，然后添加  | 才可以用于正则表达式的查询
        String[] tags = StringUtils.split(tag, ",");
        //Arrays.stream 可以对数组进行流处理 添加字符用于连接两个不同的元素并转换为字符串
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        //replace String.join("|", tags)
        Question question = new Question();
        question.setId(query.getId());
        question.setTag(regexpTag);

        //通过自己定义方法来实现查询
        List<Question> questionList = questionExtendMapper.selectRelated(question);
        List<QuestionDto> questionDtoList = questionList.stream().map(q -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(q, questionDto);
            return questionDto;
        }).collect(Collectors.toList());
        return questionDtoList;
    }

}
