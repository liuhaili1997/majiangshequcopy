package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.GitHubUserDto;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.enums.ManageTypeEnum;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.mapper.CommentMapper;
import com.haili.project.projectfirst.mapper.ManagerMapper;
import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.*;
import com.haili.project.projectfirst.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 功能描述: 实现类
 *
 * @Author: liuhaili
 * @Date: 2020-06-2, 周二, 19:42
 */
@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long getSumOfQuestion() {
        //全表查总数
        long sum = 0L;
        sum= questionMapper.countByExample(new QuestionExample());
        return sum;
    }

    @Override
    public Long getQuestionsOfToday() {
        long count = 0L;
        //数据库中存的时间是毫秒
        long current=System.currentTimeMillis();    //当前时间毫秒数
        long zeroT=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
        //格式化时间
        //String zero = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zeroT);
        long endT=zeroT+24*60*60*1000-1;  //今天23点59分59秒的毫秒数
        QuestionExample questionExample = new QuestionExample();
        //查询当天的是今天创建的就算是今天的，修改的不算
        questionExample.createCriteria()
                .andGmtCreateBetween(zeroT, endT);
        count = questionMapper.countByExample(questionExample);
        return count;
    }

    @Override
    public Long getSumOfUser() {
        //这里包括 GitHub授权的 本地的
        long sum = 0L;
        //GitHub授权的用户
        sum += userMapper.countByExample(new UserExample());
        //本地的用户，包括管理员
        sum += managerMapper.countByExample(new ManagerExample());
        return sum;
    }

    @Override
    public List<GitHubUserDto> getListOfUser() {
        GitHubUserDto gitHubUserDto;
        List<GitHubUserDto> data = new ArrayList<>();
        //全表查询 表不可能为空
        UserExample userExample = new UserExample();
        userExample.createCriteria();
        List<User> users = userMapper.selectByExample(userExample);
        List<String> accountId = users.stream().map(User::getAccountId).collect(Collectors.toList());
        Map<String,Long> publishCountList = new HashMap<>();
        //不应该把查询数据放在循环中做操作
        for (String p : accountId) {
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andCreatorEqualTo(p);
            long publishCount = questionMapper.countByExample(questionExample);
            publishCountList.put(p, publishCount);
        }
        Map<String,Long> commentCountList = new HashMap<>();
        for (String p : accountId) {
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria()
                    .andCommentatorEqualTo(p);
            long commentCount = commentMapper.countByExample(commentExample);
            commentCountList.put(p, commentCount);
        }
        for (User user : users) {
            gitHubUserDto = new GitHubUserDto();
            gitHubUserDto.setId(user.getId());
            gitHubUserDto.setName(user.getName());
            String account = user.getAccountId();
            gitHubUserDto.setAccountId(account);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getGmtCreate());
            gitHubUserDto.setRegisterTime(time);
            gitHubUserDto.setType("Github授权用户");
            gitHubUserDto.setOperator("删除");
            Long publishCount = publishCountList.get(account);
            if (null == publishCount) {
                gitHubUserDto.setPublishCount(0L);
            } else {
                gitHubUserDto.setPublishCount(publishCount);
            }
            Long commentCount = commentCountList.get(account);
            if (null == commentCount) {
                gitHubUserDto.setCommentCount(0L);
            } else {
                gitHubUserDto.setCommentCount(commentCount);
            }
            data.add(gitHubUserDto);
        }
        return data;
    }

    @Override
    public Map<Integer, List<GitHubUserDto>> getListOfManage() {
        GitHubUserDto gitHubUserDto;
        List<GitHubUserDto> data = new ArrayList<>();
        List<GitHubUserDto> managerList = new ArrayList<>();
        //全表查询 表不可能为空
        List<Manager> manage = managerMapper.selectByExample(new ManagerExample());
        List<String> accountId = manage.stream().map(Manager::getAccountId).collect(Collectors.toList());
        Map<String,Long> publishCountList = new HashMap<>();
        //不应该把查询数据放在循环中做操作
        for (String p : accountId) {
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andCreatorEqualTo(p);
            long publishCount = questionMapper.countByExample(questionExample);
            publishCountList.put(p, publishCount);
        }
        Map<String,Long> commentCountList = new HashMap<>();
        for (String p : accountId) {
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria()
                    .andCommentatorEqualTo(p);
            long commentCount = commentMapper.countByExample(commentExample);
            commentCountList.put(p, commentCount);
        }
        for (Manager manager: manage) {
            gitHubUserDto = new GitHubUserDto();
            gitHubUserDto.setId(manager.getId());
            gitHubUserDto.setName(manager.getName());
            String account = manager.getAccountId();
            gitHubUserDto.setAccountId(account);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(manager.getGmtCreate());
            gitHubUserDto.setRegisterTime(time);
            ManageTypeEnum manageTypeEnum = ManageTypeEnum.getEnums((int)manager.getType());
            if (null != manageTypeEnum) {
                gitHubUserDto.setType(manageTypeEnum.getMessage());
            }
            gitHubUserDto.setOperator("删除");
            Long publishCount = publishCountList.get(account);
            if (null == publishCount) {
                gitHubUserDto.setPublishCount(0L);
            } else {
                gitHubUserDto.setPublishCount(publishCount);
            }
            Long commentCount = commentCountList.get(account);
            if (null == commentCount) {
                gitHubUserDto.setCommentCount(0L);
            } else {
                gitHubUserDto.setCommentCount(commentCount);
            }
            if ("管理员".equals(gitHubUserDto.getType())) {
                data.add(gitHubUserDto);
            } else {
                managerList.add(gitHubUserDto);
            }
        }
        /*1: 表示管理员 2：表示普通用户*/
        Map<Integer, List<GitHubUserDto>> record = new HashMap<>();
        record.put(1, data);
        record.put(2, managerList);
        return record;
    }

    @Override
    public Integer deleteUser(Long id) {
        if (null == id) {
            return 0;
        }
        User user = userMapper.selectByPrimaryKey(id);
        if (null == user) {
            Manager manager = managerMapper.selectByPrimaryKey(id);
            if (null != manager) {
                managerMapper.deleteByPrimaryKey(id);
            } else {
                throw new CustomizeException(CustomizeErrorEnums.USER_NOT_FOUND);
            }
        } else {
            userMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }
}
