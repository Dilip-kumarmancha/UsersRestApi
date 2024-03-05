package com.rest.webservices.restfullwebServices.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private DaoService service;
	


	@GetMapping("/users")
	public List<Users> retrieveAll(){
		return service.findAll();
	}
	@GetMapping("/users/{id}") // we can add link that will take us to other resources in RestApi using EntityModela and WebmvcLinkbuild
	public  EntityModel<Users>   findOne(@PathVariable int id){
		Users user= service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:"+id);
		}
		
		EntityModel<Users> entitymodel=EntityModel.of(user);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAll());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
	}
	@PostMapping("/user")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
		Users savedUser=service.save(user);
		// By using below lines of code we can send the url of the person who make 
		//new record to the client verification it will be visible along with the creation status
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id ) {
		service.deleteByid(id);
		
		
	}

}
