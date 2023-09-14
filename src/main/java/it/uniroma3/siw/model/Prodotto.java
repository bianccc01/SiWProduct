package it.uniroma3.siw.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private int prezzo;
	
	@NotBlank
	private String descrizione;

	@ManyToMany
	private List<Fornitore> fornitori;
	
	@OneToMany(mappedBy = "prodotto")
	private List<Commento> commenti;

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

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrrizione() {
		return descrizione;
	}

	public void setDescrrizione(String descrrizione) {
		this.descrizione = descrrizione;
	}

	public List<Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	
	
	
	

	









}
