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
		Prodotto prodotto = this.prodottoService.findProdottoeById(idProdotto);
		
		if(prodotto!=null) {
			model.addAttribute("commento", new Commento());
			model.addAttribute("prodotto", prodotto);
		}
		return "authenticated/formNewCommento.html";
	}

	@PostMapping("/authenticated/newCommento/{idProdotto}")
	public String newCommento(@ModelAttribute("commento") Commento commento, @PathVariable("idProdotto") Long idProd, Model model){

		Prodotto prod = this.prodottoService.findProdottoeById(idProd);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userService.getUserAuthentication(authentication);
		commento.setProdotto(prod);
		commento.setUtente(user);
		this.commentoService.saveCommento(commento,prod , user);
		model.addAttribute("prodotto", prod);
		model.addAttribute("commentoUtente",this.commentoService.getCommentoUtente(authentication, prod));
		model.addAttribute("commenti",this.commentoService.getCommentiNotUtente(authentication, prod));
		
		return "guest/prodotto.html";
	}


	@GetMapping("/authenticated/rimuoviCommento/{comId}/{prodId}")
	public String removeCommento(@PathVariable("comId") Long comId, Model model) {
		Commento commento=this.commentoService.findCommentoById(comId);
		this.commentoService.rimuoviCommento(commento);
		return "guest/commento.html";
		
	}




}
