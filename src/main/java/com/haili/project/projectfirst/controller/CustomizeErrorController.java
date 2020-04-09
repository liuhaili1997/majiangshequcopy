package com.haili.project.projectfirst.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * 对没有捕获的异常也需要格式化显示
 * @author Created by hailitortoise on 2020-04-08
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = this.getStatus(request);
        /**
         * 400: 客户端引起的
         * 500：服务端引起
         */
        if (status.is4xxClientError()) {
            model.addAttribute("errorinfomation","这好像是客户端的问题，我需要想想！！");
        }
        if (status.is5xxServerError()) {
            model.addAttribute("errorinfomation", "这好像是服务端的问题，别催，我正在解决");
        }
        return  new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}
