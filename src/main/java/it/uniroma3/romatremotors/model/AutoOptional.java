package it.uniroma3.romatremotors.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class AutoOptional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@ManyToMany(mappedBy="optional",fetch = FetchType.EAGER)
	private List<Auto> auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public List<Auto> getAuto() {
		return auto;
	}

	public void setAuto(List<Auto> auto) {
		this.auto = auto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auto, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutoOptional other = (AutoOptional) obj;
		return Objects.equals(auto, other.auto) && Objects.equals(nome, other.nome);
	}

	
	
	
}
