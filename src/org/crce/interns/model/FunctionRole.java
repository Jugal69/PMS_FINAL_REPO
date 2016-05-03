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
	private String roleId;
	@Id
	@Column(name = "function_id" )
	private Integer functionId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	
	
}
