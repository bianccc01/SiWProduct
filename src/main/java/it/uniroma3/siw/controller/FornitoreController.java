package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ProdottoService;
@Controller
public class FornitoreController {

	@Autowired 
	private FornitoreService fornitoreService;
	
	@Autowired
	private ProdottoService prodottoService;


	@GetMapping("/admin/formNewFornitore")
	public String formNewFornitore(Model model) {
		model.addAttribute("fornitore", new Fornitore());
		return "admin/formNewFornitore.html";
	}

	@PostMapping("/admin/newFornitore")
	public String newFornitore(@ModelAttribute("fornitore") Fornitore fornitore, Model model){
		this.fornitoreService.saveFornitore(fornitore);
		model.addAttribute("fornitori", this.fornitoreService.allFornitori());
		model.addAttribute("prodotti", this.prodottoService.allProdotti());
		return "admin/gestioneCatalogo.html";
	}


	@GetMapping("/admin/rimuoviFornitore/{fornitoreId}")
	public String removeFornitore(@PathVariable("fornitoreId") Long idFornitore, Model model) {
		Fornitore fornitore = this.fornitoreService.findFornitoreById(idFornitore);
		this.fornitoreService.removeFornitore(fornitore);
		return "redirect:/admin/gestioneCatalogo";
	}
	

	@GetMapping("admin/formModificaFornitore/{fornitoreId}")
	public String formModificaFornitore(@PathVariable("fornitoreId") Long idFornitore, Model model) {
		Fornitore fornitore = this.fornitoreService.findFornitoreById(idFornitore);
		model.addAttribute("fornitore",fornitore);
		return "admin/formModificaFornitore.html";
	}
	
	@PostMapping("/admin/editFornitore/{idFornitore}")
	public String editProdotto(@ModelAttribute("fornitore") Fornitore fornitoreForm, @PathVariable("idFornitore") Long idFornitore, 
			Model model){
		Fornitore fornitore = this.fornitoreService.findFornitoreById(idFornitore);
		this.fornitoreService.modificaFornitore(fornitore,fornitoreForm);
		this.fornitoreService.saveFornitore(fornitore);
		return "redirect:/admin/gestioneCatalogo";
	}
	
	
	@GetMapping("/admin/listafornitori/{idProdotto}")
	public String listaFornitori(@PathVariable("idProdotto") Long idProdotto, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		model.addAttribute("fornitori",this.fornitoreService.getFornitoriDaAggiungere(prodotto));
		model.addAttribute("idProdotto",idProdotto);
		return "admin/listaFornitoriProdotto.html";
	}




}
