/*
*
*
* Author Name: Crystal Cuthinho	
* 
* Filename: AddUserDao.java	
* 
* Description: This dao is an interface which only declares method names which are implemented in AddUserDaoImpl.java
* 
* Functions: loadCopyFile()	
*
*/



package org.crce.interns.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.web.bind.annotation.RequestParam;

public interface AddUserDao {


	public void loadCopyFile(String tableName,String timeStamp,String userName) throws SQLException, IOException, ParseException;

}
