package com.shravya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.SupplierDaoImpl;
import com.shravya.model.Supplier;

@Controller
public class AddSupplier 
{
	@Autowired
	SupplierDaoImpl s;
@RequestMapping("/supplier")
public ModelAndView goToSupplierForm()
{
    ModelAndView  mv=new ModelAndView("supplier");
    
    mv.addObject("sup",new Supplier());
    mv.addObject("buttonName","AddSupplier");
    return  mv;
}

@RequestMapping(value="/addSup",method=RequestMethod.POST)
public ModelAndView recieveSupplierFormData(@ModelAttribute ("sup") Supplier sup)
{
	
    System.out.println(sup.getSupplierName());
    System.out.println(sup.getSupplierDescription());
    ModelAndView modelAndView=new ModelAndView("home");
    s.saveSupplier(sup);
    return modelAndView;

}
}
