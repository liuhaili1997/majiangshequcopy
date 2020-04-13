package com.haili.project.projectfirst.advice;

import com.alibaba.fastjson.JSON;
import com.haili.project.projectfirst.dto.ResultDto;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理 捕获
 * @author Created by hailitortoise on 2020-04-08
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Throwable e, Model model) {
        /**
         * 返回的是当前也页面的状态，http请求状态码
         */
        HttpStatus status = getStatus(request);
        ResultDto resultDto;
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //格式对就返回json格式的信息
            if (e instanceof CustomizeException) {
                model.addAttribute("errorinfomation", e.getMessage());
                resultDto = ResultDto.errorOf((CustomizeException) e);
            } else {
                model.addAttribute("errorinfomation", "我已乏，跪安吧！！！");
                resultDto = ResultDto.errorOf(CustomizeErrorEnums.SYSTEM_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            //错误页面的跳转操作
            if (e instanceof CustomizeException) {
                model.addAttribute("errorinfomation", e.getMessage());
            } else {
                model.addAttribute("errorinfomation", CustomizeErrorEnums.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
