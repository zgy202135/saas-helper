package com.julius.saas.keeper.interceptor;

import com.julius.saas.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author julius zhou
 * @date 5/30/22 10:11 PM
 * <p>
 *      认证拦截器
 * </p>
 **/
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private TokenUtil tokenUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 认证用户token
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //避免内存泄漏
        LoginUserHolder.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //避免内存泄漏
        LoginUserHolder.clear();
    }
}
