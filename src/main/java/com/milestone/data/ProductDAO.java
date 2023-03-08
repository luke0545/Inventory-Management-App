package com.milestone.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.milestone.model.ArmorModel;
import com.milestone.model.HealthModel;
import com.milestone.model.ProductModel;
import com.milestone.model.WeaponModel;

/*
 * LoginModel DAO to manipulate objects in database
 * implements ProductAccessInterface 
 */
@Service
public class ProductDAO <T> implements ProductAccessInterface<ProductModel>{

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplaceObject;
	/**
	 * NonDefault Constructor
	 * @param dataSource - datasource to be used by JDBC
	 */
	public ProductDAO(DataSource dataSource) 
	{
		this.dataSource = dataSource;
		this.jdbcTemplaceObject = new JdbcTemplate(dataSource);
	}
	/**
	 * used to create new product
	 * @param pModel - product to create
	 * @return int - rows affected
	 */
	@Override
	public int create(ProductModel pModel) {
		int rows;
		//sql statement
		String sql = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE, QUANTITY, VALUE, TYPE, OWNER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		rows = jdbcTemplaceObject.update(sql, pModel.getName(), pModel.getDescription(), pModel.getPrice(), pModel.getQuantity(), pModel.getValue(), pModel.getType(), "ADMIN");
		
		return rows;
	}

