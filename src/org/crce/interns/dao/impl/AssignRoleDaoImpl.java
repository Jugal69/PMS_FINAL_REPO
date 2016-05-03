package org.crce.interns.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.crce.interns.dao.AssignRoleDao;
import org.crce.interns.model.FunctionMaster;
import org.crce.interns.model.FunctionRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("assignRoleDao")
public class AssignRoleDaoImpl implements AssignRoleDao{
	 @Resource(name="sessionFactory")
     protected SessionFactory sessionFactory;

     public void setSessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
     }
    
     protected Session getSession(){
            return sessionFactory.openSession();
     }

     @Transactional
     public void save(FunctionMaster fm,FunctionRole fr,int a) {
    	 Session session = sessionFactory.getCurrentSession();
    	 if(a==-1)
    	 {
    		 session.save(fm);
    		 fr.setFunctionId(fm.getFunctionId());
    	 }
    	 else
    		 fr.setFunctionId(a);
    	 
    	 session.save(fr);
     }
     @Transactional
     public int checkFunction(String function)
     {
    	 Session session = sessionFactory.openSession();
    	 int functionId=-1;
    	 String SQL_QUERY ="from FunctionMaster as f where f.functionName=?";
    	 Query query = session.createQuery(SQL_QUERY);
 		query.setParameter(0,function);
 		List list = query.list();
 		session.close();
 		if(!list.isEmpty())
 		{
 			FunctionMaster f=(FunctionMaster)list.get(0);
 			functionId=f.getFunctionId();
 		}
 			return functionId;
 			
     }
}
