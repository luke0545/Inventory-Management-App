package com.milestone.model;

/**
 * Armor Class, extends the Product class in model package
 * @author Michal Uchmanowicz
 *
 */
public class ArmorModel extends ProductModel {

	public int armorpoints;

	/**
	 * NonDefault Constructor, calls super to reduce repetition of construction
	 * @param ID - ID of armor
	 * @param N - Name of armor
	 * @param D - Description of armor
	 * @param P - Price of armor
	 * @param Q - Quantity of armor
	 * @param a - Damage armor absorbs
	 * @param t - type of weapon
	 */
	public ArmorModel(int ID, String N, String D, int P, int Q, int a, String t) {
		super(ID, N, D, P, Q, a, t);
		// TODO Auto-generated constructor stub
		this.armorpoints=a;
	}
	/**
	 * "copy" constructor, creates a Armormodel out of a product model
	 * @param p - product model to copy
	 */
	public ArmorModel(ProductModel p)
	{
		this.setId(p.getId());
		this.setName(p.getName());
		this.setDescription(p.getDescription());
		this.setPrice(p.getPrice());
		this.setQuantity(p.getQuantity());
		this.setArmorpoints(p.getValue());

	}
	/**
	 * Default Constructor for Weapon. Sets damage to 0
	**/
	public ArmorModel()
	{
		super();
		this.armorpoints=0;
	}
	/**
	 * Overwritten ToString() method, returns properties of Armor in a neat description block
	 */
	@Override
	public String toString()
	{
		return 		" ID : "+this.getId()+" \r\n "
				+ "Armor Name : "+this.getName()+" \r\n "
				+ "Armor Description : "+this.getDescription()+" \r\n "
				+ "Armor Price : $"+this.getPrice()+ " \r\n "
				+ "Armor Quantity : "+this.getQuantity() +"\r\n "
				+ "Armor Absorption : "+this.armorpoints+"\r\n";
	}
	/**
	 *  returns armor points of Armor
	 * @return armorpoints
	 */
	public int getArmorpoints() {
		return armorpoints;
	}
	
	/**
	 * Sets the armor points
	 * @param armorpoints of armor
	 */
	public void setArmorpoints(int armorpoints) {
		this.armorpoints = armorpoints;
	}
	
}
