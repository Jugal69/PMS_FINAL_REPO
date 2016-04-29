package org.crce.interns.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.crce.interns.dao.GetBranchDao;
import org.crce.interns.model.ProfessionalProfile;
import org.crce.interns.model.SpecialRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
@Repository("getBranchDAO")
public class GetBranchDAOImpl implements GetBranchDao{
    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
           return sessionFactory.openSession();
    }
public String getBranch(String userName) {
		
		System.out.println("In Check Branch");
		
		Session session = sessionFactory.openSession();
		
		String branch="";
		
		//Query using Hibernate Query Language
		String SQL_QUERY ="from ProfessionalProfile as p where p.userName=?";
		
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,userName);
		
		
		List list = query.list();
		
		if  ( !(list.isEmpty())){
			ProfessionalProfile r=(ProfessionalProfile)list.get(0);
			branch=r.getBranch();
			
		}
		
		session.close();

		return branch;
	}
}
