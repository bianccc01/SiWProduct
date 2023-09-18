package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;

@Component
public class CredentialsValidator implements Validator {
	
	@Autowired
	private CredentialsRepository credentialsRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Credentials credentials = (Credentials)o;
		if (credentials.getUsername()!=null && credentialsRepository.existsByUsername(credentials.getUsername())) {
			errors.reject("username.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Credentials.class.equals(aClass);
	}

}
