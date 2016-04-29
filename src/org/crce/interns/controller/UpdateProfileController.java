/**
 * @author Nevil Dsouza
 *
 *	Main controller
 *	DEPENDENCIES: ProfileServiceImpl, Profile, ProfileDAOImpl
 */
package org.crce.interns.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crce.interns.beans.PersonalProfileBean;
import org.crce.interns.beans.ProfessionalProfileBean;
import org.crce.interns.beans.UserDetailsBean;
import org.crce.interns.service.CheckRoleService;
import org.crce.interns.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateProfileController {
	
	
	//private ProfileService profileService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private CheckRoleService crService;
	/*
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index() {
		
		System.out.println("Inside Controller");
		
		ModelAndView model=null;
		
		model = new ModelAndView("index");
				
		return model;
	}
	*/
//------------------------------------------------------------------------------------------------------ //
	
	/**	GET details from database
	*	
	*	Creates hardcoded user by setName and setID on user.This value is supposed to be retrieved from Session.
	*	This enhancement will be added later
	*	@param	None
	*	@return MOdelAndView viewprofile page with name
	*
	*/
	
	
	
	@RequestMapping(value="/viewprofile", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,@ModelAttribute("u")String id) {
		
		System.out.println("Inside Controller");
		HttpSession session=request.getSession();
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("UpdateProfile", roleId))
			return new ModelAndView("403");
		else
		{
			ModelAndView model=null;

		///*
			UserDetailsBean userDetailsBean= new UserDetailsBean();
			userDetailsBean.setUserName(id);
			ProfessionalProfileBean professionalProfileBean=new ProfessionalProfileBean();
			PersonalProfileBean personalProfileBean=new PersonalProfileBean();
		
		
			userDetailsBean.setUserName(id);
		//professionalProfileBean.setUserName(id);
			professionalProfileBean.setUserName(id);
			personalProfileBean.setUserName(id);
		
		
			userDetailsBean = profileService.getProfile(userDetailsBean);
			professionalProfileBean = profileService.getProfile(professionalProfileBean);
			personalProfileBean = profileService.getProfile(personalProfileBean);	
		
			model = new ModelAndView("Student");
		
			model.addObject("userDetails",userDetailsBean);
			model.addObject("professionalProfile",professionalProfileBean);
			model.addObject("personalProfile",personalProfileBean);
		//*/
			return model;
		}
	}
	
	
		
//-----------------------------------------------------------------------------------------//
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editProfessionalProfile(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("userName");
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("UpdateProfile", roleId))
			return new ModelAndView("403");
		else
		{
			System.out.println("Inside Controller");
		
			ModelAndView model=null;		

			UserDetailsBean userDetailsBean= new UserDetailsBean();
			ProfessionalProfileBean professionalProfileBean=new ProfessionalProfileBean();
			PersonalProfileBean personalProfileBean=new PersonalProfileBean();
		
			userDetailsBean.setUserName(id);
			professionalProfileBean.setUserName(id);
			personalProfileBean.setUserName(id);

			userDetailsBean = profileService.getProfile(userDetailsBean);
			professionalProfileBean = profileService.getProfile(professionalProfileBean);
			personalProfileBean = profileService.getProfile(personalProfileBean);
		
		
			model = new ModelAndView("viewprofile");		
			model.addObject("userDetails",userDetailsBean);
			model.addObject("professionalProfile",professionalProfileBean);
			model.addObject("personalProfile",personalProfileBean);

		
			return model;
		}
	}
	
	
	
	
