package br.edu.ifcvideira.beans;

import java.sql.Timestamp;

public class Clientes {
	
	private int idCliente;
	private String nomeCliente;
	private String cpfCliente;
	private String telefoneCliente;
	private String celularCliente;
	private String enderecoCliente;
	private Timestamp dataCadastroCliente;
	private int funcionariosIdFuncionario;
	
	public Clientes() {
		
	}

	
	public String getCpfCliente() {
		return cpfCliente;
	}


	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}


	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getCelularCliente() {
		return celularCliente;
	}

	public void setCelularCliente(String celularCliente) {
		this.celularCliente = celularCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public Timestamp getDataCadastroCliente() {
		return dataCadastroCliente;
	}

	public void setDataCadastroCliente(Timestamp dataCadastroCliente) {
		this.dataCadastroCliente = dataCadastroCliente;
	}

	public int getFuncionariosIdFuncionario() {
		return funcionariosIdFuncionario;
	}

	public void setFuncionariosIdFuncionario(int funcionariosIdFuncionario) {
		this.funcionariosIdFuncionario = funcionariosIdFuncionario;
	}
	
	
	
}
