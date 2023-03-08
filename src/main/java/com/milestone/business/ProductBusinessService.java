package com.milestone.business;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.milestone.data.ProductAccessInterface;
import com.milestone.model.ProductModel;


/**
 * ProductBusinessService class implemented from ProductBusinessServiceInterface
 *
 */
public class ProductBusinessService implements ProductBusinessServiceInterface{

	@Autowired
	ProductAccessInterface<ProductModel> DAO;
	/**
	 * @return List - list of products returned via DAO
	 */
	@Override
	public List<ProductModel> getProducts() 
	{		
		return DAO.findAll();
	}
	
	/**
	 * returns a list of products owned by a user
	 * @param name - name of user
	 * @return List - list of products returned via DAO by username
	 */
	@Override
	public List<ProductModel> getProductsByUsername(String name) 
	{		
		return DAO.findByUser(name);
	}
	/**
	 * adds product through dao
	 * @param pModel - product to add
	 * @return affected - adds product to list and returns rows affected
	 */
	@Override
	public int addProduct(ProductModel pModel) 
	{
		int affected = DAO.create(pModel);
		if (affected != 0)
		{
			System.out.println("Created: \n"+ pModel.toString());
			return affected;
		}
		return 0;
	}
	/**
	 * deletes product through dao
	 * @param pModel - product to delete
	 * @return affected - deletes product from list and returns rows affected
	 */
	@Override
	public int delProduct(ProductModel pModel) 
	{
		int affected = DAO.delete(pModel);
		if (affected != 0)
		{
			System.out.println("Deleted product: \n"+pModel.toString());
			return affected;
		}
		return 0;
	}

	/**
	 * updates product through dao
	 * @param pModel - product to update
	 * @return affected - updates a product in list and returns rows affected
	 */
	@Override
	public int updateProduct(ProductModel pModel) 
	{
		int affected = DAO.update(pModel);
		if (affected != 0)
		{
			System.out.println("Updated product: \n"+pModel.toString());
			return affected;
		}
		return 0;
	}

	/**
	 * calls purchase product in DAO
	 * @param pModel - product to purchase
	 * @param ownerName - name of owner
	 * @return affected - updates a product in list and returns rows affected
	 */
	@Override
	public int purchaseProduct(ProductModel pModel, String ownerName) 
	{
		int affected = DAO.purchase(pModel, ownerName);
		if (affected != 0)
		{
			System.out.println("Purchased: \n"+ pModel.toString());
			return affected;
		}
		return 0;
	}

	/**
	 * calls findproduct method in DAO
	 * @param id - id used to find product 
	 * @return p - productModel, finds a product by id in list and returns productModel
	 */
	@Override
	public ProductModel findProductById(int id) 
	{
		ProductModel p = DAO.findById(id);
		return p;
	}
	/**
	 * called when bean is created
	 */
	@Override
	public void init() 
	{
		System.out.println("ProductBusinessService Bean Instantiated");
	}
	/**
	 * called when bean is destroyed
	 */
	@Override
	public void destroy() 
	{
		System.out.println("ProductBusinessService Bean Destroyed");
	}

}
