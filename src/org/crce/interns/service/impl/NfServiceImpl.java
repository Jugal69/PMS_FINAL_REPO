/**
 * @author Nevil Dsouza ZNevzz
 *	Description : Service impl for notification related operations
 *	DEPENDENCIES: 
 * 	brans and model-	PersonalProfileBean;ProfessionalProfileBean;UserDetailsBean;NotificationBean;
 * 	DAO-	NfDAO
 */
package org.crce.interns.service.impl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.crce.interns.beans.NotificationBean;
import org.crce.interns.beans.PersonalProfileBean;
import org.crce.interns.beans.ProfessionalProfileBean;
import org.crce.interns.beans.UserDetailsBean;
import org.crce.interns.dao.NfDAO;
import org.crce.interns.model.Notification;
import org.crce.interns.service.NfService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("NfService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NfServiceImpl implements NfService{
	
	
	@Autowired
	private NfDAO nfDAO;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void checkNf() {
		System.out.print("inside Nf service");
		nfDAO.checkNf();
		nfDAO.displayXmlDatabaseInConsole();
		
	}
	
	
	/*@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void checkNf() {
		System.out.print("inside Nf service");
		nfDAO.checkNf();
		nfDAO.displayXmlDatabaseInConsole();
		
	}*/
	
	//-----------------------------------------------------------------------------------------------------------
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<NotificationBean> getNf
	(UserDetailsBean userDetailsBean,ProfessionalProfileBean professionalProfileBean,
			PersonalProfileBean personalProfileBean){
		
		List<Notification> nfList= new LinkedList<Notification>();
		List<NotificationBean> nfBeanList= new LinkedList<NotificationBean>();

		nfList = nfDAO.getNotificationDatabase().getNotifications();
		
		// filteration FOR LOOP
		for(Notification i: nfList){

			NotificationBean temp = new NotificationBean();			
			BeanUtils.copyProperties(i, temp);
			
			//BeanUtils.copyProperties(source, target);
			
			// ALL USERS
			if(temp.getType().equals("ALL")){
				nfBeanList.add(temp);
			}
			
			// PARTICULAR USER
			else if(temp.getType().equals("USER")){
				if(temp.getUserOrGroupId().equals(userDetailsBean.getUserName())){
					nfBeanList.add(temp);
				
				}
			}
			
			
			// USER GROUP
			else if(temp.getType().equals("GROUP")){
				
				// USER BRANCH
				if(temp.getUserOrGroupId().equals(professionalProfileBean.getBranch())){
					nfBeanList.add(temp);
					
				}
				
				// USER ROLE
				else if(temp.getUserOrGroupId().equals(userDetailsBean.getRoleId())){
					nfBeanList.add(temp);
					
				}
				
			}
			
			
			//nfBeanList.add(temp);
		}
		
		

		
//		System.out.println(nfList.size());

		return nfBeanList; 
	}
	
	//-----------------------------------------------------------------------------------------------------------
	public List<NotificationBean> sortByDate(List<NotificationBean> dateUnSorted){
		
		
		
		
		Collections.sort(dateUnSorted, new Comparator<NotificationBean>() {
	        
		 	DateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
	        @Override
	        public int compare(NotificationBean n1, NotificationBean n2) {
	            try {	            		            
	            			
	                return f.parse(n1.getDateTime()).compareTo(f.parse(n2.getDateTime()));
	            } catch (ParseException e) {
	                throw new IllegalArgumentException(e);
	            }
	        }
	    });
		
		
		return dateUnSorted;
		
	}
	//-----------------------------------------------------------------------------------------------------------
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addNotification(NotificationBean n){
		
		Notification temp = new Notification();
		BeanUtils.copyProperties(n, temp);
		
		if(nfDAO.addNotification(temp)){
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	
	
}
