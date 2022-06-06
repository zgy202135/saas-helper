package com.julius.saas.common.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author julius zhou
 * @date 5/30/22 9:12 PM
 * <p>
 *      用户信息实体
 * </p>
 **/
@Data
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -879494175700053009L;


    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;


    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色 0-管理员，1-开发者
     */
    private Integer role;

    /**
     * 删除标记 0-有效，1-删除
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
