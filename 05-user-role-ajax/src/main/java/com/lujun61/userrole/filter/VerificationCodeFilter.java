package com.lujun61.userrole.filter;

import com.alibaba.druid.util.StringUtils;
import com.lujun61.userrole.common.MyFailureHandler;
import com.lujun61.userrole.exception.VerificationException;
import com.lujun61.userrole.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class VerificationCodeFilter extends OncePerRequestFilter {

    @Resource
    private MyFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI: " + requestURI);

        if ("/login".equals(requestURI)) {
            try {
                // 验证码验证规则：
                // 如果未抛出异常则放行请求，继续使用SpringSecurity验证用户名以及密码
                verifyCaptcha(request);
                filterChain.doFilter(request, response);
            } catch (VerificationException e) {
                // 如果抛出异常，则不放行请求，并将错误信息进行回馈
                e.printStackTrace();
                Result result = new Result();
                result.setCode(1);
                result.setError(1002);
                result.setMsg("验证码错误！");
                failureHandler.setResult(result);
                failureHandler.onAuthenticationFailure(request, response, e);
            }
        } else {
            // 非 /login 请求直接放行
            filterChain.doFilter(request, response);
        }
    }

    private void verifyCaptcha(HttpServletRequest request) throws VerificationException {
        String codeParam = request.getParameter("code");
        HttpSession session = request.getSession();

        String codeSession = (String) session.getAttribute("code");
        // 根据拦截器的执行顺序：可以得知
        // 如果用户发起请求之前，session中就拥有数据。则这个数据是因为用户的上次登录所记录的。
        // 这个数据是无效的！！！需要手动进行删除。
        //if(StringUtils.isEmpty(codeSession)) {
        //    session.removeAttribute("code");
        //}

        System.out.println("codeParam: " + codeParam + "; codeSession: " + codeSession);

        if(StringUtils.isEmpty(codeParam) || StringUtils.isEmpty(codeSession) || !codeSession.equals(codeParam)) {
            throw new VerificationException();
        }
    }
}
