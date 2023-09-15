package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ProdottoService;

@Controller
public class CatalogoController {
	
	@Autowired 
	private ProdottoService prodottoService;
	
	@Autowired
	private FornitoreService fornitoreService;

	
	@GetMapping("/admin/gestioneCatalogo")
	public String getGestioneCatalogo(Model model) {
		model.addAttribute("prodotti",this.prodottoService.allProdotti());
		model.addAttribute("fornitori",this.fornitoreService.allFornitori());
		return "admin/gestioneCatalogo.html";
	}
	
	@GetMapping("/guest/catalogo/{idFornitore}")
	public String homepage(@PathVariable ("idFornitore") Long idFornitore, Model model) {
		
		if(idFornitore==0) {
			model.addAttribute("prodotti",this.prodottoService.allProdotti());
		}
		
		else {
			model.addAttribute("prodotti",this.prodottoService.getProdottiFornitore(idFornitore));
		}
		
		model.addAttribute("fornitori",this.fornitoreService.allFornitori());
		model.addAttribute("idFornitoreScelto",idFornitore);
		
		return "guest/catalogo.html";
	}
	
	

}
