package com.catbaseboot.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AddCatFormDto {

    @NotEmpty
    @Size(min=3, max=12)
    private String name;
    
    @NotEmpty
    @Pattern(regexp="[0-3]?[0-9].[0-1]?[0-9].([0-9]){4}", message="Give date in format 'dd.MM.yyyy'")
    //@DateTimeFormat(pattern = "dd.MM.yyyy") //MM is for months , mm for minutes
    private String birthDate;
    
    @Min(0)
    @NotNull
	private Float weight;
	
	@NotEmpty
	@Size(min=3, max=12)
	private String guardianName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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

}