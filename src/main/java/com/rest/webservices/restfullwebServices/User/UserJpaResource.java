package com.rest.webservices.restfullwebServices.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfullwebServices.jpa.PostJpaRepository;
import com.rest.webservices.restfullwebServices.jpa.UserJpaRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	
	private UserJpaRepository userjparepo;
	
	private PostJpaRepository postjparepo;
	


	public UserJpaResource(UserJpaRepository jparepo, PostJpaRepository postrepo) {
		super();
		this.userjparepo = jparepo;
		this.postjparepo = postrepo;
	}
	@GetMapping("/jpa/users")
	public List<Users> retrieveAll() {
		return userjparepo.findAll();
	}

	@GetMapping("/jpa/users/{id}") // we can add link that will take us to other resources in RestApi using
									// EntityModela and WebmvcLinkbuild
	public EntityModel<Users> findOne(@PathVariable int id) {
		Optional<Users> user = userjparepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}

		EntityModel<Users> entitymodel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
		Users savedUser = userjparepo.save(user);
		// By using below lines of code we can send the url of the person who make
		// new record to the client verification it will be visible along with the
		// creation status
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userjparepo.deleteById(id);

	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostforUser(@PathVariable int id){
		Optional<Users> user=userjparepo.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostforUser(@PathVariable int id,@Valid @RequestBody  Post post){
		Optional<Users> user=userjparepo.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		post.setUser(user.get());
		Post savedpost=postjparepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedpost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}

}
