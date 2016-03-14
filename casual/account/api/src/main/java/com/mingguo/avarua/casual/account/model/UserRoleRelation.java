package com.mingguo.avarua.casual.account.model;

import com.mingguo.casual.infra.bean.model.AbstractBaseModel;
import lombok.Data;

/**
 * 用户和角色对应关系类
 * Created by wumingguo on 2015/9/21.
 */
@Data
public class UserRoleRelation extends AbstractBaseModel{

    /**
     * 用户对应的Id
     */
    private Integer userId;
    /**
     * 角色对应的Id
     */
    private Integer roleId;

}
