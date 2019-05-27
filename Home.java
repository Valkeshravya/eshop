package com.shravya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home 
{
	@RequestMapping("/home")
public String goToHome()
{
	return "home";
}
	@RequestMapping("/contact")
	public String goToContact()
	{
		return "contact";
	}
	
	@RequestMapping("/about")
	public String goToAbout()
	{
		return "about";
    }
	
	@RequestMapping("/userhome")
	public String goToUserHome() 
	{
	return "userhome";	
	}
}

