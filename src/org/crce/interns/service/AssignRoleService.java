package org.crce.interns.service;

import org.crce.interns.model.FunctionMaster;
import org.crce.interns.model.FunctionRole;

public interface AssignRoleService {
	public void assignRole(FunctionMaster fm,FunctionRole fr,int a);
	public int checkFunction(String s);
}
