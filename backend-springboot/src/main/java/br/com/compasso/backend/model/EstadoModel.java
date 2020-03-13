package br.com.compasso.backend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "estados")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstadoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private Long estadoId;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "uf")
	private String uf;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_estado")
//	@JsonManagedReference
//	private Set<CidadeModel> cidades;

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

//	public Set<CidadeModel> getCidades() {
//		return cidades;
//	}
//
//	public void setCidades(Set<CidadeModel> cidades) {
//		this.cidades = cidades;
//	}
}
