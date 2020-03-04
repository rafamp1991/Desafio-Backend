package br.com.compasso.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidades")
public class CidadeModel {

	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	
	@ManyToOne
	private EstadoModel estadoModel;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoModel getEstadoModel() {
		return estadoModel;
	}

	public void setEstadoModel(EstadoModel estadoModel) {
		this.estadoModel = estadoModel;
	}
}
