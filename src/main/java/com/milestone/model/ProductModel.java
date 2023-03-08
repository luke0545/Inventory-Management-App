package com.milestone.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ProductModel class, superclass to weapon, armor, health
 * @author Michal Uchmanowicz
 *
 */
public class ProductModel 
{
    private int id;

    @NotNull(message="Name is a required field")
	@Size(min=3, max=32, message="Name must be between 3 and 32 characters")
	private String name;
	
    @NotNull(message="Description is a required field")
	@Size(min=3, max=200, message="Description must be between 3 and 150 characters")
	private String description;

    @Min(value=1)
    @Max(value=200)
	private int price;

    @Min(value=1)
    @Max(value=100)
	private int quantity;
    
    @Min(value=1)
    @Max(value=200)
	private int value;
	
    //Select from dropdown. html validated (client)
	private String type;

	/**
	 * NonDefault product Constructor
	 * @param id - id of product	
	 * @param name - name of product
	 * @param desc - desc of product	
	 * @param price -  price of product
	 * @param quantity	- quantity of product
	 * @param value	- value of product
	 * @param type - type of product
	 */
    public ProductModel(int id, String name, String desc, int price, int quantity, int value, String type) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.price = price;
        this.quantity = quantity;
        this.value = value;
        this.type = type;
    }

    /**
     * default product constructor
     */
    public ProductModel() {
        this.id = -1;
        this.name = "";
        this.description = "";
        this.price = 0;
        this.quantity = 0;
        this.value = 0;
        this.type = "";
    }
	/**
     * returns type of product
     * @return type - this.type of product
     */
    public String getType() {
		return type;
	}
	/**
     * sets type of product
     * @param type - this.type of product
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * sets value of product
     * @param value - this.value of product
     */
	public void setValue(int value) {
		this.value = value;
	}

	/**
     * returns value of product
     * @return value - this.value of product
     */
    public int getValue() {
		return this.value;
	}

	/**
     * returns id of product
     * @return id - this.id of product
     */
	public int getId() {
        return this.id;
    }
    /**
     * sets id of product
     * @param id - id of product
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * returns name of product
     * @return name - this.name of product
     */
    public String getName() {
        return this.name;
    }
    /**
     * sets name of product
     * @param name - name of product
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * returns description of product
     * @return description - this.description of product
     */
	public String getDescription() {
		return this.description;
	}
    /**
     * sets description of product
     * @param description - description of product
     */
	public void setDescription(String description) {
		this.description = description;
	}
    /**
     * returns price of product
     * @return price - this.price of product
     */
    public int getPrice() {
        return this.price;
    }
    /**
     * sets price of product
     * @param price - price of product
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * returns quantity of product
     * @return quantity - this.quantity of product
     */
    public int getQuantity() {
        return this.quantity;
    }
    /**
     * sets quantity of product
     * @param quantity - quantity of product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Overwritten toString method
     */
	@Override
	public String toString()
	{
		return 		" ID : "+this.getId()+" \r\n "
				+ "Product Name : "+this.getName()+" \r\n "
				+ "Product Description : "+this.getDescription()+" \r\n "
				+ "Product Price : $"+this.getPrice()+ " \r\n "
				+ "Product Quantity : "+this.getQuantity() +"\r\n "
				+ "Product Type : "+this.getType() +"\r\n ";
	}
}