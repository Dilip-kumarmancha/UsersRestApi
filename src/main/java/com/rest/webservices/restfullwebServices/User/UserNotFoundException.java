package com.rest.webservices.restfullwebServices.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
// it is used to manage the the white label page error by providing the msg and id as description in the error page
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
		
	}

	

}
