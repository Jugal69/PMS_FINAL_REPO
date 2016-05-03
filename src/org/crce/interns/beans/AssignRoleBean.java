package org.crce.interns.beans;

import org.hibernate.validator.constraints.NotEmpty;

public class AssignRoleBean {
	@NotEmpty
	private String functionName;
	@NotEmpty
	private String roleId;
	@NotEmpty
	private String functionURL;

	public String getFunctionURL() {
		return functionURL;
	}
	public void setFunctionURL(String functionURL) {
		this.functionURL = functionURL;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
