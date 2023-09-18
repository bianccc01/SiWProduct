package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.repository.FornitoreRepository;
import it.uniroma3.siw.model.*;

@Component
public class FornitoreValidator implements Validator {
	
	@Autowired
	private FornitoreRepository fornitoreRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Fornitore fornitore = (Fornitore)o;
		if (fornitore.getNome()!=null && fornitoreRepository.existsByNome(fornitore.getNome())) {
			errors.reject("fornitore.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Fornitore.class.equals(aClass);
	}
}
