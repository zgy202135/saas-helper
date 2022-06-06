package com.julius.saas.common.pojo;

import cn.hutool.http.HttpStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author julius zhou
 * @date 5/31/22 4:29 PM
 * <p>
 *      基本响应信息
 * </p>
 **/
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 176661752564907774L;


    /**
     * 成功描述
     */
    private static final String SUCCESS = "成功";

    /**
     * 响应码
     */
    private int code = HttpStatus.HTTP_OK;

    /**
     * 响应描述
     */
    private String message = SUCCESS;


    /**
     * 响应体
     */
    private T body;


}
