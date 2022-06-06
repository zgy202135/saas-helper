package com.julius.saas.keeper.controller;

import com.julius.saas.keeper.service.LoginService;
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




}
