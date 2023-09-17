package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto,Long> {
	
	public boolean existsByNome(String nome);
	
	public Iterable<Prodotto> findAll();
	
	public List<Prodotto> findByNomeContainingIgnoreCase(String nome);
	
	public Prodotto findByNome(String nome);
	
	public List<Prodotto> findByFornitori(Fornitore fornitore);
	
	@Query("SELECT AVG(c.voto) FROM Commento c WHERE c.prodotto.id = :prodottoId")
    Float findAverageVotiByProdottoId(@Param("prodottoId") Long prodottoId);


	
	

}


