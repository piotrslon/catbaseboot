package com.catbaseboot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private Date birthDate;
	private Float weight;
	private String guardianName;
	
	@OneToMany(mappedBy="cat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CatsToys> catToys;

	public void addCatToys (CatsToys catstoys) {
		if (catToys == null) {
			catToys = new ArrayList<CatsToys>();
		}
		catstoys.setCat(this);
		catToys.add(catstoys);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	
	
	public List<CatsToys> getCatToys() {
		return catToys;
	}

	public void setCatToys(List<CatsToys> catToys) {
		this.catToys = catToys;
	}

}
