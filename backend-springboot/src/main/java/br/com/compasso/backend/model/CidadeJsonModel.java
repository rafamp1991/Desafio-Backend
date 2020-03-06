package br.com.compasso.backend.model;

public class CidadeJsonModel {
	
	private String nome;
	private Double latitude;
	private Double longitude;
	private Boolean capital;
	private long estadoId;
	
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
	
	public long getEstadoId() {
		return estadoId;
	}
	
	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}
}
