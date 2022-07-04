package br.edu.ifcvideira.beans;

import java.sql.Timestamp;

public class Estoque {
	
	private int idEstoque;
	private double quantidadeEntradaEstoque;
	private double quantidadeSaidaEstoque;
	private Timestamp dataMovimentacaoEstoque;
	private int idProduto;
	public Estoque(){
		
	}
	
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdEstoque() {
		return idEstoque;
	}
	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}
	public double getQuantidadeEntradaEstoque() {
		return quantidadeEntradaEstoque;
	}
	public void setQuantidadeEntradaEstoque(double quantidadeEntradaEstoque) {
		this.quantidadeEntradaEstoque = quantidadeEntradaEstoque;
	}
	public double getQuantidadeSaidaEstoque() {
		return quantidadeSaidaEstoque;
	}
	public void setQuantidadeSaidaEstoque(double quantidadeSaidaEstoque) {
		this.quantidadeSaidaEstoque = quantidadeSaidaEstoque;
	}
	public Timestamp getDataMovimentacaoEstoque() {
		return dataMovimentacaoEstoque;
	}
	public void setDataMovimentacaoEstoque(Timestamp dataMovimentacaoEstoque) {
		this.dataMovimentacaoEstoque = dataMovimentacaoEstoque;
	}
	
	

}
