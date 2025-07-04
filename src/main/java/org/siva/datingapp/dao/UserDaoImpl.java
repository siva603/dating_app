package org.siva.datingapp.dao;

import java.util.List;
import java.util.Optional;

import org.siva.datingapp.entity.User;
import org.siva.datingapp.repository.UserRepository;
import org.siva.datingapp.util.UserGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	@Override
	public List<User> saveAllUsers(List<User> users) {
		return userRepository.saveAll(users);
	}

	@Override
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> findByGender(UserGender gender) {
		return userRepository.findByGender(gender);
	}

	@Override
	public List<User> findAllFemaleUsers() {
		return userRepository.findByGender(UserGender.FEMALE);
	}

	@Override
	public List<User> findAllMaleUsers() {
		return userRepository.findByGender(UserGender.MALE);
	}
}
