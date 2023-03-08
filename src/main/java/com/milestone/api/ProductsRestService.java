package com.milestone.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milestone.business.ProductBusinessServiceInterface;
import com.milestone.model.ProductModel;

/**
 * Service to return data from the db in JSON format
 */
@RestController
@RequestMapping("/service")
public class ProductsRestService 
{
    @Autowired
	ProductBusinessServiceInterface service;

	/**
	 * returns all products in db as a JSON array
	 * @return products - products listed as Json
	 */
    @GetMapping(path="/products")
	public ResponseEntity<?> getAllProducts()
	{
		try
		{
			List<ProductModel> products = service.getProducts();
			if(products == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(products, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    /**
	 * returns one product in db as a JSON object
	 * @param id - id of product to find
	 * @return products - product by ID listed as Json
	 */
    @GetMapping(path="/product/{id}")
   	public ResponseEntity<?> getOneProduct(@PathVariable("id") int id)
   	{
   		try
   		{
   			 ProductModel p = service.findProductById(id);
   			if(p == null)
   				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   			else
   				return new ResponseEntity<>(p, HttpStatus.OK);
   		}
   		catch(Exception e)
   		{
   			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   		}
   	}
}
