package org.siva.datingapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.siva.datingapp.dao.UserDao;
import org.siva.datingapp.dto.MatchingUsers;
import org.siva.datingapp.entity.User;
import org.siva.datingapp.util.JwtUtil;
import org.siva.datingapp.util.UserGender;
import org.siva.datingapp.util.UserSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private  UserDao userDao;

    @Override
    public ResponseEntity<?> saveUser(User user) {
    	
         User saveUser = userDao.saveUser(user);
         return ResponseEntity.status(HttpStatus.OK).body(saveUser);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
         Optional<User> optional = userDao.findById(id);
         if(optional.isEmpty())
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id ");
         return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @Override
    public ResponseEntity<?>findByEmail(String email) {
         Optional<User> optional = userDao.findByEmail(email);
         if(optional.isEmpty())
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id ");
         return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @Override
    public ResponseEntity<?> findAllUsers() {
    	 List<User> allUsers = userDao.findAllUsers();
    	if(allUsers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
    	Optional<User> optional = userDao.findById(id);
    	if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id ");
        userDao.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
    }

	@Override
	public ResponseEntity<?> saveAllUsers(List<User> users) {
		 List<User> saveAllUsers = userDao.saveAllUsers(users);
		 return ResponseEntity.status(HttpStatus.OK).body(saveAllUsers);
	}

	@Override
	public ResponseEntity<?> findByName(String name) {
		Optional<User> optional = userDao.findByName(name);
		if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id ");
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}

	@Override
	public ResponseEntity<?> findByGender(UserGender gender) {
		List<User> users = userDao.findByGender(gender);
		if(users.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
        return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	public ResponseEntity<?> findBestMatch(Long id, int top) {
		
		Optional<User> optional = userDao.findById(id);
		
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid user id unable to find besty match for this id");
		}
		
		User user =optional.get();
		
		// 1st find all users which opposite gender of user
		
		List<User> users = null;
		if(user.getGender().equals(UserGender.MALE)) {
			users = userDao.findAllFemaleUsers();
		}
		else
			users = userDao.findAllMaleUsers();
		
		// 2nd find age difference and interest match count
		
		List<MatchingUsers> list = new ArrayList<>();
		List<String> intrests = user.getIntrests();
		for(User u : users) {
			
			MatchingUsers m = new MatchingUsers();
			m.setId(u.getId());
			m.setName(u.getName());
			m.setAge(u.getAge());
			m.setEmail(u.getEmail());
			m.setPhone(u.getPhone());
			m.setIntrests(u.getIntrests());
			
			m.setAgeDifference(Math.abs(u.getAge() - user.getAge()));
			List<String> intrests2 = u.getIntrests();
			int c =0;
			for(String s : intrests) {
				if(intrests2.contains(s))
					c++;
			}
			m.setMatchInterestCount(c);
			list.add(m);
		}
		
		Collections.sort(list,new UserSorting());
		
		// now list contains matching list but we should return only top number of users
		// if top = 3
		List<MatchingUsers> result = new ArrayList();
		for(MatchingUsers u : list) {
			
			if(top==0) {
				break;
			}
			else {
				result.add(u);
				top--;
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<?> findAllMaleUsers() {
		 	List<User> allUsers = userDao.findAllMaleUsers();
	    	if(allUsers.isEmpty())
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
	        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}

	@Override
	public ResponseEntity<?> findAllFemaleUsers() {
		List<User> allUsers = userDao.findAllFemaleUsers();
    	if(allUsers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}

	@Override
	@PostMapping("/users/login")
	public ResponseEntity<?> login(String email,String password) {
	    

	    Optional<User> optional = userDao.findByEmail(email); // Use a method that returns Optional<User> without ResponseEntity

	    if (optional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }

	    User user = optional.get();

	    if (!user.getPassword().equals(password)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }

	    // Generate token
	    String token = JwtUtil.generateToken(user.getEmail(), user.getRole().name());

	    // Remove sensitive data
	    user.setPassword(null);

	    // Return user and token
	    Map<String, Object> response = new HashMap<>();
	    response.put("user", user);
	    response.put("token", token);

	    return ResponseEntity.ok(response);
	}

}
