package org.cronhub.managesystem.auth.service;

import com.novell.ldap.LDAPConnection;
import org.cronhub.managesystem.auth.dao.UserDao;
import org.cronhub.managesystem.commons.dao.bean.AuthUser;
import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserService {
//	private final static String LDAP_URL = "ldap://10.11.156.63:389/";
//	private final static String LDAP_SUFFIX = "@sohu-inc.com";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private UserDao userDao;
	
	public boolean ldapCheck(String userName, String password) {
/*		if(StringUtils.hasText(userName)) {
			if(!userName.endsWith(LDAP_SUFFIX)) {
				userName += LDAP_SUFFIX;
			}
			
			DirContext ctx = null;
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, LDAP_URL);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);

			try {
				ctx = new InitialDirContext(env);
				//log.info("user " + userName + " LDAP login success.");
				return true;
			} catch (Exception e) {
			} finally {
				if (ctx != null) {
					try {
						ctx.close();
					} catch (NamingException e) {
						//log.error(e.getMessage(), e);
					}
				}
			}
		}
		return false;
*/

		if (StringUtils.hasText(userName) && StringUtils.hasText(password)) {
			LDAPConnection lc = new LDAPConnection();
			try {
				lc.connect("ad.op.dajie-inc.com", 389);
				lc.bind(LDAPConnection.LDAP_V3, "IM20-GROUP\\" + userName, password.getBytes("UTF-8"));
				return true;
			} catch (Exception e) {
				LOGGER.error("username or password is wrong! u:{},pwd:{}", userName, password);
			}
		}
		return false;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean exist(String userId) {
		AuthUser authUser = userDao.getById(userId);
		if(authUser == null)
			return false;
		else 
			return true;
	}

	public boolean add(AuthUser authUser) {
		return userDao.insert(authUser);
	}
	
//	public void addLoginLog(String user_id) {
//		AuthLoginLog log = new AuthLoginLog();
//		log.setUser_id(user_id);
//		log.setLogin_time(new Date());
//		userDao.saveLoginLog(log);
//	}
//
//	public void addLogoutLog(String user_id) {
//		AuthLoginLog log = new AuthLoginLog();
//		log.setUser_id(user_id);
//		log.setLogout_time(new Date());
//		userDao.saveLogoutLog(log);
//	}

	public List<AuthUser> findAll() {
		return userDao.findAll();
	}

	public boolean deleteById(String user_id) {
		return userDao.deleteById(user_id);
	}
	
	public AuthUser findById(String user_id) {
		return userDao.getById(user_id);
	}

//	public List<AuthLoginLog> findAllLoginLog() {
//		return userDao.findAllLoginLog();
//	}
}
