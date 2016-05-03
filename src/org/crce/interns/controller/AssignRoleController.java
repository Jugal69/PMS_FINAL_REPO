package org.crce.interns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.crce.interns.beans.AssignRoleBean;
import org.crce.interns.model.FunctionMaster;
import org.crce.interns.model.FunctionRole;
import org.crce.interns.service.AssignRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AssignRoleController {
	@Autowired
	public AssignRoleService ars;
	
	@RequestMapping(value="/AssignRole", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("return model");
		AssignRoleBean assignRole=new AssignRoleBean();
		ModelAndView model=null;
		model=new ModelAndView("AssignRole");
		model.addObject("assignRole",assignRole);
		return model;
	}
	
	@RequestMapping(value="/roleAssigned")
	public ModelAndView assignRole(HttpServletRequest request, HttpServletResponse response, @Valid AssignRoleBean arb,BindingResult result) {
		System.out.println("return model");
		if (result.hasErrors())
		{
			ModelAndView model=new ModelAndView("AssignRole");
			model.addObject("assignRole",arb);
			return model;
		}
		FunctionMaster fm=new FunctionMaster();
		FunctionRole fr=new FunctionRole();
		fm.setFunctionName(arb.getFunctionName());
		fm.setFunctionUrl(arb.getFunctionURL());
		fr.setRoleId(arb.getRoleId());
		int a=ars.checkFunction(arb.getFunctionName());
		
		ars.assignRole(fm, fr,a);
		System.out.println("Success");
		return new ModelAndView("roleAssigned");
	}
}
