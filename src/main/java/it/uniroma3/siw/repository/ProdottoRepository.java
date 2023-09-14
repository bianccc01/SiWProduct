package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto,Long> {
	
	public boolean existsByNome(String nome);
	
	public Iterable<Prodotto> findAll();
	
	public List<Prodotto> findByNomeContainingIgnoreCase(String nome);
	
	public Prodotto findByNome(String nome);
	
	public List<Prodotto> findByFornitori(Fornitore fornitore);


	
	

}
