package com.watad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagesOfItem")
public class itemImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ; 
	
	@ManyToOne()
	private Item item;
	

	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;


	public long getId() {
		return id;
	}


	public Item getItem() {
		return item;
	}


	public byte[] getImage() {
		return image;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	

	
	
}
