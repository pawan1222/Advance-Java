package com.lpu.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
//	@SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue=100, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	private int personId;
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;
	
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(int personId, String name) {
		super();
		this.personId = personId;
		this.name = name;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

}
