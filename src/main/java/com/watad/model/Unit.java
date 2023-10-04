package com.watad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unit")
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private long id ;
	
	@Column(name ="unitName" , unique = true)
	private String unitName ;
	
	@Column(name ="unitDecription" , unique = true)
	private String unitDecription ;
	
	public long getId() {
		return id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitDecription() {
		return unitDecription;
	}
	public void setUnitDecription(String unitDecription) {
		this.unitDecription = unitDecription;
	}
	
	
	
}
