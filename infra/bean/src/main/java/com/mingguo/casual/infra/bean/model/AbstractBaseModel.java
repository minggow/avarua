package com.mingguo.casual.infra.bean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by mingguo.wu on 15/9/29.
 */
@Data
public abstract class AbstractBaseModel implements Serializable {
	//unique id
	protected Integer id;
	//flag,not real delete
	protected Boolean isDelete;
	//create time
	protected Date createTime;
	//update time
	protected Date updateTime;
}
