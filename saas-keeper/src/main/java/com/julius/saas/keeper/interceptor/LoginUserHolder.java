package com.julius.saas.keeper.interceptor;

import com.julius.saas.common.enums.From;
import com.julius.saas.common.exception.AuthenticationException;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

/**
 * @author julius zhou
 * @date 5/30/22 10:17 PM
 * <p>
 *      登录用户包装类
 * </p>
 **/
public final class LoginUserHolder {

    /**
     * 当前用户的threadLocal
     */
    private static final ThreadLocal<LoginUserHolder> CUR_USER = new ThreadLocal<>();


    /**
     * 设置用户信息
     * @param user 用户信息
     */
    public static void set(LoginUserHolder user){
        if (Objects.isNull(user)){
            throw new AuthenticationException(AuthenticationException.AUTH_FAIL,"用户认证失败");
        }
        CUR_USER.set(user);
    }

    /**
     * 安全获取当前用户信息
     * @return 用户信息
     */
    public static LoginUserHolder getSelf(){
        final LoginUserHolder loginUserHolder = CUR_USER.get();
        if (Objects.isNull(loginUserHolder)){
            throw new AuthenticationException(AuthenticationException.AUTH_FAIL,"用户认证失败");
        }
        return loginUserHolder;
    }

    public static void clear(){
        CUR_USER.remove();
    }

    /**
     * 用户id
     */
    @Setter
    @Getter
    private Integer userId;

    /**
     * 用户名
     */
    @Setter
    @Getter
    private String userName;

    /**
     * 管理员权限 true-管理员 ，false-开发者
     */
    @Setter
    @Getter
    private Boolean admin;

    /**
     * 用户来源
     * @see From
     */
    @Setter
    @Getter
    private From from;


}
