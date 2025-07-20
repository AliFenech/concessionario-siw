package it.uniroma3.romatremotors.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TestDrive {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime dataEOra;
	
	

	@ManyToOne
	private Credentials credentials;
	
	@ManyToOne
	private Auto auto;

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
	 * @return the dataEOra
	 */
	public LocalDateTime getDataEOra() {
		return dataEOra;
	}

	/**
	 * @param dataEOra the dataEOra to set
	 */
	public void setDataEOra(LocalDateTime dataEOra) {
		this.dataEOra = dataEOra;
	}

	/**
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * @return the auto
	 */
	public Auto getAuto() {
		return auto;
	}

	/**
	 * @param auto the auto to set
	 */
	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auto, credentials, dataEOra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestDrive other = (TestDrive) obj;
		return Objects.equals(auto, other.auto) && Objects.equals(credentials, other.credentials)
				&& Objects.equals(dataEOra, other.dataEOra);
	}

	
	
	
	
}
