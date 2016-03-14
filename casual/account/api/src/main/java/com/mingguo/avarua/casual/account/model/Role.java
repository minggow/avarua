package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;
import lombok.Data;

import java.util.List;

/**
 * 角色类
 * Created by mingguo.wu on 2015/9/15.
 */
@Data
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

}
