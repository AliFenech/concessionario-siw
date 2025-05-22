package it.uniroma3.romatremotors.model;

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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
public class Credentials {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String CLIENT_ROLE = "CLIENT";
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Transient
	@Column(nullable = false)
	private String passwordConfirm;
	
	@Column(nullable = false)
	private String ruolo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dipendente dipendente;

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
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the dipendente
	 */
	public Dipendente getDipendente() {
		return dipendente;
	}

	/**
	 * @param dipendente the dipendente to set
	 */
	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
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
	 * @return the clientRole
	 */
	public static String getClientRole() {
		return CLIENT_ROLE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruolo, username);
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
	 // Validazione password
    public boolean isPasswordConfirmed() {
        return this.password != null && this.password.equals(this.passwordConfirm);
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
		return Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username);
	}

	
	
}
