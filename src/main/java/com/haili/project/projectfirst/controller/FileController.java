package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.FileDto;
import com.haili.project.projectfirst.provider.UcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 选择图片时显示文件夹
 * 现在存储图片的方式：
 * 1.通过上传以后直接创建一个tmp目录或固定目录，把图片放到目录中，再通过ngkx或者本地tomcat映射一个地址往前端
 * 返回 == 非常依赖与主机迁移  在不同的主机上运行要迁移各种数据资源【不常用】
 * 2.把图片资源直接转换成二进制的文件存到数据库中，等到渲染到页面的时候直接序列化 == 成本高  本身数据库就有索引，
 * 各种存储机制和优化机制，就是为了方便优化数据的 ，现在却把其当作图片或文件存储的资源，大材小用，不是很方便
 * 3.云存储：各种云服务都会有这种云存储，很方便
 *
 * 云平台：
 * ALibaba：528￥
 * Tencent：600￥
 * China Telecom：800￥
 * Ucloud：文件存储时20G，超出则收费
 *
 * @author Created by hailitortoise on 2020-04-15
 */
@Controller
public class FileController {

    @Autowired
    private UcloudProvider ucloudProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDto upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //input 本地文件的上传name=editormd-image-file
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            String fileName = ucloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDto fileDto = new FileDto();
            fileDto.setSuccess(1);
            fileDto.setUrl(fileName);
            return fileDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl("/images/wechat.png");
        return fileDto;
    }
}
