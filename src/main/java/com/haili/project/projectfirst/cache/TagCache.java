package com.haili.project.projectfirst.cache;

import com.haili.project.projectfirst.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签缓存
 * @author Created by hailitortoise on 2020-04-14
 */
public class TagCache {
    public static List<TagDto> get() {
        List<TagDto> tagDtoList = new ArrayList<>();
        TagDto emotion = new TagDto();
        emotion.setCategoryName("大学生活");
        emotion.setTags(Arrays.asList("情感","办理证件流程","自学的培养","沟通的技巧","如何交友","人性的弱点","考试介绍"));
        tagDtoList.add(emotion);

        TagDto program = new TagDto();
        program.setCategoryName("计算机语言");
        program.setTags(Arrays.asList("JavaScript","Java","Spring boot","HTML","C++","css","C#","C"));
        tagDtoList.add(program);

        TagDto book = new TagDto();
        book.setCategoryName("相关问题");
        book.setTags(Arrays.asList("专业性问题","计算机程序异常","代码问题","英语学习","相关书籍的推荐","面试总结"));
        tagDtoList.add(book);
        return tagDtoList;
    }

    public static String filterInValid(String value) {
        String[] split = StringUtils.split(value, ",");
        List<TagDto> tagDtos = get();
        //flatMap 将集合中的集合取出来 将二维数组拍平，得到内部的集合，但是有三个集合啊，取得是那个
        List<String> tagList = tagDtos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        //!tagList.contains(t) 这个表是前端传输过来的tag，有不是内部的定义的标签，则直接返回报错
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
