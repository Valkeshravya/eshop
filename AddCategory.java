package com.shravya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.CategoryDaoImpl;
import com.shravya.daoimpl.TestSessionFactory;
import com.shravya.model.Category;

@Controller
public class AddCategory 
{
	@Autowired
	TestSessionFactory tsf;
	
	@Autowired
	CategoryDaoImpl c;
	@RequestMapping("/category")
	
    public ModelAndView goToCategoryForm()
    {
        ModelAndView  mv=new ModelAndView("category");
        
        mv.addObject("cat",new Category());
        mv.addObject("buttonName","Add Category");
        return  mv;
    }
    
    @RequestMapping(value="/addCat",method=RequestMethod.POST)
    public ModelAndView recieveCategoryFormData(@ModelAttribute ("cat") Category ca)
    {
    	
        System.out.println(ca.getCategoryName());
        System.out.println(ca.getCategoryDescription());
        ModelAndView modelAndView=new ModelAndView("home");
       //modelAndView.addObject("cat",new Category());
        //modelAndView.addObject("buttonName","Add Category");
        c.saveCategory(ca);
		return modelAndView;
       
    }
    
   

}
