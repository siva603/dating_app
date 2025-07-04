package org.siva.datingapp.repository;

import java.util.List;
import java.util.Optional;

import org.siva.datingapp.entity.User;
import org.siva.datingapp.util.UserGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);
  List<User> findByGender(UserGender gender);
}

