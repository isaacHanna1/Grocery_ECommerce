package com.watad.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.watad.model.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column(name="name")
	@NotNull
	@NotBlank(message = "حقل الاسم لا يمكن ان يكون فارغ")
	private String userName;
	

	@Column(name ="government")
	@NotBlank(message = "حقل المحافظة لا يمكن ان يكون فارغ")
	private String government ;
	
	
	@Column(name ="city")
	@NotBlank(message = "حقل المدينة لا يمكن ان يكون فارغ")
	private String city;
	
	
	@Column(name="phone", unique = true)
	@NotNull
	@NotBlank(message = "حقل رقم الهاتف لا يمكن ان يكون فارغ")
	private String userPhone;
	
	@Column(name="email" , unique = true)
    @NotNull
	@NotBlank(message = "حقل البريد الالكتروني لا يمكن ان يكون فارغ")
	private String userEmail;
	
	@Column(name="password")
	@NotNull
    @NotBlank(message = "حقل الباسورد لا يمكن ان يكون فارغ")
	private String password ;
	
	@Column(name = "address" , length = 500)
	@NotNull
	@NotBlank(message = "حقل العنوان لا يمكن ان يكون فارغ")
	private String userAddress;
	
	@Column(name="active")
	private boolean active = true;
	
	
	@ManyToMany(fetch = FetchType.EAGER , 
			    cascade = CascadeType.ALL)
	@JoinTable(
    name = "user_role",
	joinColumns = @JoinColumn (name="user_id"),
	inverseJoinColumns = @JoinColumn (name="role_id"))
	private List<Role> roles   = new ArrayList();
	
	
}
