package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;

/**
 * 用户和角色对应关系类
 * Created by wumingguo on 2015/9/21.
 */
public class UserRoleRelation extends AbstractBaseModel{

    /**
     * 用户对应的Id
     */
    private Integer userId;
    /**
     * 角色对应的Id
     */
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
