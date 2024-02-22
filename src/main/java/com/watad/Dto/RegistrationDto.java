package com.watad.Dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	
	private long id ; 
	@NotEmpty
	private String userName ;
	private String government ;
	private String city ;
	@NotEmpty
	private String phone;
	private String email;
	@NotEmpty
	private String password ;
	private String address;
	
	
	
	

}
