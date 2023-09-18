package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Component
public class CommentoValidator implements Validator {
	
	@Autowired
	private CommentoRepository commentoRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Commento commento = (Commento)o;
		if (commento.getUser()!=null && commento.getProdotto()!=null 
				&& commentoRepository.existsByUserAndProdotto(commento.getUser(),commento.getProdotto())) {
			errors.reject("commento.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Commento.class.equals(aClass);
	}

}
