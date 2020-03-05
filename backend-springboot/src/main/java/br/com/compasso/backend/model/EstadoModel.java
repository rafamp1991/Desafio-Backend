package br.com.compasso.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
public class EstadoModel {
	
	@Id
	@GeneratedValue
	private long id_estado;
	
	private String nome;
	private String uf;

	public long getId_estado() {
		return id_estado;
	}

	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
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
}
