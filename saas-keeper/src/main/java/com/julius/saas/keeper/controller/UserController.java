package com.julius.saas.keeper.controller;

import com.julius.saas.common.entity.User;
import com.julius.saas.common.exception.SaasException;
import com.julius.saas.common.pojo.BaseResponse;
import com.julius.saas.keeper.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author julius zhou
 * @date 5/31/22 4:58 PM
 * <p>
 *      用户控制器
 * </p>
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 新增用户信息
     * @param user 用户信息
     * @return 响应
     * @throws SaasException 异常
     */
    @PostMapping("/insert")
    public BaseResponse<Integer> insert(@RequestBody User user) throws SaasException {
        int insert = userService.insert(user);
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        baseResponse.setBody(insert);
        return baseResponse;
    }


    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 响应
     * @throws SaasException 异常
     */
    @PutMapping("/update")
    public BaseResponse<Integer> update(@RequestBody User user) throws SaasException {
        int update = userService.update(user);
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        baseResponse.setBody(update);
        return baseResponse;
    }

    /**
     * 根据用户id 删除
     * @param id 用户id
     * @return 结果
     * @throws SaasException 异常
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deleteById(@PathVariable Integer id) throws SaasException {
        userService.deleteById(id);
        return new BaseResponse<>();
    }



}
