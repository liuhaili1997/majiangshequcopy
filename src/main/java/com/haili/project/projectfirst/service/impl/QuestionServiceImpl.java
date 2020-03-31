package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.Question;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.QuestionService;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public PageInformationDto list(Integer currentPage, Integer pageSize) {
        //获取分页的真实数据
        PageInformationDto pageInformationDto = new PageInformationDto();
        //分页数据上传
        Integer total = questionMapper.questionTotal();
        //获取总页数
        Integer totalPage;
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

        Integer offSize = pageSize * (currentPage - 1);
        List<Question> questions = questionMapper.list(offSize, pageSize);
        if (CollectionUtils.isEmpty(questions)) {
            return new PageInformationDto();
        }
        //获取对象集合中的某一个属性生成一个集合
        List<Integer> creatorList = questions.stream().map(Question::getCreator).distinct().collect(Collectors.toList());
        List<User> userList = userMapper.findByIdList(creatorList);
        //通过id获取图片地址
        Map<Integer, String> avatarMap = new HashMap<>(10);
        if (!CollectionUtils.isEmpty(userList)) {
            //以后防止key值相同
            //avatarMap = userList.stream().collect(Collectors.toMap(User::getId, User::getAvatar, (oldValue, newValue)->newValue));
            for (User user : userList) {
                avatarMap.put(user.getId(), user.getAvatar());
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

        pageInformationDto.setQuestionDtoList(questionDtoList);

        return pageInformationDto;
    }

}
