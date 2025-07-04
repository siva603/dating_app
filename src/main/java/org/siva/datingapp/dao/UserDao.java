package org.siva.datingapp.dao;


import org.siva.datingapp.entity.User;
import org.siva.datingapp.util.UserGender;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User saveUser(User user);
    List<User> saveAllUsers(List<User> users);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    List<User> findByGender(UserGender gender);
    List<User> findAllUsers();
    void deleteUser(Long id);
	List<User> findAllFemaleUsers();
	List<User> findAllMaleUsers();
}

