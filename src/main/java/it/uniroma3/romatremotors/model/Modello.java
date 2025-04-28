
package it.uniroma3.romatremotors.model;

import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Modello {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idModello;
	
	@Column(nullable=false)
	private String nome;
	
	@ManyToOne
	private Produttore produttore;

	public Long getIdModello() {
		return idModello;
	}

	public void setIdModello(Long idModello) {
		this.idModello = idModello;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, produttore);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modello other = (Modello) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(produttore, other.produttore);
	}

	
}