	/**
	 * used to purchase product
	 * @param pModel - product to create
	 * @param ownerName - name of owner
	 * @return int - rows affected
	 */
	@Override
	public int purchase(ProductModel pModel, String ownerName) 
	{
		// get admin quantity of item
		String checkAdminQuantity = "SELECT * FROM PRODUCTS WHERE ID = '" + pModel.getId() + "' AND OWNER_NAME = 'ADMIN'";
		SqlRowSet Adminsrs = jdbcTemplaceObject.queryForRowSet(checkAdminQuantity);
		int adminQuantity = 0;
		while (Adminsrs.next())
		{
			adminQuantity = Adminsrs.getInt("QUANTITY");
		}
		// check if admin product quantity is > 0
		int editAdminProductRow = 0;
		if(adminQuantity > 0)
		{
			// adjust the admin product row quantity to reflect user purchase
			String editAdminProductSql = "UPDATE products SET QUANTITY = '" + (pModel.getQuantity() - 1) + "' WHERE ID = " + pModel.getId();
			editAdminProductRow = jdbcTemplaceObject.update(editAdminProductSql);
			
			//Delete Admin Product if quantity is 0 after purchase
			if (pModel.getQuantity()-1 == 0)
			{
				String deleteEmptyQuantity = "delete from products WHERE ID = " + pModel.getId();
				jdbcTemplaceObject.update(deleteEmptyQuantity);
				
			}
		}

		// if admin row adjust and quantity > 1 was successful, add product to user inventory
		int rows = 0;
		if(editAdminProductRow > 0)
		{
			// check if product already exists for user
			String checkExistingProduct = "SELECT * FROM PRODUCTS WHERE NAME = '" + pModel.getName() + "' AND OWNER_NAME = '" + ownerName + "'";
			SqlRowSet srs = jdbcTemplaceObject.queryForRowSet(checkExistingProduct);

			// get current quantity of products from user
			int counter = 0;
			int userQuantity = 0;
			while (srs.next())
			{
				userQuantity = srs.getInt("QUANTITY");
				counter++;
			}
			// if product exists for user, update quantity of product for user
			if(counter > 0)
			{
				String updateUserProductQuantity = "UPDATE products SET QUANTITY = '" + (userQuantity + 1) + "' WHERE NAME = '" + pModel.getName() + "' AND OWNER_NAME = '" + ownerName + "'";
				rows = jdbcTemplaceObject.update(updateUserProductQuantity);
			}
			// if product does not exist, add new product row for user
			else
			{
				//sql statement
				String sql = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE, QUANTITY, VALUE, TYPE, OWNER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
				rows = jdbcTemplaceObject.update(sql, pModel.getName(), pModel.getDescription(), pModel.getPrice(), 1, pModel.getValue(), pModel.getType(), ownerName);
			}
		}

		return rows;
	}
	/**
	 * used to get all products
	 * @return List - list of products found
	 */
	@Override
	public List<ProductModel> findAll() {

		List<ProductModel> products = new ArrayList<ProductModel>();
		String sql = "SELECT * FROM products WHERE OWNER_NAME = 'admin'";

		SqlRowSet srs = jdbcTemplaceObject.queryForRowSet(sql);
		while (srs.next())
		{//add new Weapon if type == weapon
			if (srs.getString("TYPE").equals("Weapon"))
			{
				products.add(new WeaponModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
			else if (srs.getString("TYPE").equals("Armor"))
			{//add new armor if type == armor
				products.add(new ArmorModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
			if (srs.getString("TYPE").equals("Health"))
			{//add new health if type == health
				products.add(new HealthModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
		}

		return products;

	}
	
	/**
	 * returns a list of products owned by a user
	 * @param name - name of user
	 * @return List - list of products returned via DAO by username
	 */
	@Override
	public List<ProductModel> findByUser(String name) {

		List<ProductModel> products = new ArrayList<ProductModel>();
		String sql = "SELECT * FROM products WHERE OWNER_NAME = '"+name+"'";

		SqlRowSet srs = jdbcTemplaceObject.queryForRowSet(sql);
		while (srs.next())
		{//add new Weapon if type == weapon
			if (srs.getString("TYPE").equals("Weapon"))
			{
				products.add(new WeaponModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
			else if (srs.getString("TYPE").equals("Armor"))
			{//add new armor if type == armor
				products.add(new ArmorModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
			if (srs.getString("TYPE").equals("Health"))
			{//add new health if type == health
				products.add(new HealthModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						));
			}
		}
		return products;
	}
	/**
	 * used to get a product by id
	 * @param id - id to find in db
	 * @return productModel - product returned 
	 */
	@Override
	public ProductModel findById(int id) {
		String sql = "SELECT * FROM PRODUCTS WHERE ID = "+id;
		
		SqlRowSet srs = jdbcTemplaceObject.queryForRowSet(sql);
		while (srs.next())
		{
			if (srs.getString("TYPE").equals("Weapon"))
			{//return Weapon if type == weapon
				WeaponModel w = new WeaponModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						);
				return w;
			}
			else if (srs.getString("TYPE").equals("Armor"))
			{//return armor if type == armor
				ArmorModel a = new ArmorModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						);
				return a;
			}
			else if (srs.getString("TYPE").equals("Health"))
			{//return health if type == health
				HealthModel h = new HealthModel(
						srs.getInt("ID"), 
						srs.getString("NAME"), 
						srs.getString("DESCRIPTION"), 
						srs.getInt("PRICE"), 
						srs.getInt("QUANTITY"),
						srs.getInt("VALUE"),
						srs.getString("TYPE")
						);
				return h;
			}
		}

		return null;
	}
	/**
	 * used to update a product
	 * @param pModel - object to update
	 * @return int - rows affected
	 */
	@Override
	public int update(ProductModel pModel) 
	{
		int rows;
		String sql = "UPDATE products SET NAME = '" + pModel.getName() + "', DESCRIPTION = '" + pModel.getDescription() + "', PRICE = " + 
						pModel.getPrice() + ", QUANTITY = " + pModel.getQuantity() + ", VALUE = " + pModel.getValue() + ", TYPE = '" + pModel.getType() + "' WHERE ID = " + pModel.getId();
		
		rows = jdbcTemplaceObject.update(sql);
		return rows;
	}
	/**
	 * used to delete a product from product table
	 * @param pModel - product to delete
	 * @return int - return rows affected
	 */
	@Override
	public int delete(ProductModel pModel) 
	{
		int rows;
		String sql = "DELETE FROM PRODUCTS WHERE ID = " + pModel.getId();;
		
		rows = jdbcTemplaceObject.update(sql);

		return rows;
	}

}
