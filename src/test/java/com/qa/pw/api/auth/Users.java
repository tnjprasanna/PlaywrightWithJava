package com.qa.pw.api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter

public class Users {
	
		private String FirstName;
		private String Salary;
		private String phoeNum;
		private String location;
		private String email;
		private String age;
		private Skills skills; 
		
	  

}
