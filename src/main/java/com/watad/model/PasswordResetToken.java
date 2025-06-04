package com.watad.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

	
	private static final int EXPIRATION = 3 * 60;  // 3 hours in seconds
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ; 
	
	@Column(nullable = false , unique = true)
	private String token; 
	
	@ManyToOne(targetEntity = User.class , fetch = FetchType.EAGER)
	@JoinColumn(nullable = false , name = "user_id")
	private User user;
	
	private Date expireDate ;
	
    @Column(nullable = false, columnDefinition = "TINYINT")
	private int used ; 	

	
	public PasswordResetToken() {
		
		this.expireDate = calculateExpiryDate(EXPIRATION);
		this.used       = 0;
	}
	
	public PasswordResetToken(String token , User user) {
		this.token      = token ; 
		this.user       = user  ;
		this.expireDate = calculateExpiryDate(EXPIRATION);
		this.used       = 0;
	}
	private Date calculateExpiryDate(int expireyTimeMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, expireyTimeMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public boolean isExpired() {
		System.out.println("the date is expired in:-"+expireDate+" , "+ new Date().after(this.expireDate));
		return new Date().after(this.expireDate);
	}

	public static int getExpiration() {
		return EXPIRATION;
	}

	public long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public int getUsed() {
		return used;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setUsed(int used) {
		this.used = used;
	}
	
	
	
}
