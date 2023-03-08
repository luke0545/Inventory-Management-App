package com.milestone.model;
/**
 * Weapon Class, extends the Product class in model package
 * @author Michal Uchmanowicz
 *
 */
public class WeaponModel extends ProductModel {

	public int damage;

	/**
	 * NonDefault Constructor, calls super to reduce repetition of construction
	 * @param ID - ID of weapon
	 * @param N - Name of weapon
	 * @param D - Description of Weapon
	 * @param P - Price of Weapon
	 * @param Q - Quantity of Weapon
	 * @param d - Damage Weapon Deals
	 * @param t - type of product
	 */
	public WeaponModel(int ID, String N, String D, int P, int Q, int d, String t) {
		super(ID, N, D, P, Q, d, t);
		// TODO Auto-generated constructor stub
		this.damage=d;
	}
	/**
	 * "copy" constructor, creates a weapon model out of a product model
	 * @param p - product model to copy
	 */
	public WeaponModel(ProductModel p)
	{
		this.setId(p.getId());
		this.setName(p.getName());
		this.setDescription(p.getDescription());
		this.setPrice(p.getPrice());
		this.setQuantity(p.getQuantity());
		this.setDamage(p.getValue());
	}
	/**
	 * Default Constructor for Weapon. Sets damage to 0
	**/
	public WeaponModel()
	{
		super();
		this.damage=0;
	}

	/**
	 * Overwritten ToString() method, returns properties of Weapon in a neat description block
	 */
	@Override
	public String toString()
	{
		return 		" ID : "+this.getId()+" \r\n "
				+ "Weapon Name : "+this.getName()+" \r\n "
				+ "Weapon Description : "+this.getDescription()+" \r\n "
				+ "Weapon Price : $"+this.getPrice()+ " \r\n "
				+ "Weapon Quantity : "+this.getQuantity() +"\r\n "
				+ "Weapon Damage : "+this.getDamage()+"\r\n";
	}
/**
 * getDamage returns Damage of weapon
 * @return Damage- Damage of weapon
 */
	public int getDamage() {
		return damage;
	}
	/**
	 * sets Damage of weapon
	 * @param damage - damage of weapon
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}


}
