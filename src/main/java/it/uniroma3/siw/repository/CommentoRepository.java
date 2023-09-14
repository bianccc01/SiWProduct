package it.uniroma3.siw.repository;


import java.util.List;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.model.User;


public interface CommentoRepository extends CrudRepository<Commento,Long> {

	public boolean existsByUserAndProdotto(User utente, Prodotto p);

	public Set<Commento> findAll();

	public List<Commento> findByProdotto(Prodotto p);

	public Commento findByUserAndProdotto(User user, Prodotto p);

	public List<Commento> findByProdottoAndUserNot(Prodotto p, User user);

	public List<Commento> findFirst2ByProdottoAndUserNot(Prodotto p,User user);

	public List<Commento> findFirst3ByProdotto(Prodotto p);

}
