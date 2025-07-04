package org.siva.datingapp.service;

import org.siva.datingapp.entity.User;
import org.siva.datingapp.util.UserGender;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
	ResponseEntity<?> saveUser(User user);
	ResponseEntity<?> saveAllUsers(List<User> users);
	ResponseEntity<?> findById(Long id);
	ResponseEntity<?> findByEmail(String email);
	ResponseEntity<?> findByName(String name);
	ResponseEntity<?> findByGender(UserGender gender);
	ResponseEntity<?> findAllUsers();
	ResponseEntity<?> deleteUser(Long id);
	ResponseEntity<?> findBestMatch(Long id,int top);
	ResponseEntity<?> findAllMaleUsers();
	ResponseEntity<?> findAllFemaleUsers();
	ResponseEntity<?> login(String email, String password);
	
}
