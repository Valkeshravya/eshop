package com.shravya.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.CartDaoImpl;
import com.shravya.daoimpl.ProductDaoImpl;
import com.shravya.model.Cart;
import com.shravya.model.Product;

@Controller
public class CartController 
{
	@Autowired
	ProductDaoImpl productDaoImpl;
	@Autowired
	CartDaoImpl cartDaoImpl;
	
	  @RequestMapping("/cart")
	   public ModelAndView addToCart(HttpServletRequest request)
	   {
		   ModelAndView modelAndView=new ModelAndView("userhome");
		 int productId=Integer.parseInt(request.getParameter("pid"));
		 Product product=productDaoImpl.getProduct(productId);
	 	 int quantity=Integer.parseInt(request.getParameter("quan"));
	 	 Cart cart=cartDaoImpl.setCart(product, quantity);
	 	 cartDaoImpl.saveCart(cart);
		 return modelAndView;
	   }

	  @RequestMapping("/showcart")
	  public ModelAndView displayCart() 
	  {
		  ModelAndView modelAndView=new ModelAndView("cart");
		List<Cart> list=cartDaoImpl.recieveCart();
		modelAndView.addObject("add", list);
		return modelAndView;
		
	}
}
