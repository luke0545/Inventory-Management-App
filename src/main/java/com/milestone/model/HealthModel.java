package com.milestone.model;
/**
 * Health Class, extends the Product class in model package
 * @author Michal Uchmanowicz
 *
 */
public class HealthModel extends ProductModel {

	public int healthpoints;

	/**
	 * NonDefault Constructor, calls super() to reduce repetition of construction
	 * @param ID - ID of health
	 * @param N - Name of health
	 * @param D - Description of health
	 * @param P - Price of health
	 * @param Q - Quantity of health
	 * @param h - Damage health heals
	 * @param t - Type of Weapon
	 */
	public HealthModel(int ID, String N, String D, int P, int Q, int h, String t) {
		super(ID, N, D, P, Q, h, t);
		// TODO Auto-generated constructor stub
		this.healthpoints=h;
	}
	/**
	 * "copy" constructor, creates a Healthmodel out of a product model
	 * @param p - product model to copy
	 */
	public HealthModel(ProductModel p)
	{
		this.setId(p.getId());
		this.setName(p.getName());
		this.setDescription(p.getDescription());
		this.setPrice(p.getPrice());
		this.setQuantity(p.getQuantity());
		this.setHealthpoints(p.getValue());
	}
	/**
	 * Default Constructor for Weapon. Sets damage to 0
	**/
	public HealthModel()
	{
		super();
		this.healthpoints=0;

	}

	/**
	 * Overwritten ToString() method, returns properties of Health in a neat description block
	 */
	@Override
	public String toString()
	{
		return 		" ID : "+this.getId()+" \r\n "
				+ "Potion Name : "+this.getName()+" \r\n "
				+ "Potion Description : "+this.getDescription()+" \r\n "
				+ "Potion Price : $"+this.getPrice()+ " \r\n "
				+ "Potion Quantity : "+this.getQuantity() +"\r\n "
				+ "Health Points Restored : "+this.healthpoints+"\r\n";
	}
	/**
	 * gets Health Points of health object
	 * @return healthpoints - health restored by potion
	 */
	public int getHealthpoints() {
		return healthpoints;
	}
	/**
	 * sets healthpoints of health object
	 * @param healthpoints - health restored by potion
	 */
	public void setHealthpoints(int healthpoints) {
		this.healthpoints = healthpoints;
	}
	
}
