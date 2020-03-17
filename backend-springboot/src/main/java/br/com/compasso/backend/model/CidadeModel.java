package br.com.compasso.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo para criação da Cidade
 * @author Rafael Martins de Padua
 */
@Entity
@Table(name = "cidades")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CidadeModel {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cidade")
	private Long cidadeId;
	
	/**
	 * Nome
	 */
	private String nome;
	
	/**
	 * Latitude
	 */
	private Double latitude;
	
	/**
	 * Longitude
	 */
	private Double longitude;
	
	/**
	 * Capital
	 */
	private Boolean capital;
	
	/**
	 * ID do estado
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	@JsonBackReference
	private EstadoModel estadoModel;

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public EstadoModel getEstadoModel() {
		return estadoModel;
	}

	public void setEstadoModel(EstadoModel estadoModel) {
		this.estadoModel = estadoModel;
	}
}
