package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 50)
	@NotBlank
	private String nome;

	@NotNull
	private int prezzo;
	
	@Size(max = 50)
	@NotBlank
	private String descrizione;
	
	@OneToOne
	private Image image;

	@ManyToMany
	private List<Fornitore> fornitori = new ArrayList<>();
	
	@OneToMany(mappedBy = "prodotto")
	private List<Commento> commenti = new ArrayList<>();

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

	public Image getImage() {
		return this.image;
	}
	
	public String getDataImage() {
		if(image.getDataImage().isEmpty())
			return null;
		else return this.image.getBase64Image();
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
	
	
	
	
	

	









}
