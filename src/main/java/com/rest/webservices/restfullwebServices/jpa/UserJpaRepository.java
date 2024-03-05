package com.rest.webservices.restfullwebServices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfullwebServices.User.Users;

public interface UserJpaRepository extends JpaRepository<Users, Integer> {

}
