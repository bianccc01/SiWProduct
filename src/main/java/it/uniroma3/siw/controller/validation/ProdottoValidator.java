package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.ProdottoRepository;

@Component
public class ProdottoValidator implements Validator {
	
	@Autowired
	private ProdottoRepository prodottoRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Prodotto prodotto = (Prodotto)o;
		if (prodotto.getNome()!=null && prodottoRepository.existsByNome(prodotto.getNome())) {
			errors.reject("prodotto.duplicate");
		}
		
		if(prodotto.getNome().length()==0) {
			errors.reject("prodotto.blank");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Prodotto.class.equals(aClass);
	}

}
