package org.cronhub.managesystem.auth.dao;

import org.cronhub.managesystem.commons.dao.bean.AuthUser;

import java.util.List;

public interface UserDao {

	AuthUser getById(String userId);

	boolean insert(AuthUser authUser);

	List<AuthUser> findAll();

	boolean deleteById(String user_id);

//	void saveLoginLog(AuthLoginLog log);
//
//	void saveLogoutLog(AuthLoginLog log);
//
//	List<AuthLoginLog> findAllLoginLog();
}
