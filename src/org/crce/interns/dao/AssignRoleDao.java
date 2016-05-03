package org.crce.interns.dao;

import org.crce.interns.model.FunctionMaster;
import org.crce.interns.model.FunctionRole;

public interface AssignRoleDao {
	public void save(FunctionMaster fm,FunctionRole fr,int a);
	public int checkFunction(String function);
}
