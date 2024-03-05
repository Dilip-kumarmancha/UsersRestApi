package com.rest.webservices.restfullwebServices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfullwebServices.User.Post;

public interface PostJpaRepository extends JpaRepository<Post, Integer>{

}
