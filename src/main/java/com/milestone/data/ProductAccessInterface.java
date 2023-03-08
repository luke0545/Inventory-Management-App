package com.milestone.data;

import java.util.List;

import com.milestone.model.ProductModel;

/**
 * ProductAccessInterface for BusinessService
 * @author Mike U
 *
 */
public interface ProductAccessInterface <T> 
{
	/**
	 * used to get all products
	 * @return List - list of products found
	 */
	public List<T> findAll();

	/**
	 * used to get a product by id
	 * @param id - id to find in db
	 * @return T - product returned 
	 */
	public T findById(int id);

	/**
	 * used to create new product
	 * @param t - product to create
	 * @return affected - rows affected
	 */
	public int create(T t);

	/**
	 * used to create new product
	 * @param t - product to purchase
	 * @param ownerName - name of owner
	 * @return affected - rows affected
	 */
	public int purchase(T t, String ownerName);

	/**
	 * used to update a product
	 * @param t - object to update
	 * @return affected - rows affected
	 */
	public int update(T t);

	/**
	 * used to delete a product from product table
	 * @param t - product to delete
	 * @return affected - rows affected
	 */
	public int delete(T t);

	/**
	 * returns a list of products owned by a user
	 * @param name - name of user
	 * @return List - list of products returned via DAO by username
	 */
	List<ProductModel> findByUser(String name);
}

