package it.uniroma3.siw.service;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.repository.ProdottoRepository;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class CommentoService {

	@Autowired
	private CommentoRepository commentoRepository;

	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;

	@Transactional
	public Set<Commento> allCommenti(){
		return this.commentoRepository.findAll();
	}

	@Transactional
	public Commento findCommentoById(Long id) {
		return this.commentoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Commento> allCommentiProdotto(Prodotto prodotto){
		return this.commentoRepository.findByProdotto(prodotto);
	}
	
	@Transactional
	public void saveCommento(Commento commento, Prodotto prodotto, User user) {
		prodotto.getCommenti().add(commento);
		user.getCommenti().add(commento);
		this.prodottoRepository.save(prodotto);
		this.userRepository.save(user);
		this.commentoRepository.save(commento);
	}

	@Transactional
	public Commento getCommentoUtente(Authentication auth, Prodotto prod) {
		User user = this.credentialsService.getUser(auth);
		if(user==null) {
			return null;
		}
		
		return this.commentoRepository.findByUserAndProdotto(user,prod);
	}

	@Transactional
	public void rimuoviCommento(Commento c) {
		c.getProdotto().getCommenti().remove(c);
		c.getUser().getCommenti().remove(c);
		this.commentoRepository.delete(c);
	}
	
	@Transactional
	public List<Commento> getCommentiNotUtente(Authentication auth, Prodotto prod) {
		User user=this.credentialsService.getUser(auth);
		if(user==null) {
			return this.allCommentiProdotto(prod);
		}
		return this.commentoRepository.findByProdottoAndUserNot(prod,user);
	}
	

}
