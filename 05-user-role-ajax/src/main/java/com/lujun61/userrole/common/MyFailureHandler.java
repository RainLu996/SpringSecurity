package com.lujun61.userrole.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lujun61.userrole.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component("failureHandler")
public class MyFailureHandler implements AuthenticationFailureHandler {
    private Result result;

    public void setResult(Result result) {
        this.result = result;
    }

    /*
       参数：
         request : 请求对象
         response：应答对象
         e: spring security框架验证用户信息失败后的异常信息
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        //当框架验证用户信息失败时执行的方法
        response.setContentType("text/json;charset=utf-8");

        // 默认值
        if (result == null) {
            Result result = new Result();
            result.setCode(1);
            result.setError(1001);
            result.setMsg("登录失败");

            this.result = result;
        }

        OutputStream out = response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out, result);
        out.flush();
        out.close();

    }
}
