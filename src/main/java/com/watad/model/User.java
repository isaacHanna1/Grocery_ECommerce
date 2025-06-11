package com.watad.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.watad.notification.strategy.NotificationStrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements Observer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;

	@Column(name="name")

	private String userName ="مستخدم";


	@Column(name ="government")
	private String government ="ادخل المحافظة" ;


	@Column(name ="city")
	private String city ="ادخل المدينة";


	@Column(name="phone", unique = true)
	@NotNull
	@NotBlank(message = "حقل رقم الهاتف لا يمكن ان يكون فارغ")
	private String userPhone;

	@Column(name="email" , unique = true)
	private String userEmail ="غير معروف";

	@Column(name="password")
	@NotNull
    @NotBlank(message = "حقل الباسورد لا يمكن ان يكون فارغ")
	private String password ;

	@Column(name = "address" , length = 500)
	private String userAddress ="داخل عنوانك";

	@Column(name = "enabled")
	private boolean active = false ;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER ,
			    cascade = CascadeType.ALL)
	@JoinTable(
    name = "user_role",
	joinColumns = @JoinColumn (name="user_id"),
	inverseJoinColumns = @JoinColumn (name="role_id"))
	private List<Role> roles   = new ArrayList();

	@OneToMany(mappedBy = "user")
	private List<Order> order;

	@Override
	public void notify(NotificationStrategy notifaction, String messageSubject,String message) {
		notifaction.sendNotification(messageSubject,this,message);
	}

}
