package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Clientes;
import br.edu.ifcvideira.utils.Conexao;

public class ClientesDao{
	
	public void CadastrarCliente(Clientes cl) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO clientes (nome_cliente, cpf_cliente, telefone_cliente, celular_cliente, endereco_cliente, data_cadastro_cliente, funcionarios_id_funcionario) VALUES (?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNomeCliente());
			sqlPrep.setString(2, cl.getCpfCliente());
			sqlPrep.setString(3, cl.getTelefoneCliente());
			sqlPrep.setString(4, cl.getCelularCliente());
			sqlPrep.setString(5, cl.getEnderecoCliente());
			sqlPrep.setTimestamp(6, cl.getDataCadastroCliente());
			sqlPrep.setInt(7, cl.getFuncionariosIdFuncionario());

			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		finally {
			Conexao.fechar();
		}
	}

	public void AlterarCliente(Clientes cl) throws Exception {
		try{
			String sql = "UPDATE clientes SET nome_cliente=?, cpf_cliente=?, telefone_cliente=?, celular_cliente=?, endereco_cliente=?, data_cadastro_cliente=?, funcionarios_id_Funcionario=? WHERE id_cliente=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNomeCliente());
			sqlPrep.setString(2, cl.getCpfCliente());
			sqlPrep.setString(3, cl.getTelefoneCliente());
			sqlPrep.setString(4, cl.getCelularCliente());
			sqlPrep.setString(5, cl.getEnderecoCliente());
			sqlPrep.setTimestamp(6, cl.getDataCadastroCliente());
			sqlPrep.setInt(7, cl.getFuncionariosIdFuncionario());
			sqlPrep.setInt(8, cl.getIdCliente());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCliente(Clientes cl) throws Exception{
		try{
			String sql = "DELETE FROM clientes WHERE id_cliente=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cl.getIdCliente());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> clientes = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM clientes";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8)};
				clientes.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return clientes;
	}
	
	public int RetornarProximoCodigoCliente() throws Exception { 
		try{
			String sql ="SELECT MAX(id_cliente)+1 AS id_cliente FROM clientes ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_cliente");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
}