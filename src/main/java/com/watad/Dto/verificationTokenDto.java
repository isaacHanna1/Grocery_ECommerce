package com.watad.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

import com.watad.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class verificationTokenDto {
	
	private long id;
	private String token ;
	private LocalDateTime expireDate;
	private User user;

}
