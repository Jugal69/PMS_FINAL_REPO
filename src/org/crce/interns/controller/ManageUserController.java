package org.crce.interns.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.crce.interns.beans.FacultyBean;
import org.crce.interns.beans.StudentBean;
import org.crce.interns.model.Student;
import org.crce.interns.service.CheckRoleService;
import org.crce.interns.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;







@Controller
public class ManageUserController {

	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private CheckRoleService crService;
	/*@RequestMapping("/")
	public ModelAndView welcome() {
				return new ModelAndView("index");
	}*/
	
	
	//actually adding student
	@RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute("studentBean")StudentBean studentBean,BindingResult result) {
		manageUserService.addStudent(studentBean);
		return new ModelAndView("addStudent");
	}
	
	//actually adding faculty
	@RequestMapping(value = "/registerFaculty", method = RequestMethod.POST)
	public ModelAndView addFaculty(@ModelAttribute("facultyBean")FacultyBean facultyBean,BindingResult result) {
		manageUserService.addFaculty(facultyBean);
		return new ModelAndView("addFaculty");
	}
	
	@RequestMapping(value = "/addstudent", method = RequestMethod.GET)
	public ModelAndView welcomeStudent(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("ManageUser", roleId))
			return new ModelAndView("403");
		else
		{
			StudentBean studentBean = new StudentBean(); // declaring

			model.addAttribute("studentBean", studentBean); // adding in model

			return new ModelAndView("addStudent");
		}
	}
	
	@RequestMapping(value = "/addfaculty", method = RequestMethod.GET)
	public ModelAndView welcomeFaculty(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("ManageUser", roleId))
			return new ModelAndView("403");
		else
		{
			FacultyBean facultyBean = new FacultyBean(); // declaring

			model.addAttribute("facultyBean", facultyBean); // adding in model
			return new ModelAndView("addFaculty");
		}
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/removeuser", method = RequestMethod.GET)
	public ModelAndView removeUser(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("ManageUser", roleId))
			return new ModelAndView("403");
		else
		return new ModelAndView("removeUser");		
	}
	
	//actually removing user
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	public ModelAndView removeUser1(@ModelAttribute("command")  StudentBean studentBean,
			BindingResult result, @RequestParam("username")String username) {
		manageUserService.removeUser(studentBean, username);
		return new ModelAndView("removeUser");
	}

}
