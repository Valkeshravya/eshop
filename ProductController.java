package com.shravya.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.Temporal;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shravya.daoimpl.CartDaoImpl;
import com.shravya.daoimpl.CategoryDaoImpl;
import com.shravya.daoimpl.ProductDaoImpl;
import com.shravya.daoimpl.SupplierDaoImpl;
import com.shravya.daoimpl.TestSessionFactory;
import com.shravya.model.Cart;
import com.shravya.model.Category;
import com.shravya.model.Product;
import com.shravya.model.Supplier;

@Controller
public class ProductController
{
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@Autowired
	SupplierDaoImpl supplierDaoImpl;
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@Autowired
	CartDaoImpl cartDaoImpl;
	
    @RequestMapping("/product")   
	public ModelAndView saveProduct()
	{
		ModelAndView modelAndView=new ModelAndView("product");
		Product product=new Product();
		/*
		if(product.getProductId()==0)
	    {
	    int id=(int)(Math.random()*10000);
	    product.setProductId(id);
	    System.out.println("..........."+id);
	    }
	    */
		product.setProductId((int)(Math.random()*1000));
		modelAndView.addObject("pro", product);
		  List<Category> catList=categoryDaoImpl.recieveAllCategory();
	        modelAndView.addObject("catlist",catList);
	        List<Supplier> supList=supplierDaoImpl.recieveSupplier();
	        modelAndView.addObject("suplist", supList);

		modelAndView.addObject("buttonName","Add Product");
		return modelAndView;
	}

    @RequestMapping(value="/addPro",method=RequestMethod.POST)
    public ModelAndView recieveProductFormData(@ModelAttribute ("pro") Product pr)
    {
    	
        System.out.println(pr.getProductName());
        System.out.println(pr.getProductDescription());
        System.out.println(pr.getProductPrice());
        System.out.println(pr.getProductCategory());
        System.out.println(pr.getProductSupplier());
        ModelAndView modelAndView=new ModelAndView("home");
        MultipartFile image=pr.getProductImage();
        System.out.println("Testing image------"+image);
        BufferedOutputStream bos = null;
        try {
        	byte bytearray[]=image.getBytes();
        	FileOutputStream fos=new FileOutputStream("G:\\eclipse-workspace\\eeshop\\src\\main\\webapp\\resources\\images\\"+pr.getProductId()+".jpg");
             bos=new BufferedOutputStream(fos);
			bos.write(bytearray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
        	try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        productDaoImpl.addProduct(pr);
        return modelAndView;
    }
    @RequestMapping("/showp")
    public ModelAndView showProductData(Product product)
    {
    	ModelAndView modelAndView=new ModelAndView("showproduct");
    	productDaoImpl.recieveAllProducts();
    	List<Product> list=productDaoImpl.recieveAllProducts();
    	/*for (Product product2 : list) 
    	{
			 System.out.println(product2.getProductId());
			 System.out.println(product2.getProductName());
			 System.out.println(product2.getProductDescription());
			 System.out.println(product2.getProductCategory());
			 System.out.println(product2.getProductSupplier());
		}
		*/
    	modelAndView.addObject("showdata",list);
    	return modelAndView;
    }
    @RequestMapping("/delet")
    public String deleteProduct(@RequestParam("proId") int productId)
    {
    	ModelAndView modelAndView=new ModelAndView("showproduct");
    	productDaoImpl.deleteProductRecord(productId);
		//List<Product> list=productDaoImpl.recieveAllProducts();
    	// modelAndView.addObject("showdata",list);
		File file=new File("G:\\eclipse-workspace\\eeshop\\src\\main\\webapp\\resources\\images\\"+productId+".jpg");
		file.delete();
    	return "redirect:showp";
    }
    
    @RequestMapping("/edt")
    public ModelAndView editProductData(@RequestParam("proId") int productId)
    {
    	ModelAndView modelAndView=new ModelAndView("product");
        Product product=productDaoImpl.editProduct(productId);
        modelAndView.addObject("pro",product);
        List<Category> catList=categoryDaoImpl.recieveAllCategory();
        modelAndView.addObject("catlist",catList);
        List<Supplier> supList=supplierDaoImpl.recieveSupplier();
        modelAndView.addObject("suplist", supList);
        modelAndView.addObject("buttonName","Update Product");
	    return modelAndView;	
	}
    
    
  //------------------USER MODULE---------------------------  
    
    
   @RequestMapping("/userproducts") 
   public ModelAndView userproducts()
   {
	   ModelAndView modelAndView=new ModelAndView("userproducts");
	   productDaoImpl.recieveAllProducts();
   	List<Product> list=productDaoImpl.recieveAllProducts();
   	modelAndView.addObject("showdata",list);
      return modelAndView;	
   }
   
   @RequestMapping("/productdetail")
   public ModelAndView displayProduct(@RequestParam("pid") int  productId)
   {
	   ModelAndView modelAndView=new ModelAndView("singleproduct");
	 Product product= productDaoImpl.editProduct(productId);
	 //List<Product> list=productDaoImpl.recieveAllProducts();
	//supplierDaoImpl.printSupplierAddress();
	   modelAndView.addObject("showdata",product);
	   return modelAndView;
   }
   
 }

