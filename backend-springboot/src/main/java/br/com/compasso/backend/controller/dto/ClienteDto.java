package br.com.compasso.backend.controller.dto;

import java.util.Date;

import br.com.compasso.backend.model.CidadeModel;

public class ClienteDto {

	private long id;
	private String nome;
	private String sobrenome;
	private String sexo;
	private Date dataDeNascimento;
	private int idade;
	private CidadeModel cidadeNome;
	
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
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public CidadeModel getCidadeNome() {
		return cidadeNome;
	}

	public void setCidadeNome(CidadeModel cidadeNome) {
		this.cidadeNome = cidadeNome;
	}
}
