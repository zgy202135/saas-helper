package com.julius.saas.keeper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.julius.saas.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author julius zhou
 * @date 5/30/22 9:48 PM
 * <p>
 *      用户持久层接口
 * </p>
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
