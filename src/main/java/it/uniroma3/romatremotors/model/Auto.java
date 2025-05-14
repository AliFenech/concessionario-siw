package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Auto {

	@Id
	private String telaio;
	
	@Column(nullable = false)
	private String targa;
	
	@Column(nullable = false)
	private Float km;
	
	@Column(nullable = false)
	private String colore;
	
	@Column(nullable = false)
	private Float prezzo;
	
	@ManyToOne(optional = true)
	private Cliente proprietario;
	
	@OneToMany(mappedBy= "auto")
	private List<TestDrive> testDrive;

	@ManyToMany
	private List<AutoOptional> optionalAuto;
	
	@ManyToOne
	private Dipendente dipendente;
	
	@ManyToOne
	private PuntoVendita puntoVendita;
	
	
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
	 * @return the puntoVendita
	 */
	public PuntoVendita getPuntoVendita() {
		return puntoVendita;
	}

	/**
	 * @param puntoVendita the puntoVendita to set
	 */
	public void setPuntoVendita(PuntoVendita puntoVendita) {
		this.puntoVendita = puntoVendita;
	}

	/**
	 * @return the telaio
	 */
	public String getTelaio() {
		return telaio;
	}

	/**
	 * @param telaio the telaio to set
	 */
	public void setTelaio(String telaio) {
		this.telaio = telaio;
	}

	/**
	 * @return the targa
	 */
	public String getTarga() {
		return targa;
	}

	/**
	 * @param targa the targa to set
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}

	/**
	 * @return the km
	 */
	public Float getKm() {
		return km;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(Float km) {
		this.km = km;
	}

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @param colore the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}

	/**
	 * @return the prezzo
	 */
	public Float getPrezzo() {
		return prezzo;
	}

	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * @return the proprietario
	 */
	public Cliente getProprietario() {
		return proprietario;
	}

	/**
	 * @param proprietario the proprietario to set
	 */
	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}

	

	/**
	 * @return the testDrive
	 */
	public List<TestDrive> getTestDrive() {
		return testDrive;
	}

	/**
	 * @param testDrive the testDrive to set
	 */
	public void setTestDrive(List<TestDrive> testDrive) {
		this.testDrive = testDrive;
	}


	

	/**
	 * @return the optionalAuto
	 */
	public List<AutoOptional> getOptionalAuto() {
		return optionalAuto;
	}

	/**
	 * @param optionalAuto the optionalAuto to set
	 */
	public void setOptionalAuto(List<AutoOptional> optionalAuto) {
		this.optionalAuto = optionalAuto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(telaio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		return Objects.equals(telaio, other.telaio);
	}
	
	
	
}
