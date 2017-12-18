package com.yunengzhe.PVMTS.i18n;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yunengzhe.PVMTS.i18n.ResourceDataManager;

public class I18n extends TagSupport {

	private static final long serialVersionUID = 4592227792813891321L;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String setname(String name){
		String local = (String)com.yunengzhe.commons.authentication.HttpSessionUtil.getAttribute("local");
		Map<String, String> localMap = ResourceDataManager.getLocalMap(local);//参数
		return localMap.get(name);
	}
	@Override
	public int doStartTag() throws JspException {
		String local = (String)com.yunengzhe.commons.authentication.HttpSessionUtil.getAttribute("local");
		Map<String, String> localMap = ResourceDataManager.getLocalMap(local);//参数

		String string = localMap.get(name);
		pageContext.setAttribute(name, string);
		setName(string);
		JspWriter out = pageContext.getOut();
		String privilege_role = this.name;
		try {
			out.print(privilege_role);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TagSupport.EVAL_PAGE;
	}
}
