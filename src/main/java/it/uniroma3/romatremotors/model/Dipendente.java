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
public class Dipendente {
	@Id
	private Long matricola;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@OneToMany(mappedBy="dipendente")
	private List<Auto> auto;

	public Long getMatricola() {
		return matricola;
	}

	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	

	public List<Auto> getAuto() {
		return auto;
	}

	public void setAuto(List<Auto> auto) {
		this.auto = auto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auto, cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(auto, other.auto) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(nome, other.nome);
	}
	
	
	
}
