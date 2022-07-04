package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Funcionarios;
import br.edu.ifcvideira.utils.Conexao;

public class FuncionariosDao{
	
	public void CadastrarFuncionario(Funcionarios fu) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO funcionarios (nome_funcionario, cpf_funcionario, rg_funcionario, telefone_funcionario, celular_funcionario, data_admissao_funcionario) VALUES (?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fu.getNomeFuncionario());
			sqlPrep.setString(2, fu.getCpfFuncionario());
			sqlPrep.setString(3, fu.getRgFuncionario());
			sqlPrep.setString(4, fu.getTelefoneFuncionario());
			sqlPrep.setString(5, fu.getCelularFuncionario());
			sqlPrep.setString(6, fu.getDataAdmissao());
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

	public void AlterarFuncionario(Funcionarios fu) throws Exception {
		try{
			String sql = "UPDATE funcionarios SET nome_funcionario=?, cpf_funcionario=?, rg_funcionario=?, telefone_funcionario=?, celular_funcionario=?, data_admissao_funcionario=? WHERE id_funcionario=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fu.getNomeFuncionario());
			sqlPrep.setString(2, fu.getCpfFuncionario());
			sqlPrep.setString(3, fu.getRgFuncionario());
			sqlPrep.setString(4, fu.getTelefoneFuncionario());
			sqlPrep.setString(5, fu.getCelularFuncionario());
			sqlPrep.setString(6, fu.getDataAdmissao());
			sqlPrep.setInt(7, fu.getIdFuncionario());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarFuncionario(Funcionarios fu) throws Exception{
		try{
			String sql = "DELETE FROM funcionarios WHERE id_funcionario=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, fu.getIdFuncionario());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> funcionarios = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM funcionarios";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
				funcionarios.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return funcionarios;
	}
	
	public int RetornarProximoCodigoFuncionario() throws Exception { 
		try{
			String sql ="SELECT MAX(id_funcionario)+1 AS id_funcionario FROM funcionarios ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_funcionario");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
	public String RetornarNomeFuncionario(int idFuncionario) throws Exception {
        try{
            String sql ="SELECT nome_funcionario FROM funcionarios where id_funcionario=?";
            PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
            sqlPrep.setInt(1, idFuncionario);
            
            ResultSet rs = sqlPrep.executeQuery();

            if (rs.next()){
                return rs.getString("nome_funcionario");
            }else{
                return "";
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "Erro02";
        }
    }
}