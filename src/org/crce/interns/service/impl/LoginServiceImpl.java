package org.crce.interns.service.impl;
import org.crce.interns.dao.*;
import org.crce.interns.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

	 @Autowired
	 private LoginDAO loginDAO;

	   public void setLoginDAO(LoginDAO loginDAO) {
              this.loginDAO = loginDAO;
       }
      
	/*Author:Khusaal
	Classes Used: LoginDAO.java
	Description: Calling LoginDAOImpl.java*/

	   public String checkLogin(String userName, String userPassword){
           
		   System.out.println("In Service class...Check Login");
           
		   return loginDAO.checkLogin(userName, userPassword);
	   }
	 
	/*Author:Khusaal
	Classes Used: LoginDAO.java
	Description: Calling LoginDAOImpl.java*/
  
	   public int getStudentByid(String userName)
		{
			System.out.println("In Service class...Get Student By ID");
			return loginDAO.getStudentById(userName);
		}

	/*Author:Khusaal
	Classes Used: LoginDAO.java
	Description: Calling LoginDAOImpl.java*/

		public boolean getNotification(String userName)
		{
			System.out.println("In Service class...Get Notification");
			return loginDAO.getNotification(userName);
		}

	/*Author:Khusaal
	Classes Used: LoginDAO.java
	Description: Calling LoginDAOImpl.java*/

		public String checkSR(String userName){
	           
			   System.out.println("In Service class...Check Login");
	           
			   return loginDAO.checkSpecialRole(userName);
		   }
	}