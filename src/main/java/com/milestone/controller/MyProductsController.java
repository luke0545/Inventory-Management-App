package com.milestone.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milestone.business.ProductBusinessServiceInterface;
import com.milestone.model.ProductModel;

/**
 * products controller used to log products and add new items to a user's inventory
 * @author Luke Walder
 */
@Controller
@RequestMapping("/myProducts")
public class MyProductsController 
{
	@Autowired
	private ProductBusinessServiceInterface prodService;
	
	/**
	 * displays products owned by specific user
    * @param model - model to attach attributes to 
	* @param principal - Security User Object provided by Spring Security
	* @return String - view "productsPerUser" to be returned
 	*/
	@GetMapping("/")
	public String displayByUser(Model model, Principal principal)
	{
	    String name = principal.getName();
	    
	    //get all products
		List<ProductModel> products = prodService.getProductsByUsername(name);
		model.addAttribute("productModel", new ProductModel());
		model.addAttribute("products", products);
	    
		return "productsPerUser";
	}
	
	/**
	 * deletes product from user inventory
	 * @param identity - id of product being deleted
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return String - view "products" to be returned
	 */
	@PostMapping("/delProduct")
	public String delProduct(@RequestParam(name="identity") String identity, Model model, Principal principal)
	{
		model.addAttribute("productModel", new ProductModel());
		//find product
		int i = Integer.parseInt(identity);
		ProductModel p = prodService.findProductById(i);
		//delete product and return product deleted alert for view 
		if (prodService.delProduct(p)>0)
		{
			model.addAttribute("productdeleted", true);
		}

		//obtain username
	    String name = principal.getName();
	    
		List<ProductModel> products = prodService.getProductsByUsername(name);
		model.addAttribute("productModel", new ProductModel());
		model.addAttribute("products", products);
		
		//navigate back to products view
		return "productsPerUser";
	}
	/**
	 * purchases product for user inventory
	 * @param identity - id of product being deleted
	 * @param productModel - model to attach attributes to.
	 * @param bindingResult - error checking
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return String - view "products" to be returned
	 */
	@PostMapping("/purchaseProduct")
	public String purchaseProduct(@RequestParam(name="identity") String identity, @Valid ProductModel productModel, BindingResult bindingResult, Model model, Principal principal)
	{
		String ownerName = principal.getName();
		
		//find product
		int id = Integer.parseInt(identity);
		productModel = prodService.findProductById(id);
	
		// Check for validation errors and return view with error messages
		if (bindingResult.hasErrors()) 
		{	
			List<ProductModel> products = prodService.getProducts();
			model.addAttribute("products", products);
			model.addAttribute("productError", "Product Failed to Purchase. Data Validation Errors in 'Purchase' Popup.");
			return "products";
		}
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("products", products);
		model.addAttribute("productModel", new ProductModel());

		return "products";
	}
}