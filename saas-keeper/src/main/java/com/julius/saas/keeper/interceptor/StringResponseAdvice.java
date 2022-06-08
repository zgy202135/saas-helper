package com.julius.saas.keeper.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.julius.saas.keeper.controller.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author julius zhou
 * @date 2022/6/8 22:36
 * <p>
 *
 * </p>
 **/
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = {LoginController.class})
public class StringResponseAdvice<Object> implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        //获取对象
        final String bodyStr = JSONObject.toJSONString(body);

        LoginUserHolder.User user = LoginUserHolder.getSelf();

        user.setResponseBody(bodyStr);

        LoginUserHolder.set(user);


        return body;
    }
}
