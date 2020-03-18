package br.com.compasso.backend.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo para criação do Cliente
 * @author Rafael Martins de Padua
 */
@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClienteModel {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long clienteId;
	
	/**
	 * Nome
	 */
	private String nome;
	
	/**
	 * Sobrenome
	 */
	private String sobrenome;
	
	/**
	 * Sexo
	 */
	private String sexo;
	
	/**
	 * Data de nascimento
	 */
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "datanascimento")
	private LocalDate dataNascimento;
	
	/**
	 * Idade
	 */
	private int idade;
	
	/**
	 * ID da cidade
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cidade")
	private CidadeModel cidadeModel;

	public CidadeModel getCidadeModel(Optional<CidadeModel> optional) {
		return cidadeModel;
	}

	public void setCidadeModel(CidadeModel cidadeModel) {
		this.cidadeModel = cidadeModel;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}
