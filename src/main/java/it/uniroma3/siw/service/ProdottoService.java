package it.uniroma3.siw.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.repository.ProdottoRepository;

@Service
public class ProdottoService {
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private FornitoreService fornitoreService;
	
	@Autowired
	private CommentoService commentoService;
	
	@Transactional
	public Iterable<Prodotto> allProdotti(){
		return this.prodottoRepository.findAll();
	}
	
	@Transactional
	public Prodotto findProdottoeById(Long id) {
		return this.prodottoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Prodotto> searchProdottiByNome(String nome){
		return this.prodottoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Transactional
	public Prodotto findProdottoByNome(String nome){
		return this.prodottoRepository.findByNome(nome);
	}

	@Transactional
	public void saveProdotto(Prodotto p) {
		this.prodottoRepository.save(p);
	}
	
	@Transactional
	public void removeProdotto(Prodotto prodotto) {
		
		for(Commento c: prodotto.getCommenti()) {
			this.commentoService.rimuoviCommento(c);
		}
		
		for(Fornitore f: prodotto.getFornitori()) {
			f.getProdotti().remove(prodotto);
		}
		
		this.prodottoRepository.delete(prodotto);
	}
	
	@Transactional
	public List<Prodotto> getProdottiFornitore(Long idFornitore){
		return this.prodottoRepository.findByFornitori(this.fornitoreService.findFornitoreById(idFornitore));
	}

	@Transactional
	public void addFornitore(Prodotto prodotto, Fornitore fornitore) {
		prodotto.getFornitori().add(fornitore);
		fornitore.getProdotti().add(prodotto);
		this.fornitoreService.saveFornitore(fornitore);
		this.saveProdotto(prodotto);
		
	}

	@Transactional
	public void rmvFornitore(Prodotto prodotto, Fornitore fornitore) {
		prodotto.getFornitori().remove(fornitore);
		fornitore.getProdotti().remove(prodotto);
		this.fornitoreService.saveFornitore(fornitore);
		this.saveProdotto(prodotto);
		
	}

}
