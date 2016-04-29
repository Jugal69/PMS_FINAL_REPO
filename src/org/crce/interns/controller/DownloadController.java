package org.crce.interns.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crce.interns.service.CheckRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController extends HttpServlet{
	@Autowired
	private CheckRoleService crService;
	private String basePath = "C:\\Users\\Melwyn\\Desktop\\PMS";
	
	//The base path would be the root directory of all the folders
	
	private static final int BUFFER_SIZE = 4096;
	
	@RequestMapping("/downloadResume")
	public void downloadResume(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName")String fileName) {
		String userName = (String) request.getSession().getAttribute("userName");
		String role = getRole((String) request.getSession().getAttribute("roleId"));
		
		String fileToBeDownloaded = basePath + "\\" + role + "\\" + userName + "\\" + fileName;
		System.out.println(fileToBeDownloaded);
		
		
		ServletContext context = request.getServletContext();
		
		File downloadFile = new File(fileToBeDownloaded);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String mimeType = context.getMimeType(fileToBeDownloaded);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        
        OutputStream outStream = null;
        try {
        	byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
			outStream = response.getOutputStream();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    
	}
	
	@RequestMapping("/viewResumes")
	public ModelAndView viewFiles(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String) request.getSession().getAttribute("userName");
		String role = getRole((String) request.getSession().getAttribute("roleId"));
		String roleId=(String) request.getSession().getAttribute("roleId");
		if(!crService.checkRole("Download", roleId))
			return new ModelAndView("403");
		else
		{
			String directoryPath = basePath + "\\" + role + "\\" + userName;
			File directory = new File(directoryPath);
			File[] listOfFiles = directory.listFiles();
		
			System.out.println(directoryPath);
		
			List<String> fileList = new ArrayList<String>();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					System.out.println("FILE : " + file.getName());
					fileList.add(file.getName());
				} else 
				System.out.println("DIRECTORY : " + file.getName());
			}
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("fileList", fileList);
			return new ModelAndView("viewResumes", modelMap);
		}
	}
	
	public String getRole(String role) {
		if (role.equals("1"))
			return "Student";
		else if (role.equals("2"))
			return "Faculty";
		else if (role.equals("3"))
			return "StudentTPC";
		else if (role.equals("4"))
			return "FacultyTPC";
		else if (role.equals("5"))
			return "TPO";
		else if (role.equals("6"))
			return "Admin";
		return null;
	}
}
