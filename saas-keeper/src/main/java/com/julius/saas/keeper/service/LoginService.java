package com.julius.saas.keeper.service;

import cn.hutool.core.util.StrUtil;
import com.julius.saas.common.entity.User;
import com.julius.saas.common.enums.From;
import com.julius.saas.common.exception.AuthenticationException;
import com.julius.saas.common.exception.SaasException;
import com.julius.saas.common.pojo.Login;
import com.julius.saas.keeper.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author julius zhou
 * @date 5/31/22 5:24 PM
 * <p>
 *      登录服务接口
 * </p>
 **/
@Slf4j
@Service
public class LoginService implements InitializingBean {

    @Resource
    private UserService userService;

    @Resource
    private TokenUtil tokenUtil;

    /**
     * 登录策略初始化 {@link #afterPropertiesSet()}
     */
    private Map<From, Function<Login, String>> loginStrategy;







    @Override
    public void afterPropertiesSet() {

        Map<From,Function<Login,String>> map = new HashMap<>(1 << 1);
        map.put(From.B,new BLogin());
        loginStrategy = Collections.unmodifiableMap(map);
    }


    /**
     * B 端登录
     */
    private final class BLogin implements Function<Login,String> {


        @Override
        public String apply(Login login) {
            final String password = login.getPassword();
            final String userName = login.getUserName();
            if (StringUtils.isAnyBlank(password,userName)){
                throw new SaasException(SaasException.PRARAM_FAIL,"参数错误");
            }

            User user = userService.selectByName(userName);

            final String userPassword = user.getPassword();

            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            boolean matches = encoder.matches(password, userPassword);
            if(!matches){
                throw new SaasException(SaasException.PRARAM_FAIL,StrUtil.format("用户[{}]密码错误",userName));
            }
            Optional<String> optional = tokenUtil.createToken(userName, password,login.getFrom(),user.getRole());
            if (!optional.isPresent()){
                throw new AuthenticationException(AuthenticationException.AUTH_FAIL,"登录失败");
            }
            return optional.get();
        }
    }
}