//------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updateProfessionalProfile(
			@RequestParam Map<String, String> r,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Inside Controller");
	
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("userName");
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("UpdateProfile", roleId))
			return new ModelAndView("403");
		else
		{
		ModelAndView model=null;
		
		ProfessionalProfileBean professionalProfileBean = new ProfessionalProfileBean();
		UserDetailsBean userDetailsBean=new UserDetailsBean();
		PersonalProfileBean personalProfileBean=new PersonalProfileBean();
		
		professionalProfileBean.setUserName(id);
		userDetailsBean.setUserName(id);		
		personalProfileBean.setUserName(id);
		
		professionalProfileBean = profileService.getProfile(professionalProfileBean);
		personalProfileBean = profileService.getProfile(personalProfileBean);
		// set values from Map to Bean
		professionalProfileBean.setFieldOfInterest(r.get("fieldOfInterest"));
		professionalProfileBean.setAchievements(r.get("achievements"));
		professionalProfileBean.setProjects(r.get("projects"));		
		professionalProfileBean.setInternships(r.get("internships"));
		professionalProfileBean.setCoCurricularActivities(r.get("coCurricularActivities"));		
		professionalProfileBean.setExtraCurricularActivities(r.get("extraCurricularActivities"));
		
		personalProfileBean.setCorrespondenceAddress(r.get("correspondenceAddress"));
		personalProfileBean.setPermanentAddress(r.get("permanentAddress"));		
		
		userDetailsBean = profileService.getProfile(userDetailsBean);		
		personalProfileBean = profileService.updatePersonalProfile(personalProfileBean);			
		professionalProfileBean = profileService.updateProfessionalProfile(professionalProfileBean);				
		
		model = new ModelAndView("Student");
		model.addObject("userDetails",userDetailsBean);
		model.addObject("professionalProfile",professionalProfileBean);
		model.addObject("personalProfile",personalProfileBean);			
		
		return model;
		}
	}
	
	
	
	
	
	
	
	//-----------------------------------------------------------------------------------------//
	

	/*
	@RequestMapping(value="/update-personal", method = RequestMethod.POST)
	public ModelAndView updatePersonalProfile(
			@RequestParam Map<String, String> r,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Inside Controller");
	
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("userName");
		ModelAndView model=null;
		
		ProfessionalProfileBean professionalProfileBean=new ProfessionalProfileBean();		
		UserDetailsBean userDetailsBean= new UserDetailsBean();
		PersonalProfileBean personalProfileBean=new PersonalProfileBean();
		
		personalProfileBean.setUserName(id);
		userDetailsBean.setUserName(id);		
		professionalProfileBean.setUserName(id);
		
		personalProfileBean = profileService.getProfile(personalProfileBean);
		//set values from Map to Bean
		
		
		
		
		userDetailsBean = profileService.getProfile(userDetailsBean);
		professionalProfileBean = profileService.getProfile(professionalProfileBean);
		personalProfileBean = profileService.updatePersonalProfile(personalProfileBean);
				
		model = new ModelAndView("Student");
		model.addObject("userDetails",userDetailsBean);
		model.addObject("professionalProfile",professionalProfileBean);
		model.addObject("personalProfile",personalProfileBean);			
		
		return model;
	}
	*/
	//-----------------------------------------------------------------------------------------//

	@RequestMapping(value="/update-password", method = RequestMethod.POST)
	public ModelAndView updateUserDetails(@RequestParam("username") String username,@RequestParam("password") String password) {
		
		System.out.println("Inside Controller");
	
		ModelAndView model=null;
		
		
		UserDetailsBean userDetailsBean= new UserDetailsBean();
		ProfessionalProfileBean professionalProfileBean=new ProfessionalProfileBean();
		PersonalProfileBean personalProfileBean=new PersonalProfileBean();
		
		
		
		
		userDetailsBean.setUserName(username);
		
		professionalProfileBean.setUserName(username);
		personalProfileBean.setUserName(username);
		
		
		userDetailsBean = profileService.getProfile(userDetailsBean);
		professionalProfileBean = profileService.getProfile(professionalProfileBean);
		personalProfileBean = profileService.getProfile(personalProfileBean);
		
		
		
		userDetailsBean = profileService.updateUserDetails(userDetailsBean);
		
			
		
		model = new ModelAndView("viewprofile");
		model.addObject("change",true);
		model.addObject("userDetails",userDetailsBean);
		model.addObject("professionalProfile",professionalProfileBean);
		model.addObject("personalProfile",personalProfileBean);			
		
		return model;
	}
	
	
	// extra
	@RequestMapping(value="/nevz-feedback", method = RequestMethod.GET)
	public ModelAndView f(HttpServletRequest request) {
		
		System.out.println("Inside Controller");
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("userName");
		String roleId=(String)session.getAttribute("roleId");
		if(!crService.checkRole("UpdateProfile", roleId))
			return new ModelAndView("403");
		else
		{
		ModelAndView model=null;
		
		model = new ModelAndView("FeedbackForm");
				
		return model;
		}
	}
	@RequestMapping(value="/nevz-feedbacks", method = RequestMethod.GET)
	public ModelAndView fs(HttpServletRequest request) {
		
		System.out.println("Inside Controller");
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("userName");
		String roleId=(String)session.getAttribute("roleId");
		
		if(!crService.checkRole("UpdateProfile", roleId))
			return new ModelAndView("403");
		else
		{
		ModelAndView model=null;
		
		model = new ModelAndView("feedbacks");
				
		return model;
		}
	}
}


/*

*/