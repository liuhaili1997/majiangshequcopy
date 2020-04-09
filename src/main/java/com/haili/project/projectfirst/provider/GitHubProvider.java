package com.haili.project.projectfirst.provider;

import com.alibaba.fastjson.JSON;
import com.haili.project.projectfirst.dto.AccessTokenDto;
import com.haili.project.projectfirst.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 提供的请求访问，对code access_token，state等进行判断
 * @COntroller 将注解下的类作为路由api的承载者
 * @Component 仅仅是把当前的类初始化到spring容器的上下文，加了注解之后不需要new(实例化队形)IOC
 *
 * @author lhl
 * @date 2020-03-20
 */
@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //resolve java.net.SocketTimeoutException: timeout 增加到60s
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
        //建议使用get获取token，post请求无法找到页面，出现404 直接这样使用get是成功的
        String url = new StringBuilder("https://github.com/login/oauth/access_token?client_id=").append(accessTokenDto.getClientId())
                .append("&client_secret=").append(accessTokenDto.getClientSecret()).append("&code=").append(accessTokenDto.getCode())
                .append("&redirect_uri=").append(accessTokenDto.getRedirectUri()).toString();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.newCall(request).cancel();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
