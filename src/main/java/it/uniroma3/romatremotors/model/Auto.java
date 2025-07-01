package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String targa;
	
	@Column(nullable = false)
	private Float km;
	
	@Column(nullable = false)
	private String modello;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private String colore;
	
	@Column(nullable = false)
	private Float prezzo;
	
	@Column(nullable = false)
	private String carburante;
	
	@Column(nullable = false)
	private String categoria;
	
	@Lob
	private byte[] immagine;
	
	@ManyToOne(optional = true)
	private Utente proprietario;
	
	@OneToMany(mappedBy= "auto")
	private List<TestDrive> testDrive;

	@ManyToMany
	private List<AutoOptional> optionalAuto;
	
	@ManyToOne
	private Utente dipendente;
	
	@ManyToOne
	private PuntoVendita puntoVendita;
	
	
	
	
	/**
	 * @return the modello
	 */
	public String getModello() {
		return modello;
	}

	/**
	 * @param modello the modello to set
	 */
	public void setModello(String modello) {
		this.modello = modello;
	}

	/**
	 * @return the dipendente
	 */
	public Utente getDipendente() {
		return dipendente;
	}

	/**
	 * @param dipendente the dipendente to set
	 */
	public void setDipendente(Utente dipendente) {
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
	public Utente getProprietario() {
		return proprietario;
	}

	/**
	 * @param proprietario the proprietario to set
	 */
	public void setProprietario(Utente proprietario) {
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

	
	
	/**
	 * @return the immagine
	 */
	public byte[] getImmagine() {
		return immagine;
	}

	/**
	 * @param immagine the immagine to set
	 */
	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

	
	
	
	/**
	 * @return the carburante
	 */
	public String getCarburante() {
		return carburante;
	}

	/**
	 * @param carburante the carburante to set
	 */
	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}

	
	
	
	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	


	@Override
	public int hashCode() {
		return Objects.hash(carburante, categoria, colore, km, marca, modello, targa);
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
		return Objects.equals(carburante, other.carburante) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(colore, other.colore) && Objects.equals(km, other.km)
				&& Objects.equals(marca, other.marca) && Objects.equals(modello, other.modello)
				&& Objects.equals(targa, other.targa);
	}

	@Override
	public String toString() {
		return "Auto [autoId=" + id + "]";
	}


	
	
	
}
