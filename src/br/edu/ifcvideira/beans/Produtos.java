package br.edu.ifcvideira.beans;

public class Produtos {
	public int idProduto;
	private String descricaoProduto;
	private double compraProduto;
	private double vendaProduto;
	private String observacaoProduto;
	private int idCategorias;
	private int fornecedoresIdFornecedor;
	private int marcaIdMarca;
	private int funcionariosIdFuncionario;
	
	public Produtos() {
		
	}
	
	
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProdutos(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public double getCompraProduto() {
		return compraProduto;
	}
	public double getVendaProduto() {
		return vendaProduto;
	}



	public void setVendaProduto(double vendaProduto) {
		this.vendaProduto = vendaProduto;
	}



	public void setCompraProduto(double compraProduto) {
		this.compraProduto = compraProduto;
	}
	public String getObservacaoProduto() {
		return observacaoProduto;
	}
	public void setObservacaoProduto(String observacaoProduto) {
		this.observacaoProduto = observacaoProduto;
	}
	public int getIdCategorias() {
		return idCategorias;
	}
	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}
	public int getFornecedoresIdFornecedor() {
		return fornecedoresIdFornecedor;
	}
	public void setFornecedoresIdFornecedor(int fornecedoresIdFornecedor) {
		this.fornecedoresIdFornecedor = fornecedoresIdFornecedor;
	}
	public int getMarcaIdMarca() {
		return marcaIdMarca;
	}
	public void setMarcaIdMarca(int marcaIdMarca) {
		this.marcaIdMarca = marcaIdMarca;
	}
	public int getFuncionariosIdFuncionario() {
		return funcionariosIdFuncionario;
	}
	public void setFuncionariosIdFuncionario(int funcionariosIdFuncionario) {
		this.funcionariosIdFuncionario = funcionariosIdFuncionario;
	}
	
	
	
}
