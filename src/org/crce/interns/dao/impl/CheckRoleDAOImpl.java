package org.crce.interns.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.crce.interns.dao.CheckRoleDAO;
import org.crce.interns.model.FunctionRole;
import org.crce.interns.model.RoleMaster;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
@Repository("checkRoleDAO")
public class CheckRoleDAOImpl implements CheckRoleDAO{
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
           return sessionFactory.openSession();
    }

	public boolean checkRole(String functionName,String roleId) {
		
		System.out.println("In Check Role");
		
		Session session = sessionFactory.openSession();
		
		if(roleId==null)
			return false;
		
		//Query using Hibernate Query Language
		String SQL_QUERY ="from FunctionRole as f where f.roleId=? and f.functionId=(select u.functionId from FunctionMaster as u where u.functionName=?)";
		
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(1,functionName);
		query.setParameter(0, roleId);
		
		
		List list = query.list();
		
		session.close();
		if(list.isEmpty())
			return false;
		else 
			return true;
		

		
	}
	

}
