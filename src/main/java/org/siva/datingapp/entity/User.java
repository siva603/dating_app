package org.siva.datingapp.entity;

import java.util.List;

import org.siva.datingapp.util.UserGender;
import org.siva.datingapp.util.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true, nullable = false)
  private String email;
  
  private String pic;
  
  private Long phone;

  @Column(nullable = false)
  private String password; // Store hashed password

  @Enumerated(EnumType.STRING)
  private UserGender gender;

  private int age;

  @ElementCollection
  private List<String> intrests;

  @Enumerated(EnumType.STRING)
  private UserRole role = UserRole.USER;
}
