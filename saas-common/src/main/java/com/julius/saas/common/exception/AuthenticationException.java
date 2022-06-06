package com.julius.saas.common.exception;

/**
 * @author julius zhou
 * @date 5/30/22 6:48 PM
 * <p>
 *      认证异常
 * </p>
 **/
public class AuthenticationException extends SaasException {
    private static final long serialVersionUID = -6284959613396042316L;


    /**
     * 认证异常
     */
    public static final int AUTH_FAIL = 1 << 3;


    public AuthenticationException(int code) {
        super(code);
    }

    public AuthenticationException(int code, String message) {
        super(code, message);
    }

    public AuthenticationException(int code, String message, Throwable e) {
        super(code, message, e);
    }

    public AuthenticationException(int code, Throwable e) {
        super(code, e);
    }

    public AuthenticationException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(code, message, cause, enableSuppression, writableStackTrace);
    }
}
