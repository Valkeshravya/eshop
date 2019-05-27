package com.shravya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.SupplierDaoImpl;
import com.shravya.model.Category;
import com.shravya.model.Supplier;

@Controller
public class ShowSupplier 
{
	@Autowired
	SupplierDaoImpl sd;
	
	@RequestMapping("/showSupp")
public ModelAndView recieveSupplierData(Supplier su)
{
	ModelAndView modelAndView=new ModelAndView("showsupplier");
	List<Supplier> lt=sd.recieveSupplier();
	for(Supplier supp:lt)
	{
		System.out.println(supp.getSupplierId());
		System.out.println(supp.getSupplierName());
		System.out.println(supp.getSupplierDescription());
	}
   modelAndView.addObject("datarecieve", lt);
	return modelAndView;	
}
	@RequestMapping("/delete")
	 public String deleteSupplier(@RequestParam("suppId") int supplierId)
	 {
		 System.out.println(supplierId);
		 ModelAndView modelAndView=new ModelAndView("showsupplier");
		 sd.deleteSupplier(supplierId);
		 return "redirect:showSupp";
				 }
@RequestMapping("/ed")
public ModelAndView editCategory(@RequestParam("suppId") int supplierId)
{
	System.out.println("at sup controller"+supplierId);
	ModelAndView modelAndView=new ModelAndView("supplier");
	Supplier supplier=sd.editSupplier(supplierId);
	modelAndView.addObject("sup",supplier);
	modelAndView.addObject("buttonName","Update Supplier");
	return modelAndView;
}

	
}
