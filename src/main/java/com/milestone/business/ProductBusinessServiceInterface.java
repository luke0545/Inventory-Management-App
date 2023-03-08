package com.milestone.business;

import java.util.List;

import com.milestone.model.ProductModel;
/**
 * BusinessServiceInterface for BusinessService
 * @author Michal Uchmanowicz
 *
 */
public interface ProductBusinessServiceInterface {
	/**
	 * 
	 * @return List - list of products
	 */
	public List<ProductModel> getProducts();
	
	/**
	 * gets product
	 * @param name - name of user
	 * @return List - list of products returned via DAO by username
	 */
	public List<ProductModel> getProductsByUsername(String name);
	
	/**
	 * gets products from one user
	 * @param pModel - product to add
	 * @return affected - adds product to list and returns rows affected
	 */
	public int addProduct(ProductModel pModel);
	
	/**
	 * deletes product from db
	 * @param pModel - product to delete
	 * @return affected - deletes product from list and returns rows affected
	 */
	public int delProduct(ProductModel pModel);
	
	/**
	 * updates product in db
	 * @param pModel - product to update
	 * @return affected - updates a product in list and returns rows affected
	 */
	public int updateProduct(ProductModel pModel);

	/**
	 * adds product to user profile
	 * @param pModel - product to purchase
	 * @param ownerName - name of owner
	 * @return affected - updates a product in list and returns rows affected
	 */
	public int purchaseProduct(ProductModel pModel, String ownerName);
	
	/**
	 * returns a product found by Id
	 * @param id - id of user
	 * @return pModel - product found by Id
	 */
	public ProductModel findProductById(int id);
	
	/**
	 * 
	 * called when bean is created
	 */
	public void init();
	
	/**
	 * 
	 * called when bean is destroyed
	 */
	public void destroy();
}
