
//this class for the header (information about order date and the cutomer who wanted this order)

package com.watad.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId",nullable = false)
	private User user ;
	
    @JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<OrderItems> orderItems;
	
	private Date orderDate = new Date();
	
	@Column(name = "details" , length = 500)
	private String details;
	
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	private OrderStatus status = OrderStatus.wait;
		
	
	private String statusComment ="not set yet";
	
	
	

}
