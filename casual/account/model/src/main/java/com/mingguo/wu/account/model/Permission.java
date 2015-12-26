package com.mingguo.wu.account.model;

import com.mingguo.wu.infra.model.AbstractBaseModel;

/**
 * 权限类
 * Created by mingguo.wu on 2015/9/15.
 */
public class Permission extends AbstractBaseModel{
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 资源总数
     */
    private Integer resourceCount;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(Integer resourceCount) {
        this.resourceCount = resourceCount;
    }
}
