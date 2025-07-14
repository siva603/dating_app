package org.siva.datingapp.controller;
import java.util.List;
import java.util.Map;

import org.siva.datingapp.Application;
import org.siva.datingapp.entity.User;
import org.siva.datingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "https://love-match-taupe.vercel.app")

@RestController
public class UserController {

    private final Application application;
	
	@Autowired
	private UserService userService;

    UserController(Application application) {
        this.application = application;
    }

	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		System.out.println(user);
		return userService.saveUser(user);
	}
	
	@PostMapping("/users/all")
	public ResponseEntity<?> saveAllUsers(@RequestBody List<User> users){
		return userService.saveAllUsers(users);
	}
	
	@GetMapping("/users/gender/male")
	public ResponseEntity<?> findAllMaleUsers(){
		return userService.findAllMaleUsers();
	}
	
	
	@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
		return userService.findAllFemaleUsers();
	}
	
	@GetMapping("/users/best-match/{id}/{top}")
	public ResponseEntity<?> findBestMatch(@PathVariable Long id, @PathVariable int top){
		return userService.findBestMatch(id,top);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> findAll(){
		return userService.findAllUsers();
	}
	
	@GetMapping("/users/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return userService.findById(id);
	}
	
	@DeleteMapping("/users/id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return userService.deleteUser(id);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
	    String email = credentials.get("email");
	    String password = credentials.get("password");
	    System.out.println("email : "+email);
	    System.out.println("password : "+password);
	    return userService.login(email, password);
	}

}
