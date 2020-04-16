package com.haili.project.projectfirst.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 使用云存储，实现文件的上传，下载，图片处理
 * @author Created by hailitortoise on 2020-04-16
 */
@Service
public class UcloudProvider {

    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;

    @Value("${ucloud.ufile.bucket-name}")
    private String bucketName;

    @Value("${ucloud.ufile.region}")
    private String region;

    @Value("${ucloud.ufile.proxy-suffix}")
    private String proxySuffix;

    @Value("${ucloud.ufile.expires-duration}")
    private Integer expiresDuration;

    /**
     * 对象操作需要ObjectConfig来配置您的地区和域名后缀
     * */
    ObjectConfig config = new ObjectConfig(region, proxySuffix);

    /**
     * putObject put一个file上传的，spring可以拿到流但是拿不到file  重载可以有流的方式
     * 同步上传文件
     *
     * @param fileStream 流
     * @param mimeType 是图片还是文字
     * @param fileName 为了防止重名和其他的操作
     * @return
     */
    public String upload(InputStream fileStream, String mimeType,String fileName){
        File file = new File("your file path");

        String generateFileName;
        /*\. 这是正则的转义的点*/
        String[] fileSplit = fileName.split("\\.");
        if (fileSplit.length > 1) {
            generateFileName = UUID.randomUUID() + "." + fileSplit[fileSplit.length - 1];
        } else {
            throw new CustomizeException(CustomizeErrorEnums.FILE_UPLOAD_FAIL);
        }


        try {
            /**
             * Bucket相关API的授权器，spring在实例化对象的时候才会做初始化变量null 我们需要赋值，就需要放到方法中
             * */
            ObjectAuthorization objectLocalAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
            PutObjectResultBean response = UfileClient.object(objectLocalAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generateFileName)
                    .toBucket(bucketName)
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener((bytesWritten, contentLength) -> {

                    }).execute();
                    //设置key的有效期为一天 24 * 60 * 60

            if (null != null && response.getRetCode() == 0) {
                String url = UfileClient.object(objectLocalAuthorization, config)
                        .getDownloadUrlFromPrivateBucket(generateFileName, bucketName, expiresDuration)
                        .createUrl();
                return url;
            } else {
                throw new CustomizeException(CustomizeErrorEnums.FILE_UPLOAD_FAIL);
            }

        } catch (UfileClientException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorEnums.FILE_UPLOAD_FAIL);
        } catch (UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorEnums.FILE_UPLOAD_FAIL);
        }

    }



}
