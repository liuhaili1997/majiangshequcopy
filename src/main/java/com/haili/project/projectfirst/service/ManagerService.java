package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.model.Manager;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理者的实现业务类
 * @author Created by hailitortoise on 2020-05-09
 */

public interface ManagerService {

    /**
     * 创建管理者的登录密码记录
     *
     * @param manager 管理者
     * @param file 图片路径
     * @return 处理状态
     */
    Integer createNewRecord(Manager manager, MultipartFile file);
}
