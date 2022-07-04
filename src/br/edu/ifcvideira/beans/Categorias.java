package br.edu.ifcvideira.beans;

import java.sql.Timestamp;

public class Categorias {
	private int idCategorias;
	private String descricaoCategorias;
	private double margemLucroCategorias;
	
	public Categorias() {
		
	}
	
	public int getIdCategorias() {
		return idCategorias;
	}
	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}
	public String getDescricaoCategorias() {
		return descricaoCategorias;
	}
	public void setDescricaoCategorias(String descricaoCategorias) {
		this.descricaoCategorias = descricaoCategorias;
	}
	public double getMargemLucroCategorias() {
		return margemLucroCategorias;
	}
	public void setMargemLucroCategorias(double margemLucroCategorias) {
		this.margemLucroCategorias = margemLucroCategorias;
	}
	
	
	
	public void setDataCategorias(Timestamp timestamp) {
		
	}

}
