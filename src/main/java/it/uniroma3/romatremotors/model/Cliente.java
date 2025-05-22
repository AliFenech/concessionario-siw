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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;


@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
	
	@Column(nullable = false)
	@NotBlank
	private String cognome;
	
	@Column(nullable = false)
	@Past
	private LocalDate dataNascita;
	
	@Column(nullable = false)
	@Email
	private String email;
	
	@OneToMany(mappedBy= "proprietario")
	private List<Auto> auto;
	
	@OneToMany(mappedBy= "cliente")
	private List<TestDrive> testdrive;
	
	
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
	 * @param auto the auto to set
	 */
	public void setAuto(List<Auto> auto) {
		this.auto = auto;
	}

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
		return Objects.hash(cognome, dataNascita, email, nome);
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
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataNascita, other.dataNascita)
				&& Objects.equals(email, other.email) && Objects.equals(nome, other.nome);
	}
	
}
