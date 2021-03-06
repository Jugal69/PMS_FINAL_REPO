/**
 * @author Nevil Dsouza ZNevzz
 *	Description : Service interface for notification related operations
 *	DEPENDENCIES: 
 * 	brans and model-	PersonalProfileBean;ProfessionalProfileBean;UserDetailsBean;NotificationBean;
 */

package org.crce.interns.service;

import java.util.List;

import org.crce.interns.beans.NotificationBean;
import org.crce.interns.beans.PersonalProfileBean;
import org.crce.interns.beans.ProfessionalProfileBean;
import org.crce.interns.beans.UserDetailsBean;


public interface NfService {
	
	public void checkNf();
	
	public List<NotificationBean> getNf
	(UserDetailsBean userDetailsBean,ProfessionalProfileBean professionalProfileBean,
			PersonalProfileBean personalProfileBean);
	
	public List<NotificationBean> sortByDate(List<NotificationBean> dateUnSorted);
			
	public boolean addNotification(NotificationBean n);

}
