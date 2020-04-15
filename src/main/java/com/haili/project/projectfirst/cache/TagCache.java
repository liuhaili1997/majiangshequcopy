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
        emotion.setCategoryName("情感");
        emotion.setTags(Arrays.asList("生活","感悟","爱情","悲伤","幸福","美好","未来","坚强"));
        tagDtoList.add(emotion);

        TagDto program = new TagDto();
        program.setCategoryName("语言");
        program.setTags(Arrays.asList("JavaScript","Java","PHP","node.js","typescript","css","python","c"));
        tagDtoList.add(program);

        TagDto book = new TagDto();
        book.setCategoryName("书籍");
        book.setTags(Arrays.asList("红楼梦","三国演义","出师表","登黄鹤楼","假如给我三天光明","三体","完美世界"));
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
