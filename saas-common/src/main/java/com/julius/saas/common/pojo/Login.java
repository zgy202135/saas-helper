package com.julius.saas.common.pojo;

import com.julius.saas.common.enums.From;
import lombok.Data;

import java.io.Serializable;

/**
 * @author julius zhou
 * @date 5/31/22 5:15 PM
 * <p>
 *      登录信息实体
 * </p>
 **/
@Data
public class Login implements Serializable {
    private static final long serialVersionUID = 2598690541695847173L;


    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String password;

    /**
     * 用户来源
     * @see From
     */
    private From from;
}
