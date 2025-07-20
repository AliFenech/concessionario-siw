package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
public class Credentials {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String CLIENTE_ROLE = "CLIENTE";
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_generator")
    @SequenceGenerator(name = "credentials_generator", sequenceName = "credentials_sequence", allocationSize = 1)
    private Long id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String passwordEncode;
	
	@Transient
	@Column(nullable = false)
	private String passwordConfirm;
	
	@Column(nullable = false)
	private String ruolo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Utente utente;
	
	@OneToMany(mappedBy= "credentials")
	private List<TestDrive> testdriveCliente;

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return passwordEncode;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.passwordEncode = password;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
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
	 * @return the defaultRole
	 */
	public static String getDefaultRole() {
		return DEFAULT_ROLE;
	}

	/**
	 * @return the adminRole
	 */
	public static String getAdminRole() {
		return ADMIN_ROLE;
	}

	/**
	 * @return the clienteRole
	 */
	public static String getClienteRole() {
		return CLIENTE_ROLE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruolo, username, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username)
				&& Objects.equals(utente, other.utente);
	}

	
	
}
