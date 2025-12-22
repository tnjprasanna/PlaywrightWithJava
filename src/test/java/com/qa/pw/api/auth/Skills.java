package com.qa.pw.api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
public class Skills {
	
	private String first;
	private String second;
	private String third;

}
