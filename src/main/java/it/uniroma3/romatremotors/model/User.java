package it.uniroma3.romatremotors.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Utente")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private LocalDate dataDiNascita;
	
	@Column(nullable = false)
	private String citta;
	
	@Column(nullable = false)
	private String indirizzo;
	
	@Column(nullable = false)
	private String numeroDiTelefono;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Credentials credenziali;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dataDiNascita
	 */
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param dataDiNascita the dataDiNascita to set
	 */
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the numeroDiTelefono
	 */
	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	/**
	 * @param numeroDiTelefono the numeroDiTelefono to set
	 */
	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}

	/**
	 * @return the credenziali
	 */
	public Credentials getCredenziali() {
		return credenziali;
	}

	/**
	 * @param credenziali the credenziali to set
	 */
	public void setCredenziali(Credentials credenziali) {
		this.credenziali = credenziali;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citta, cognome, dataDiNascita, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(citta, other.citta) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataDiNascita, other.dataDiNascita) && Objects.equals(nome, other.nome);
	}
	
	
	
}
