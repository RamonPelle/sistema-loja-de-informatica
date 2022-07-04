package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Categorias;
import br.edu.ifcvideira.utils.Conexao;

public class CategoriasDao{
	
	public void CadastrarCategorias(Categorias ct) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO categorias (descricao_categorias, margem_lucro_categorias) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, ct.getDescricaoCategorias());
			sqlPrep.setDouble(2,ct.getMargemLucroCategorias());
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

	public void AlterarCategorias(Categorias ct) throws Exception {
		try{
			String sql = "UPDATE categorias SET descricao_categorias=?, margem_lucro_categorias=? WHERE id_categorias=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, ct.getDescricaoCategorias());
			sqlPrep.setDouble(2,ct.getMargemLucroCategorias());
			sqlPrep.setInt(3, ct.getIdCategorias());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCategorias(Categorias ct) throws Exception{
		try{
			String sql = "DELETE FROM categorias WHERE id_categorias=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, ct.getIdCategorias());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> categorias = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM categorias";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3)};
				categorias.add(linha);//?
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return categorias;
	}
	
	public void buscarIdCategorias(Categorias ct) throws Exception {
		try{
			String sql = "SELECT id_categorias from categorias";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, ct.getIdCategorias());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public int RetornarProximoCodigoCategoria() throws Exception {
		try{
			String sql ="SELECT MAX(id_categorias)+1 AS id_categorias FROM categorias ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_categorias");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
	
	public String RetornarNomeCategorias(int idCategorias) throws Exception {
        try{
            String sql ="SELECT descricao_categorias FROM categorias where id_categorias=?";
            PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
            sqlPrep.setInt(1, idCategorias);
            
            ResultSet rs = sqlPrep.executeQuery();

            if (rs.next()){
                return rs.getString("descricao_categorias");
            }else{
                return "";
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "Erro02";
        }
	}
    	public Double RetornarMargemLucro(int idCategorias) throws Exception {
            try{
                String sql ="SELECT margem_lucro_categorias FROM categorias where id_categorias=?";
                PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
                sqlPrep.setInt(1, idCategorias);
                
                ResultSet rs = sqlPrep.executeQuery();

                if (rs.next()){
                    return rs.getDouble("margem_lucro_categorias");
                }else{
                    return null;
                }

            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return null;
            }
    }
}