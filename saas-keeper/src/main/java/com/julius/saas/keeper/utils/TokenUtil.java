package com.julius.saas.keeper.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.julius.saas.common.enums.From;
import com.julius.saas.common.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.Optional;

/**
 * @author julius zhou
 * @date 6/2/22 9:22 AM
 * <p>
 *      jwt web token 工具
 * </p>
 **/
@Slf4j
@Component
public class TokenUtil {

    /**
     * token过期时间，默认1天
     */
    private static final long TOKEN_EXPIRES_TIME = 24 * 60 * 60 * 1000;

    /**
     * 创建token
     * @param userName 用户名
     * @param password  密码
     * @param from 用户来源
     * @param role 用户角色 角色 0-管理员，1-开发者
     * @return token
     */
    public Optional<String> createToken(String userName, String password,From from,int role){

        String token = JWT.create()
                //签发时间
                .withIssuedAt(new Date())
                //用户名
                .withClaim("userName", userName)
                //用户来源
                .withClaim("from", from.name())
                //用户角色
                .withClaim("role", role)
                //过期时间
                .withExpiresAt(DateUtil.offsetMillisecond(new Date(), (int) TOKEN_EXPIRES_TIME))
                //使用密码加密
                .sign(Algorithm.HMAC256(DigestUtils.md5Digest(password.getBytes())));

        return Optional.ofNullable(token);
    }


    /**
     * 认证token
     * @param token token
     * @param password 密码
     * @return 认证结果
     */
    public void checkToken(String token,String password){
        final DecodedJWT decode = JWT.decode(token);

        final String userName = decode.getClaim("userName").asString();

        final String from = decode.getClaim("from").asString();

        final int role = decode.getClaim("role").asInt();

        //检查token是否过期
        final Date expiresAt = decode.getExpiresAt();

        if(DateUtil.compare(expiresAt,new Date()) <= 0){
            throw new AuthenticationException(AuthenticationException.AUTH_FAIL,"Token已过期");
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(DigestUtils.md5Digest(password.getBytes())))
                //用户名
                .withClaim("userName", userName)
                //用户来源
                .withClaim("from", from)
                //用户角色
                .withClaim("role", role)
                .build();
        try{
            verifier.verify(token);
        }catch (JWTVerificationException e){
            log.error("Token验证失败，{}",e.getMessage(),e);
            throw new AuthenticationException(AuthenticationException.AUTH_FAIL,"Token验证失败");
        }
    }



}
