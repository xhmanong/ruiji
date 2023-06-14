package com.ecust.filter;


import com.alibaba.fastjson.JSON;

import com.ecust.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 实现登陆拦截器
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("session是:{},",request.getSession().getAttribute(("employee")));
        log.info("session是:{},",request.getSession().getAttribute(("user")));

        String[] uris = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",//测试上传下载时使用的路径
                "/user/sendMsg", //发送验证码的请求的路径
                "/user/login"
        };
        boolean check1 = check(uris, requestURI);
        if (check1) {
            log.info("check方法被调用,放行");
            log.info("拦截的请求 {}",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getSession().getAttribute(("employee")) != null||request.getSession().getAttribute(("user")) != null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute(("employee")) ==null ||request.getSession().getAttribute(("user")) == null) {
            //如果未登录，通过输出流方式向客户端响应数据
            response.getWriter().write(JSON.toJSONString(R.error( "NOTLOGIN")));
            log.error("未登录,拦截页面 {}",requestURI);
        }
    }
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if (match){
                return  true;
            }
        }
        return false;
    }
}