package org.crce.interns.service.impl;

import org.crce.interns.dao.GetBranchDao;
import org.crce.interns.service.GetBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("getBranchService")
public class GetBranchServiceImpl implements GetBranchService{
	 @Autowired
	 private GetBranchDao gbDao;

	   public void setLoginDAO(GetBranchDao gbDao) {
              this.gbDao = gbDao;
       }
	public String getBranch(String userName){
        
		   System.out.println("In Service class...Check Login");
        
		   return gbDao.getBranch(userName);
	   }
}
