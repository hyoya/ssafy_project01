package com.ssafy;

public class Foods {
	
	private String foodcode;
	private String foodname;
	private String maker;
	private String material;
	private String nut;
	private String image;

	
	
	public Foods(String foodcode, String foodname, String maker, String material, String nut, String image) {
		this.foodcode = foodcode;
		this.foodname = foodname;
		this.maker = maker;
		this.material = material;
		this.nut = nut;
		this.image = image;
	}

	public Foods() {
		
	}

	public String getFoodcode() {
		return foodcode;
	}

	public void setFoodcode(String foodcode) {
		this.foodcode = foodcode;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getNut() {
		return nut;
	}

	public void setNut(String nut) {
		this.nut = nut;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Foods [foodcode=" + foodcode + ", foodname=" + foodname + ", maker=" + maker + ", material=" + material
				+ "]";
	}

	
}
