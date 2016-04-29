package org.crce.interns.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_function_map",catalog="role_function_schema")
public class FunctionRole implements Serializable{
	@Id
	@Column(name = "role_id" )
	String roleId;
	@Id
	@Column(name = "function_id" )
	String functionId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	
}
