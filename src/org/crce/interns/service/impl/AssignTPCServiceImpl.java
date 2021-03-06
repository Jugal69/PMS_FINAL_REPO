/*
 * AssignTPCServiceImpl Class contains all logic related operations.
 * 
 * AssignTPCServiceImpl Class contains the implementations for various methods like 
 * 
 * Assign TPC(for STPC and FTPC)
 * Remove TPC(for STPC and FTPC)
 * Assign special task to FTPC
 * View Users and View Tasks
 * 
 * @author Adarsh
 * 
 * */
package org.crce.interns.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.crce.interns.beans.FacultyUserBean;
import org.crce.interns.beans.UserDetailsBean;
import org.crce.interns.dao.AssignTPCDao;
import org.crce.interns.model.FacultyUser;
import org.crce.interns.model.RMUser;
import org.crce.interns.model.UserDetails;
import org.crce.interns.service.AssignTPCService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignTPCServiceImpl implements AssignTPCService {
	@Autowired
	private AssignTPCDao assignTPCDao;

	/* Methods to Insert the data */
	@Override
	public int assignTPC(UserDetailsBean userBean) {
		UserDetails user = new UserDetails();
		UserDetails checkUser = new UserDetails();
		RMUser rmuser = new RMUser();

		String st;
		BeanUtils.copyProperties(userBean, user);
		checkUser.setUserName(userBean.getUserName());

		checkUser = assignTPCDao.getUser(checkUser);//Call to fetch User by his Username

		System.out.println("User Role ID from JSP : " + userBean.getRoleId() + "\n");
		String roleID = userBean.getRoleId();
		System.out.println(roleID);
		rmuser = assignTPCDao.getUserRole(roleID);//Call to fetch User by his RoleID

		System.out.println("User Id in RM Table: " + rmuser.getRole_id());
		System.out.println("User Role in RM Table: " + rmuser.getUserRole());

		if (checkUser == null) {
			System.out.println("Error:No User Defined" + "\n");
			return 0;
		}

		System.out.println("User Role ID from DB : " + checkUser.getRoleId() + "\n");
		/*
		 * 1-Student 2-Faculty 3-Student-TPC 4-Faculty-TPC
		 */
		st = userBean.getRoleId();
		if (checkUser.getRoleId().equalsIgnoreCase("3")||checkUser.getRoleId().equalsIgnoreCase("4")) {
			return 34;	//Return 34 if User is already a TPC
		}
		else if (st.equalsIgnoreCase("1")) {	//Check if User is a Student

			if (checkUser.getRoleId().equalsIgnoreCase(userBean.getRoleId())) {
				System.out.println("Before update Student Role ID : " + checkUser.getRoleId() + "\n");
				checkUser.setRoleId("3");	//Update RoleID to make him STPC
				System.out.println("After update Student Role ID : " + checkUser.getRoleId() + "\n");
				assignTPCDao.assignTPC(checkUser);
				return 1;	//Return 1 for normal execution
			} else {
				System.out.println("Invalid Input: Student" + "\n");
				return 3;	//Return 3 if not a Student
			}
		} else if (st.equalsIgnoreCase("2")) {	//Check if User is a Student
			System.out.println(userBean.getRoleId());
			if (checkUser.getRoleId().equalsIgnoreCase(userBean.getRoleId())) {
				System.out.println("Before update Faculty Role ID : " + checkUser.getRoleId() + "\n");
				checkUser.setRoleId("4");	//Update RoleID to make him FTPC
				System.out.println("After update Faculty Role ID : " + checkUser.getRoleId() + "\n");
				assignTPCDao.assignTPCFac(checkUser);
				return 1;	//Return 1 for normal execution
			} else {
				System.out.println("Invalid Input : Faculty" + "\n");
				return 4;	//Return 4 if not a Faculty
			}
		} else {
			System.out.println("Error : No Such User Exists");
			return 0;	//Return 0 if no such User exists
		}

	}

	@Override
	public int insertWork(FacultyUserBean fuserBean) {
		FacultyUser fuser = new FacultyUser();

		fuser.setUserName(fuserBean.getUserName());

		fuser = assignTPCDao.getFacultyUser(fuser);
		System.out.println(fuser);
		if (fuser == null) {
			System.out.println("Error: No such User Defined" + "\n");
			return 0;	//Return 0 if no such User exists
		}
		
		System.out.println("UserWorkk in Service with Bean: " + fuserBean.getUserWork());
		if(fuserBean.getUserWork().equalsIgnoreCase("01"))
		{
			fuser.setUserWork("PLACEMENT_REPORT");
		}
		if(fuserBean.getUserWork().equalsIgnoreCase("02"))
		{
			fuser.setUserWork("ROOM_ALLOTMENT");
		}
		if(fuserBean.getUserWork().equalsIgnoreCase("03"))
		{
			fuser.setUserWork("COUNSELLING_REPORT");
		}
		if(fuserBean.getUserWork().equalsIgnoreCase("04"))
		{
			fuser.setUserWork("FEEDBACK_REPORT");
		}
		System.out.println("Username in Service IMPL :" + fuser.getUserName());
		System.out.println("UserWork in Service IMPL :" + fuser.getUserWork());

		assignTPCDao.insertWork(fuser);
		return 1;	//Return 1 for normal execution
		
	}

	public List<UserDetailsBean> convertToBean(List<UserDetails> userList) {
		List<UserDetailsBean> userBeanList = new ArrayList<UserDetailsBean>();
		for (UserDetails user : userList) {
			UserDetailsBean userBean = new UserDetailsBean();
			BeanUtils.copyProperties(user, userBean);
			userBeanList.add(userBean);
		}
		return userBeanList;
	}

	public List<FacultyUserBean> convertToBeanFaculty(List<FacultyUser> userList) {
		List<FacultyUserBean> userBeanList = new ArrayList<FacultyUserBean>();
		for (FacultyUser fuser : userList) {
			FacultyUserBean fuserBean = new FacultyUserBean();
			BeanUtils.copyProperties(fuser, fuserBean);
			userBeanList.add(fuserBean);
		}
		return userBeanList;
	}

	@Override
	public int removeTPC(UserDetailsBean userBean) {
		// TODO Auto-generated method stub
		UserDetails user = new UserDetails();
		UserDetails checkUser = new UserDetails();
		BeanUtils.copyProperties(userBean, user);
		checkUser.setUserName(userBean.getUserName());

		checkUser = assignTPCDao.getUser(checkUser);

		if (checkUser == null) {
			System.out.println("Error: No User Defined" + "\n");
			return 0;	//Return 0 if no such User exists
		}

		if (checkUser.getRoleId().equalsIgnoreCase("3")) {
			System.out.println("Before update Student Role : " + checkUser.getRoleId() + "\n");
			checkUser.setRoleId("1");// 1 is Student & 3 is Student tpc
			System.out.println("After update Student Role : " + checkUser.getRoleId() + "\n");
			assignTPCDao.removeTPC(checkUser);
			return 1;	//Return 1 for normal execution
		} else if (checkUser.getRoleId().equalsIgnoreCase("4")) {
			System.out.println("Before update Faculty Role : " + checkUser.getRoleId() + "\n");
			checkUser.setRoleId("2");// 2 is faculty & 4 is Fac tpc
			System.out.println("After update Faculty Role : " + checkUser.getRoleId() + "\n");
			assignTPCDao.removeTPCFac(checkUser);
			return 1;	//Return 1 for normal execution
		}
		else {
			System.out.println("Service :Error (LE) : No Such User Exists Last Error");
			return 33;	//Return 33 if User is not a TPC
		}
	}

	@Override
	public List<UserDetailsBean> viewUsers() {
		// TODO Auto-generated method stub
		List<UserDetails> userList = assignTPCDao.viewUsers();
		return convertToBean(userList);
	}

	@Override
	public List<FacultyUserBean> viewFacultyTasks() {
		// TODO Auto-generated method stub
		List<FacultyUser> userList = assignTPCDao.viewFacultyTasks();
		return convertToBeanFaculty(userList);
	}

}
