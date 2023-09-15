package it.uniroma3.siw.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Fornitore {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "my_entity_sequence", initialValue = 10)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String indirizzo;
	
	@NotBlank
	private String email;

	@ManyToMany
	private List<Prodotto> prodotti;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	
	
	

	









}
