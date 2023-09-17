package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CommentoService;
import it.uniroma3.siw.service.ProdottoService;
import it.uniroma3.siw.service.UserService;

@Controller
public class CommentoController {

	@Autowired 
	private CommentoService commentoService;

	@Autowired
	private ProdottoService prodottoService;

	@Autowired
	private UserService userService;


	@GetMapping("/authenticated/formNewCommento/{idProdotto}")
	public String formNewRecensione(@PathVariable("idProdotto") Long idProdotto ,Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		
		if(prodotto!=null) {
			model.addAttribute("commento", new Commento());
			model.addAttribute("prodotto", prodotto);
		}
		return "authenticated/formNewCommento.html";
	}

	@PostMapping("/authenticated/newCommento/{idProdotto}")
	public String newCommento(@ModelAttribute("commento") Commento commento, @PathVariable("idProdotto") Long idProd, Model model){

		Prodotto prod = this.prodottoService.findProdottoById(idProd);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userService.getUserAuthentication(authentication);
		commento.setProdotto(prod);
		commento.setUser(user);
		this.commentoService.saveCommento(commento,prod , user);
		model.addAttribute("prodotto", prod);
		model.addAttribute("commentoUtente",this.commentoService.getCommentoUtente(authentication, prod));
		model.addAttribute("commenti",this.commentoService.getCommentiNotUtente(authentication, prod));
		model.addAttribute("mediaVoto",this.prodottoService.getMediaVotiProdotto(idProd));
		
		return "guest/prodotto.html";
	}


	@GetMapping("/authenticated/rmvCommento/{comId}")
	public String removeCommento(@PathVariable("comId") Long comId, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Commento commento=this.commentoService.findCommentoById(comId);
		Prodotto prodotto = commento.getProdotto();
		this.commentoService.rimuoviCommento(commento);
		model.addAttribute("prodotto",prodotto);
		model.addAttribute("commenti",this.commentoService.getCommentiNotUtente(authentication, prodotto));
		model.addAttribute("mediaVoto",this.prodottoService.getMediaVotiProdotto(prodotto.getId()));
		return "guest/prodotto.html";
		
	}




}
