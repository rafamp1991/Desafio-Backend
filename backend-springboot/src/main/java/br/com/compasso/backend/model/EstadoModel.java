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
	private long id_cidade;
	
	private String nome;
	private String uf;

	public long getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(long id_cidade) {
		this.id_cidade = id_cidade;
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
