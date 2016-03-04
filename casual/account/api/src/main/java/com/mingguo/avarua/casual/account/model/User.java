package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户类
 * Created by mingguo.wu on 2015/9/15.
 */
public class User extends AbstractBaseModel {
    /**
     * 用户登录名称
     */
    private String loginName;
    /**
     * 用户登录密码
     */
    private String password;
    /**
     * 用户登陆后显示名称
     */
    private String displayName;
    /**
     * 用户描述
     */
    private String description;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 用户拥有的角色列表
     */
    private List<Role> roles = new ArrayList<Role>();
    /**
     * 用户拥有的权限列表
     */
    private List<Integer> permissionList = new ArrayList<Integer>();

    /**
     * 判断用户是否具备某一个权限
     * @param permissionID 权限对应的ID
     * @return 判断权限的结果
     */
    public boolean hasPermission(Integer permissionID) {
        if (permissionList.isEmpty()) {
            return false;
        }
        return permissionList.contains(permissionID);
    }

    /**
     * 判断用户是否具备权限列表中的某个权限
     * @param permissionIDList 权限ID列表
     * @return 判断权限的结果
     */
    public boolean hasPermission(List<Integer> permissionIDList) {
        if (permissionList.isEmpty()) {
            return false;
        }
        for (Integer permissionID : permissionIDList) {
            if (permissionList.contains(permissionID)) {
                return true;
            }
        }
        return false;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Integer> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Integer> permissionList) {
        this.permissionList = permissionList;
    }
}
