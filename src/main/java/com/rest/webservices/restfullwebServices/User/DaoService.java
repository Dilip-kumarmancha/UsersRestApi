package com.rest.webservices.restfullwebServices.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;


@Component
public class DaoService {
	public static List<Users> users=new ArrayList<>();
	public static Integer UserCnt=0;
	static {
		users.add(new Users(++UserCnt,"Kaisen",LocalDate.now().minusYears(20)));
		users.add(new Users(++UserCnt,"gojo",LocalDate.now().minusYears(30)));
		
	}
	

	public List<Users> findAll(){
		return users;
	}
	
	
	public Users findOne( int id) {
//		return users.get(id); this may cause error if list not have index with same id so not recommandable
		Predicate<? super Users> predicate=user -> user.getId()==id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
 //	@PostMapping("/users/{User")
	public Users save(Users user) {
		user.setId(++UserCnt);
		users.add(user);
		return user;
		
		
	}
	public void deleteByid( int id) {
//		return users.get(id); this may cause error if list not have index with same id so not recommandable
		Predicate<? super Users> predicate=user -> user.getId()==id;
		users.removeIf(predicate);
	}

}
