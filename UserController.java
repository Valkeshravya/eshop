package com.shravya.controller;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.UserDaoImpl;
import com.shravya.model.Product;
import com.shravya.model.User;
@Controller
public class UserController
{
	@Autowired
	UserDaoImpl userDaoImpl;
  @RequestMapping("/registr")
	public ModelAndView goToRegForm()
	{
	  User user=new User();
		ModelAndView modelandview=new ModelAndView("register");
		modelandview.addObject("usr",user);
		return modelandview;
	}
  @RequestMapping(value="/addUser",method=RequestMethod.POST)
  public ModelAndView recieveUserFormData(@ModelAttribute ("usr") User user)
  {
	  ModelAndView modelAndView=new ModelAndView("userhome");
      userDaoImpl.addToUserForm(user);
      return modelAndView;
  }
  
  @RequestMapping(value="/login",method=RequestMethod.GET)
  public ModelAndView goToLoginForm(@ModelAttribute ("usr") User user)
  {
	  User user2=new User();
	  ModelAndView modelAndView=new ModelAndView("login");
	  return modelAndView;
  }
  @RequestMapping(value="/login",method=RequestMethod.POST)
  public ModelAndView recieveLoginFormData(@ModelAttribute ("usr") User user)
  {
	
     boolean result=userDaoImpl.checkLogin(user);
     if(result==true)
     {
       ModelAndView modelAndView=new ModelAndView("loginsuccess");
    	 return modelAndView;
  }
     else {
    	 ModelAndView modelAndView=new ModelAndView("login");
    	 return modelAndView;
	}
  }
}
