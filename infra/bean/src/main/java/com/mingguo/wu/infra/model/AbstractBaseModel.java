package com.mingguo.wu.infra.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by mingguo.wu on 15/9/29.
 */
public abstract class AbstractBaseModel implements Serializable {
	//unique id
	protected Integer id;
	//flag,not real delete
	protected Boolean isDelete;
	//create time
	protected Date createTime;
	//update time
	protected Date updateTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
