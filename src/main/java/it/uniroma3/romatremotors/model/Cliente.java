package it.uniroma3.romatremotors.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codiceFiscale;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
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

	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@Column(nullable = false)
	private String email;
	
	@OneToMany(mappedBy= "proprietario")
	private List<Auto> auto;
	
	@OneToMany(mappedBy= "cliente")
	private List<TestDrive> testdrive;

	/**
	 * @param auto the auto to set
	 */
	public void setAuto(List<Auto> auto) {
		this.auto = auto;
	}

	

	/**
	 * @return the codiceFiscale
	 */
	public Long getCodiceFiscale() {
		return codiceFiscale;
	}



	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(Long codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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
	 * @return the dataNascita
	 */
	public LocalDate getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}



	/**
	 * @return the testdrive
	 */
	public List<TestDrive> getTestdrive() {
		return testdrive;
	}

	/**
	 * @param testdrive the testdrive to set
	 */
	public void setTestdrive(List<TestDrive> testdrive) {
		this.testdrive = testdrive;
	}

	/**
	 * @return the auto
	 */
	public List<Auto> getAuto() {
		return auto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, cognome, dataNascita, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(nome, other.nome);
	}
	
	
	
	
}
