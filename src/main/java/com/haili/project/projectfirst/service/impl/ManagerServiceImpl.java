package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.mapper.ManagerMapper;
import com.haili.project.projectfirst.model.Manager;
import com.haili.project.projectfirst.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 *
 * @author Created by hailitortoise on 2020-05-09
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Integer createNewRecord(Manager manager, MultipartFile file) {
        // 图片路径
        String imgUrl = null;
        String uploadDir = "C://Users//lhl03//Videos//git//repository//projectfirst//src//main//resources//static//images//avatar/";
        //生成token
        String token = UUID.randomUUID().toString();
        // 根据时间生成唯一的id
        Long stamp = System.currentTimeMillis() / 1000;
        String stampstr = stamp.toString();
        /**
         * Long.ValueOf("String")返回Long包装类型
         * Long.parseLong("String")返回long基本数据类型
         */
        Long accountId =Long.valueOf(stampstr.substring(1));
        manager.setAccountId(accountId.toString());
        manager.setGmtCreate(System.currentTimeMillis());
        manager.setGmtModified(System.currentTimeMillis());
        try {
            //上传
            String filename = upload(file, uploadDir, file.getOriginalFilename());
            if (StringUtils.isNotBlank(filename)) {
                imgUrl = new StringBuilder("/images/").append(new File(uploadDir).getName()).append("/").append(filename).toString();
            }
            manager.setAvatar(imgUrl);
            manager.setToken(token);
            managerMapper.insert(manager);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String upload(MultipartFile file, String path, String fileName) throws Exception {
        // 生成新的文件名
        String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdir();
        }
        //保存文件
        file.transferTo(dest);
        return dest.getName();
    }
}
