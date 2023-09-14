package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ProdottoService;


@Controller
public class ProdottoController {

	@Autowired 
	private ProdottoService prodottoService;


	@GetMapping("/admin/formNewProdotto")
	public String formNewProdotto(Model model) {
		model.addAttribute("prodotto", new Prodotto());
		return "admin/formNewFornitore.html";
	}

	@PostMapping("/authenticated/newProdotto")
	public String newFornitore(@ModelAttribute("prodotto") Prodotto prodotto, Model model){
		this.prodottoService.saveProdotto(prodotto);
		model.addAttribute("prodotti", this.prodottoService.allProdotti());
		return "guest/prodotti.html";
	}
	
	@GetMapping("/guest/prodotti")
	public String getProdotti(Model model) {
		model.addAttribute("prodotti",this.prodottoService.allProdotti());
		return "guest/prodotti.html";
	}
	
	@GetMapping("/guest/prodottiPerFornitore/{idFornitore}")
	public String getProdottiFornitore(@PathVariable("idFornitore") Long idFornitore, Model model) {
		model.addAttribute("prodotti",this.prodottoService.getProdottiFornitore(idFornitore));
		return "guest/prodotti.html";
	}


	@GetMapping("/admin/rimuoviProdotto/{prodottoId}")
	public String removeFornitore(@PathVariable("prodottoId") Long idProdotto, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoeById(idProdotto);
		this.prodottoService.removeProdotto(prodotto);
		model.addAttribute("Prodotti",this.prodottoService.allProdotti());
		return "guest/prodotti.html";
	}




}
