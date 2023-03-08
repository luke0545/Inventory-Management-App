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
@RequestMapping("/products")
public class ProductsController 
{
	@Autowired
	private ProductBusinessServiceInterface prodService;
	
	/**
	 * displays all products in store
 	* @param model - model to attach attributes to 
	* @return String - view "products" to be returned
 	*/
	@GetMapping("/")
	public String display(Model model)
	{
		//Display Login form view with current products
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("productModel", new ProductModel());
		model.addAttribute("products", products);
		
		return "products";
	}
	
	/**
	 * adds a product to the store page
	 * @param productModel - product from form
	 * @param bindingResult - binding result to check for validaiton errors
	 * @param model - model to attach attributes to.
	 * @return String - view "products" to be returned
	 */
	@PostMapping("/addProduct")
	public String addProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors and return view with error messages
		if (bindingResult.hasErrors()) 
		{	
			List<ProductModel> products = prodService.getProducts();
			model.addAttribute("products", products);
			model.addAttribute("productError", "Product Failed to Add. Data Validation Errors in 'Add' Popup.");
			return "products";
		}
		//add product return product added alert for view
		if (prodService.addProduct(productModel)>0)
		{
			model.addAttribute("productadded", true);
		}
		//return updated products
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("products", products);
		//navigate back to products view
		return "products";
	}

/**
 * edits a product on store page
 * @param identity - id of product being editted
 * @param productModel - product from form
 * @param bindingResult - binding result to check for validation errors
 * @param model - model to attach attributes to.
*  @return String - view "products" to be returned
 */
	@PostMapping("/editProduct")
	public String editProduct(@RequestParam(name="identity") String identity, @Valid ProductModel productModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors and return view with error messages
		if (bindingResult.hasErrors()) 
		{	
			List<ProductModel> products = prodService.getProducts();
			model.addAttribute("products", products);
			model.addAttribute("productError", "Product Failed to Edit. Data Validation Errors in 'Edit' Popup.");
			return "products";
		}
		model.addAttribute("productModel", new ProductModel());
		int id = Integer.parseInt(identity);
		productModel.setId(id);
		//edit product and return product editted alert for view 
		if (prodService.updateProduct(productModel)>0)
		{
			model.addAttribute("producteditted", true);
		}
				//return updated product list.
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("products", products);
		//navigate back to products view
		return "products";
	}
	/**
	 * purchases product on store page
	 * @param identity - id of product being editted
	 * @param productModel - product from form
	 * @param bindingResult - binding result to check for validation errors
	 * @param model - model to attach attributes to.
	 * @param principal - used to get name of user
	*  @return String - view "products" to be returned
	*/
	@PostMapping("/purchaseProduct")
	public String purchaseProduct(@RequestParam(name="identity") String identity, @Valid ProductModel productModel, BindingResult bindingResult, Model model, Principal principal)
	{
		int id = Integer.parseInt(identity);
		String ownerName = principal.getName();
		productModel = prodService.findProductById(id);
		System.out.println(productModel.toString());
		//purchase product and return product purchased alert for view 
		if (prodService.purchaseProduct(productModel, ownerName) > 0)
		{
			model.addAttribute("productadded", true);
		}
		else
		{
			model.addAttribute("productError", "Purchase Failed");
		}
		//return updated product list.
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("products", products);
		//navigate back to products view
		return "products";
	}

	
	/**
	 * deletes product on store page
	 * @param identity - id of product being editted
	 * @param model - model to attach attributes to.
	*  @return String - view "products" to be returned
	*/
	@PostMapping("/delProduct")
	public String delProduct(@RequestParam(name="identity") String identity, Model model)
	{
		model.addAttribute("productModel", new ProductModel());
		//find product and delete it
		int i = Integer.parseInt(identity);
		ProductModel p = prodService.findProductById(i);
		//delete product and return product deleted alert for view 
		if (prodService.delProduct(p)>0)
		{
			model.addAttribute("productdeleted", true);
			
		}
		//return updated product list.
		List<ProductModel> products = prodService.getProducts();
		model.addAttribute("products", products);
		//navigate back to products view
		return "products";
	
	}
}