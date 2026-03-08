package com.lpu.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="passport")
public class Passport {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
//	@SequenceGenerator(name = "passport_seq", sequenceName = "passport_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	private int passportId;
	private String passportNumber;
	private String country;
	
	public Passport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passport(int passportId, String passportNumber, String country) {
		super();
		this.passportId = passportId;
		this.passportNumber = passportNumber;
		this.country = country;
	}

	public int getPassportId() {
		return passportId;
	}

	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	

}


