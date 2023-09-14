package it.uniroma3.siw.service;


import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.FornitoreRepository;
import it.uniroma3.siw.repository.ProdottoRepository;

@Service
public class FornitoreService {

	@Autowired
	private FornitoreRepository fornitoreRepository;
	
	@Autowired
	private ProdottoRepository prodottoRepository;

	@Transactional
	public Set<Fornitore> allFornitori(){
		return this.fornitoreRepository.findAll();
	}

	@Transactional
	public Fornitore findFornitoreById(Long id) {
		return this.fornitoreRepository.findById(id).get();
	}

	@Transactional
	public void saveFornitore(Fornitore fornitore) {
		this.fornitoreRepository.save(fornitore);
	}
	
	@Transactional
	public void removeFornitore(Fornitore fornitore) {
		
		for(Prodotto p : fornitore.getProdotti()) {
			p.getFornitori().remove(fornitore);
			this.prodottoRepository.save(p);
		}
		

	}



}
