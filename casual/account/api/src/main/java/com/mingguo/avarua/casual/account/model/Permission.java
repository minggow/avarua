package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;
import lombok.Data;

/**
 * 权限类
 * Created by mingguo.wu on 2015/9/15.
 */
@Data
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
}
