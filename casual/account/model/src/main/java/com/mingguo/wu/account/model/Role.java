package com.mingguo.wu.account.model;

import com.mingguo.wu.infra.model.AbstractBaseModel;

import java.util.List;

/**
 * 角色类
 * Created by mingguo.wu on 2015/9/15.
 */
public class Role extends AbstractBaseModel{
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 拥有的权限列表
     */
    private List<Permission> permissions;
    /**
     * 拥有的权限总数
     */
    private Integer permissionCount;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getPermissionCount() {
        return permissionCount;
    }

    public void setPermissionCount(Integer permissionCount) {
        this.permissionCount = permissionCount;
    }
}
