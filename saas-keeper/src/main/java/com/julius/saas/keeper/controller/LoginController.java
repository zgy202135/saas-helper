package com.julius.saas.keeper.controller;

import com.julius.saas.common.exception.SaasException;
import com.julius.saas.common.pojo.BaseResponse;
import com.julius.saas.common.pojo.Login;
import com.julius.saas.keeper.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author julius zhou
 * @date 5/31/22 5:13 PM
 * <p>
 *      登录控制器
 * </p>
 **/
@RestController
public class LoginController {


    @Resource
    private LoginService loginService;


    /**
     * 用户登录
     * @param login 登录信息
     * @return token
     * @throws SaasException 异常
     */
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody Login login)throws SaasException {
        String token = loginService.login(login);
        BaseResponse<String> response = new BaseResponse<>();
        response.setBody(token);
        return response;
    }

}
