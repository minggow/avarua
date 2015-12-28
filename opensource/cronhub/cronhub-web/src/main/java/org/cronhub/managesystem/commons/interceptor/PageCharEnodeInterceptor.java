package org.cronhub.managesystem.commons.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

public class PageCharEnodeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		return  invocation.invoke();
	}
}
