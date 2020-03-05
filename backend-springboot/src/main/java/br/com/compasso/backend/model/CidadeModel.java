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

@Entity
@Table(name = "cidades")
public class CidadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_cidade;
	
	private String nome;
	private Double latitude;
	private Double longitude;
	private Boolean capital;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_estado")
//	private EstadoModel estadoModel;
	
	@Column(name = "id_estado")
	private long estadoId;
	
	public long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}

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

//	public EstadoModel getEstadoModel() {
//		return estadoModel;
//	}
//
//	public void setEstadoModel(EstadoModel estadoModel) {
//		this.estadoModel = estadoModel;
//	}
}
