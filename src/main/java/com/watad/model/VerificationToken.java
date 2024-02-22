package com.watad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
 	
	private long id ;
	
	private String token;
	
	
	private User user ;
	
	private LocalDateTime expireDate ;
	
	
	
	
	
}

