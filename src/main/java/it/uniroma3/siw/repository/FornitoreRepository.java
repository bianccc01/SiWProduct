package it.uniroma3.siw.repository;


import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Fornitore;

public interface FornitoreRepository extends CrudRepository<Fornitore,Long> {
	
	public boolean existsByNome(String nome);
	
	public Set<Fornitore> findAll();
	

}
