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

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("authenticated/userDetails")
	public String getUserDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user",this.userService.getUserAuthentication(authentication));
		return "authenticated/gestioneUtente.html";
		
	}
	
	@GetMapping("/authenticated/formModificaAccount")
	public String getFormModificaAccount(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user",this.userService.getUserAuthentication(authentication));
		return "authenticated/formModificaAccount.html";
	}
	
	@PostMapping("/authenticated/editUser/{idUser}")
	public String editProdotto(@ModelAttribute("user") User userForm, @PathVariable("idUser") Long idUser, 
			Model model){
		User user = this.userService.getUser(idUser);
		this.userService.modificaUser(user, userForm);
		this.userService.saveUser(user);
		return "redirect:/authenticated/userDetails";
	}
	
	
	
	


}
