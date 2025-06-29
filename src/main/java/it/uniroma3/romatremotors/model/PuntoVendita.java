package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;
import java.util.Objects.*;
import jakarta.persistence.*;

@Entity
public class PuntoVendita {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String citta;
	
	@Column(nullable=false)
	private String indirizzo;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="puntoVendita")
	private List<Auto> auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	

	public List<Auto> getAuto() {
		return auto;
	}

	public void setAuto(List<Auto> auto) {
		this.auto = auto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auto, citta, indirizzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntoVendita other = (PuntoVendita) obj;
		return Objects.equals(auto, other.auto) && Objects.equals(citta, other.citta)
				&& Objects.equals(indirizzo, other.indirizzo);
	}
	
	
	
}
