package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Estoque;
import br.edu.ifcvideira.utils.Conexao;

public class EstoqueDao{
	
	public void CadastrarEstoque(Estoque es) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO estoque (quantidade_entrada_estoque, quantidade_saida_estoque, data_movimentacao_estoque, produtos_id_produto) VALUES (?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setDouble(1, es.getQuantidadeEntradaEstoque());
			sqlPrep.setDouble(2, es.getQuantidadeSaidaEstoque());
			sqlPrep.setTimestamp(3, es.getDataMovimentacaoEstoque());
			sqlPrep.setInt(4, es.getIdProduto());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarEstoque(Estoque es) throws Exception {
		try{
			String sql = "UPDATE estoque SET quantidade_entrada_estoque=?, quantidade_saida_estoque=?, produtos_id_produto=? WHERE id_estoque=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setDouble(1, es.getQuantidadeEntradaEstoque());
			sqlPrep.setDouble(2, es.getQuantidadeSaidaEstoque());
			sqlPrep.setInt(3, es.getIdProduto());
			sqlPrep.setInt(4, es.getIdEstoque());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarEstoque(Estoque es) throws Exception{
		try{
			String sql = "DELETE FROM estoque WHERE id_estoque=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, es.getIdEstoque());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> estoque = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM estoque";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				estoque.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return estoque;
	}
		
	
	public int RetornarProximoCodigoEstoque() throws Exception {
		try{
			String sql ="SELECT MAX(id_estoque)+1 AS id_estoque FROM estoque ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_estoque");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
}