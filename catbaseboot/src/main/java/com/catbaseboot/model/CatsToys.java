package com.catbaseboot.model;

import javax.persistence.*;

@Entity
public class CatsToys {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	private Cat cat;
	
	private String toyName;
	
	private String toyColor;
	
	private Float toyPrice;

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}
	
	public String getToyName() {
		return toyName;
	}

	public void setToyName(String toyName) {
		this.toyName = toyName;
	}

	public String getToyColor() {
		return toyColor;
	}

	public void setToyColor(String toyColor) {
		this.toyColor = toyColor;
	}

	public Float getToyPrice() {
		return toyPrice;
	}

	public void setToyPrice(Float toyPrice) {
		this.toyPrice = toyPrice;
	}
	
}