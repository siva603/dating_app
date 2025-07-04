package org.siva.datingapp.dto;

import java.util.List;

import org.siva.datingapp.util.UserGender;
import org.siva.datingapp.util.UserRole;

import lombok.Data;

@Data
public class MatchingUsers {

	  private Long id;
	  private String name;
	  private String email;
	  private String pic;
	  private Long phone;
	  private String password; // Store hashed password
	  private UserGender gender;
	  private int age;
	  private List<String> intrests;
	  private UserRole role;
	  
	  private int ageDifference;
	  private int matchInterestCount;
}
