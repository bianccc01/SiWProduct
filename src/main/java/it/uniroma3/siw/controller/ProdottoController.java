package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import it.uniroma3.siw.service.CommentoService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ImageService;
import it.uniroma3.siw.service.ProdottoService;


@Controller
public class ProdottoController {

	@Autowired 
	private ProdottoService prodottoService;

	@Autowired
	private FornitoreService fornitoreService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private CommentoService commentoService;

	@Autowired
	private CredentialsService credentialsService;

	@GetMapping("/admin/formNewProdotto")
	public String formNewProdotto(Model model) {
		model.addAttribute("prodotto", new Prodotto());
		return "admin/formNewProdotto.html";
	}

	@PostMapping("/admin/newProdotto")
	public String newProdotto(@ModelAttribute("prodotto") Prodotto prodotto, @RequestParam("file") MultipartFile file, Model model) throws IOException{
		this.imageService.newImagesProd(file, prodotto);
		this.prodottoService.saveProdotto(prodotto);
		return "redirect:/admin/getListaFornitoriProdotto/" + prodotto.getId();
	}

	@GetMapping("/admin/getListaFornitoriProdotto/{idProdotto}")
	public String getListaFornitoriProdotto(@PathVariable("idProdotto") Long idProdotto, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		model.addAttribute("fornitori",this.fornitoreService.getFornitoriDaAggiungere(prodotto));
		model.addAttribute("prodotto",prodotto);
		return "admin/listaFornitoriProdotto.html";
	}


	@GetMapping("/admin/addFornitoreToProdotto/{idProdotto}/{idFornitore}")
	public String addFornitoreProdotto(@PathVariable("idProdotto") Long idProdotto, @PathVariable ("idFornitore") Long idFornitore, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		Fornitore fornitore = this.fornitoreService.findFornitoreById(idFornitore);
		this.prodottoService.addFornitore(prodotto,fornitore);
		return "redirect:/admin/getListaFornitoriProdotto/" + idProdotto;
	}

	@GetMapping("/admin/rmvFornitoreToProdotto/{idProdotto}/{idFornitore}")
	public String rmvFornitoreProdotto(@PathVariable("idProdotto") Long idProdotto, @PathVariable ("idFornitore") Long idFornitore, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		Fornitore fornitore = this.fornitoreService.findFornitoreById(idFornitore);
		this.prodottoService.rmvFornitore(prodotto,fornitore);
		return "redirect:/admin/getListaFornitoriProdotto/" + idProdotto;
	}


	@GetMapping("/guest/prodotti")
	public String getProdotti(Model model) {
		model.addAttribute("prodotti",this.prodottoService.allProdotti());
		return "guest/prodotti.html";
	}

	@GetMapping("/admin/formModificaProdotto/{idProdotto}")
	public String getFormModificaProdotto(@PathVariable("idProdotto") Long idProdotto, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		model.addAttribute("prodotto",prodotto);
		return "admin/formModificaProdotto.html";
	}

	@PostMapping("/admin/editProdotto/{idProdotto}")
	public String editProdotto(@ModelAttribute("prodotto") Prodotto prodottoForm, @PathVariable("idProdotto") Long idProdotto, @RequestParam("file") MultipartFile file,
			Model model) throws IOException {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		this.prodottoService.modificaProdotto(prodotto,prodottoForm);
		this.imageService.newImagesProd(file, prodotto);
		this.prodottoService.saveProdotto(prodotto);
		return "redirect:/guest/prodotto/"+ idProdotto;
	}

	@GetMapping("/guest/prodotto/{idProdotto}")
	public String getProdotto(@PathVariable("idProdotto") Long idProdotto,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		model.addAttribute("credentials",this.credentialsService.getCredentials(authentication));
		model.addAttribute("commentoUtente",this.commentoService.getCommentoUtente(authentication, prodotto));
		model.addAttribute("commenti",this.commentoService.getCommentiNotUtente(authentication, prodotto));
		model.addAttribute("mediaVoto",this.prodottoService.getMediaVotiProdotto(idProdotto));
		model.addAttribute("prodotto",prodotto);
		return "guest/prodotto.html";
	}



	@GetMapping("/admin/rimuoviProdotto/{prodottoId}")
	public String removeFornitore(@PathVariable("prodottoId") Long idProdotto, Model model) {
		Prodotto prodotto = this.prodottoService.findProdottoById(idProdotto);
		this.prodottoService.removeProdotto(prodotto);
		return "redirect:/guest/catalogo/0";
	}



	@PostMapping("/guest/cercaProdotti")
	public String cercaDestinazioni(Model model, @RequestParam String nome) {
		model.addAttribute("prodotti", this.prodottoService.searchProdottiByNome(nome));
		model.addAttribute("fornitori", this.fornitoreService.allFornitori());
		model.addAttribute("idFornitoreScelto",0);
		return "guest/catalogo.html";
	}




}
