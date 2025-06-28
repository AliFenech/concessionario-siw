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
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;


@Entity
public class Utente {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
    @SequenceGenerator(name = "utente_generator", sequenceName = "hibernate_sequence", allocationSize = 1)
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
	private List<Auto> autoAcquistate;
	
	@OneToMany(mappedBy= "dipendente")
	private List<Auto> autoInserite;
	
	@OneToMany(mappedBy= "cliente")
	private List<TestDrive> testdriveCliente;
	
	@OneToMany(mappedBy= "dipendente")
	private List<TestDrive> testdriveDipendente;

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
	 * @return the autoAcquistate
	 */
	public List<Auto> getAutoAcquistate() {
		return autoAcquistate;
	}

	/**
	 * @param autoAcquistate the autoAcquistate to set
	 */
	public void setAutoAcquistate(List<Auto> autoAcquistate) {
		this.autoAcquistate = autoAcquistate;
	}

	/**
	 * @return the autoInserite
	 */
	public List<Auto> getAutoInserite() {
		return autoInserite;
	}

	/**
	 * @param autoInserite the autoInserite to set
	 */
	public void setAutoInserite(List<Auto> autoInserite) {
		this.autoInserite = autoInserite;
	}

	/**
	 * @return the testdriveCliente
	 */
	public List<TestDrive> getTestdriveCliente() {
		return testdriveCliente;
	}

	/**
	 * @param testdriveCliente the testdriveCliente to set
	 */
	public void setTestdriveCliente(List<TestDrive> testdriveCliente) {
		this.testdriveCliente = testdriveCliente;
	}

	/**
	 * @return the testdriveDipendente
	 */
	public List<TestDrive> getTestdriveDipendente() {
		return testdriveDipendente;
	}

	/**
	 * @param testdriveDipendente the testdriveDipendente to set
	 */
	public void setTestdriveDipendente(List<TestDrive> testdriveDipendente) {
		this.testdriveDipendente = testdriveDipendente;
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
		Utente other = (Utente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataNascita, other.dataNascita)
				&& Objects.equals(email, other.email) && Objects.equals(nome, other.nome);
	}
	
	
}
