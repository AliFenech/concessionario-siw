package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Produttore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProduttore;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String nazionalita;
	
	@OneToMany(mappedBy= "produttore")
	private List<Modello> modello;

	public Long getIdProduttore() {
		return idProduttore;
	}

	public void setIdProduttore(Long idProduttore) {
		this.idProduttore = idProduttore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	
	
	public List<Modello> getModello() {
		return modello;
	}

	public void setModello(List<Modello> modello) {
		this.modello = modello;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nazionalita, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produttore other = (Produttore) obj;
		return Objects.equals(nazionalita, other.nazionalita) && Objects.equals(nome, other.nome);
	}
	
	
	
}

