package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Marca;
import br.edu.ifcvideira.utils.Conexao;

public class MarcaDao{
	
	public void CadastrarMarca(Marca mr) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO marca (descricao_marca) VALUES (?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, mr.getDescricaoMarca());

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

	public void AlterarMarca(Marca mr) throws Exception {
		try{
			String sql = "UPDATE marca SET descricao_marca=? WHERE id_marca=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			
			sqlPrep.setString(1, mr.getDescricaoMarca());
			sqlPrep.setInt(2, mr.getIdMarca());
			
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarMarca(Marca mr) throws Exception{
		try{
			String sql = "DELETE FROM marca WHERE id_marca=?";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, mr.getIdMarca());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> marca = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM marca";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2)};
				marca.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return marca;
	}
	
	public int RetornarProximoCodigoMarca() throws Exception {
		try{
			String sql ="SELECT MAX(id_marca)+1 AS id_marca FROM marca ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_marca");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
	public String RetornarNomeMarca(int idMarca) throws Exception {
        try{
            String sql ="SELECT descricao_marca FROM marca where id_marca=?";
            PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
            sqlPrep.setInt(1, idMarca);
            
            ResultSet rs = sqlPrep.executeQuery();

            if (rs.next()){
                return rs.getString("descricao_marca");
            }else{
                return "";
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "Erro02";
        }
    }
}