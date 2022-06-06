package com.julius.saas.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author julius zhou
 * @date 5/30/22 6:28 PM
 * <p>
 *      Saas-helper系统顶级异常
 * </p>
 **/
public class SaasException extends RuntimeException {
    private static final long serialVersionUID = 1214041366314927559L;

    /**
     * 系统异常
     */
    public static final int SYSTEM_FAIL = 1;

    /**
     * 参数错误
     */
    public static final int PRARAM_FAIL = 1 << 1;


    /**
     * 错误码
     */
    @Setter
    @Getter
    private int code;


    /**
     * 异常描述
     */
    @Setter
    @Getter
    private String message;



    public SaasException(int code){
        super();
        this.code = code;
    }

    public SaasException(int code,String message){
        super(message);
        this.code = code;
    }

    public SaasException(int code,String message,Throwable e){
        super(message,e);
        this.code = code;
    }

    public SaasException(int code,Throwable e){
        super(e);
        this.code = code;
    }

    public SaasException(int code,String message, Throwable cause,
                         boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }


}
