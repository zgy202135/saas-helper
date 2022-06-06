package com.julius.saas.keeper.service;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.julius.saas.common.entity.User;
import com.julius.saas.common.exception.SaasException;
import com.julius.saas.keeper.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
/**
 * @author julius zhou
 * @date 5/30/22 9:56 PM
 * <p>
 *      用户服务层接口实现<br/>
 *      整合redis实现用户缓存<br/>
 * </p>
 **/
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    @Resource
    private UserMapper userMapper;


    /**
     *  新增用户信息
     * @param user 用户信息
     * @return 1-代表成功，其他代表失败，异常也代表失败
     * @throws SaasException 异常
     */
    public int insert(User user) throws SaasException {
        final String userName = user.getUserName();

        final String userPassword = user.getPassword();

        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(User::getUserName,userName);

        User selectOne = userMapper.selectOne(wrapper);

        if (Objects.nonNull(selectOne)){
            throw new SaasException(SaasException.PRARAM_FAIL, StrUtil.format("当前用户[{}]已经存在",userName));
        }
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final String password = encoder.encode(userPassword);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);

        if (insert == 1){
            return insert;
        }
        throw new SaasException(SaasException.SYSTEM_FAIL,StrUtil.format("用户[{}]新增失败",userName));
    }


    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 1-代表成功，其他代表失败
     * @throws SaasException 异常
     */
    public int update(User user) throws SaasException {
        user.setUpdateTime(new Date());

        int update = userMapper.updateById(user);

        if (update == 1){
            return update;
        }

        throw new SaasException(SaasException.SYSTEM_FAIL,StrUtil.format("更新[{}]新增失败",user.getUserName()));
    }

    /**
     * 根据用户id删除用户 逻辑删除
     * @param id 用户id
     * @throws SaasException 异常
     */
    public void deleteById(int id) throws SaasException {

        User user = super.getById(id);

        if (Objects.isNull(user)){
            throw new SaasException(SaasException.PRARAM_FAIL,StrUtil.format("用户[{}]不存在",id));
        }

        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(User::getId,id);
        updateWrapper.set(User::getDeleteFlag,1);

        boolean update = super.update(updateWrapper);

        if (!update){
            throw new SaasException(SaasException.SYSTEM_FAIL,StrUtil.format("用户[{}]删除失败",id));
        }

    }

    /**
     * 根据用户名查询用户信息
     * @param name 用户名
     * @return 用户信息
     * @throws SaasException 异常
     */
    public User selectByName(String name) throws SaasException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(User::getUserName,name);

        User user = userMapper.selectOne(wrapper);

        if (Objects.isNull(user)){
            throw new SaasException(SaasException.PRARAM_FAIL,StrUtil.format("用户[{}]不存在",name));
        }

        return user;
    }

}
