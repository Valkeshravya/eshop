package com.shravya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.CategoryDaoImpl;
import com.shravya.model.Category;
@Controller
public class ShowCategory
{
	@Autowired
	CategoryDaoImpl c;
	 @RequestMapping("/show")
	public ModelAndView recieveData()
		{
		ModelAndView modelAndView=new ModelAndView("showcategory");	
	
		List<Category> lc=c.recieveAllCategory();
       modelAndView.addObject("data", lc);
		return modelAndView;
		}
	 
@RequestMapping("/del")
	 public String deleteCategory(@RequestParam("catId") int categoryId)
	 {
		 System.out.println(categoryId);
		 ModelAndView modelAndView=new ModelAndView("showcategory");
		 c.deleteCategory(categoryId);
	     return "redirect:show";
				 }
@RequestMapping("/edit")
public ModelAndView editCategory(@RequestParam("catId") int categoryId)
{
	ModelAndView modelAndView=new ModelAndView("category");
	Category category=c.editCategory(categoryId);
	modelAndView.addObject("cat",category);
	modelAndView.addObject("buttonName","Update Catogory");
	return modelAndView;
}

}
