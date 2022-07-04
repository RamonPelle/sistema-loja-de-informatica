package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Fornecedores;
import br.edu.ifcvideira.utils.Conexao;

public class FornecedoresDao{
	
	public void CadastrarFornecedor(Fornecedores fo) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO fornecedores (nome_fornecedor, cnpj_fornecedor, email_fornecedor, telefone_fornecedor, endereco_fornecedor) VALUES (?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getNomeFornecedor());
			sqlPrep.setString(2, fo.getCnpjFornecedor());
			sqlPrep.setString(3, fo.getEmailFornecedor());
			sqlPrep.setString(4, fo.getTelefoneFornecedor());
			sqlPrep.setString(5, fo.getEnderecoFornecedor());
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

	public void AlterarFornecedor(Fornecedores fo) throws Exception {
		try{
			String sql = "UPDATE fornecedores SET nome_fornecedor=?, cnpj_fornecedor=?, email_fornecedor=?, telefone_fornecedor=?, endereco_fornecedor=? WHERE id_fornecedor=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getNomeFornecedor());
			sqlPrep.setString(2, fo.getCnpjFornecedor());
			sqlPrep.setString(3, fo.getEmailFornecedor());
			sqlPrep.setString(4, fo.getTelefoneFornecedor());
			sqlPrep.setString(5, fo.getEnderecoFornecedor());
			sqlPrep.setInt(6, fo.getIdFornecedor());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarFornecedor(Fornecedores fo) throws Exception{
		try{
			String sql = "DELETE FROM fornecedores WHERE id_fornecedor=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, fo.getIdFornecedor());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> fornecedores = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM fornecedores";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
				fornecedores.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return fornecedores;
	}
	
	public int RetornarProximoCodigoFornecedor() throws Exception { 
		try{
			String sql ="SELECT MAX(id_fornecedor)+1 AS id_fornecedor FROM fornecedores ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_fornecedor");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
	public String RetornarNomeFornecedor(int idFornecedor) throws Exception {
        try{
            String sql ="SELECT nome_fornecedor FROM fornecedores where id_fornecedor=?";
            PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
            sqlPrep.setInt(1, idFornecedor);
            
            ResultSet rs = sqlPrep.executeQuery();

            if (rs.next()){
                return rs.getString("nome_fornecedor");
            }else{
                return "";
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "Erro02";
        }
    }
}