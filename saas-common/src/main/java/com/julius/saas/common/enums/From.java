package com.julius.saas.common.enums;

import lombok.Getter;

/**
 * 用户来源枚举类
 * @author zhougaoyang
 */
public enum From {

    /**
     * b端密码登录
     */
    B("b-pwd","b端密码登录"),
    /**
     * 钉钉登录
     */
    DINGTALK("ding-talk","钉钉登录");


    /**
     * 来源
     */
    @Getter
    private String from;

    /**
     * 描述
     */
    @Getter
    private String description;

    From(String from,String description){
        this.from = from;
        this.description = description;
    }

}
