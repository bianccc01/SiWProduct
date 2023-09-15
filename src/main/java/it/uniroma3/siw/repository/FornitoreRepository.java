package it.uniroma3.siw.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;

public interface FornitoreRepository extends CrudRepository<Fornitore,Long> {
	
	public boolean existsByNome(String nome);
	
	public Set<Fornitore> findAll();
	
	public List<Fornitore> findAllByProdottiNotContaining(Prodotto prodotto);
	

}
