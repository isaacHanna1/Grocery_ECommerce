package com.watad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
	private List<Item> items;

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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
