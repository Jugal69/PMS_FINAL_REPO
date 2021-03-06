/*
*
* Author Name: Crystal Cuthinho	
* 
* Filename: ManageUserServiceImpl.java	
* 	
* Classes used by code: ManageUserService, ManageUserDao,Faculty, Student
* 
* Tabes used: User_schema.userdetails,User_schema.personal_profile,User_schema.professional_profile,User_schema.qualification
* 
* Description: This service implementation is used to implement the methods in ManageUserService.java
* 
* Functions: addStudent(), addFaculty(), removeUser()
*
*/

package org.crce.interns.service.impl;

import org.crce.interns.beans.FacultyBean;
import org.crce.interns.beans.StudentBean;
import org.crce.interns.dao.ManageUserDao;
import org.crce.interns.model.Faculty;
import org.crce.interns.model.Student;
import org.crce.interns.service.ManageUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




@Service("manageUserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ManageUserServiceImpl implements ManageUserService {
	
	@Autowired
	private ManageUserDao manageUserDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addStudent(StudentBean studentBean) {
		
		Student student = new Student();
		
		//converts bean to model
		BeanUtils.copyProperties(studentBean, student);
		student.setRole_id("1");
		
		manageUserDao.createStudent(student);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addFaculty(FacultyBean facultyBean) {
		Faculty faculty = new Faculty();
		
		//converts bean to model
		BeanUtils.copyProperties(facultyBean, faculty);
		faculty.setRole_id("2");
		manageUserDao.createFaculty(faculty);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void removeUser(StudentBean studentBean, String username){
		
		Student student = new Student();
		//converts bean to model
		BeanUtils.copyProperties(studentBean, student);
		
		manageUserDao.deleteUser(student, username);
	}

}
