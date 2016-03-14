package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;
import lombok.Data;

/**
 * 角色权限对应关系
 * Created by mingguo.wu on 2015/10/8.
 */
@Data
public class RolePermissionRelation extends AbstractBaseModel{
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;

}
