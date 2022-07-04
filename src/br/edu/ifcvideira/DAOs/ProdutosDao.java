package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Produtos;
import br.edu.ifcvideira.utils.Conexao;

public class ProdutosDao{
	
	public void CadastrarProduto(Produtos pr) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO produtos (descricao_produto, preco_compra_produto, preco_venda_produto, observacao_produto, categorias_id_categorias, fornecedores_id_fornecedor, marca_id_marca, funcionarios_id_funcionario) VALUES (?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			
			
			sqlPrep.setString(1, pr.getDescricaoProduto());
			sqlPrep.setDouble(2, pr.getCompraProduto());
			sqlPrep.setDouble(3, pr.getVendaProduto());
			sqlPrep.setString(4, pr.getObservacaoProduto());
			sqlPrep.setInt(5, pr.getIdCategorias());
			sqlPrep.setInt(6, pr.getFornecedoresIdFornecedor());
			sqlPrep.setInt(7, pr.getMarcaIdMarca());
			sqlPrep.setInt(8, pr.getFuncionariosIdFuncionario());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProdutos(Produtos pr) throws Exception {
		try{
			String sql = "UPDATE produtos SET descricao_produto=?, preco_compra_produto=?, preco_venda_produto=?, observacao_produto=?, categorias_id_categorias=?, fornecedores_id_fornecedor=?, marca_id_marca=? ,funcionarios_id_funcionario=?  WHERE id_produto=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pr.getDescricaoProduto());
			sqlPrep.setDouble(2, pr.getCompraProduto());
			sqlPrep.setDouble(3, pr.getVendaProduto());
			sqlPrep.setString(4, pr.getObservacaoProduto());
			sqlPrep.setInt(5, pr.getIdCategorias());
			sqlPrep.setInt(6, pr.getFornecedoresIdFornecedor());
			sqlPrep.setInt(7, pr.getMarcaIdMarca());
			sqlPrep.setInt(8, pr.getFuncionariosIdFuncionario());
			sqlPrep.setInt(9, pr.getIdProduto());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarProdutos(Produtos pr) throws Exception{
		try{
			String sql = "DELETE FROM produtos WHERE id_produto=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pr.getIdProduto());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> produtos = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM produtos";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
				produtos.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return produtos;
	}
	
	public int RetornarProximoCodigoProduto() throws Exception {
		try{
			String sql ="SELECT MAX(id_produto)+1 AS id_produto FROM produtos ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id_produto");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}

    }
	public String RetornarNomeProduto(int idProduto) throws Exception {
        try{
            String sql ="SELECT descricao_produto FROM produtos where id_produto=?";
            PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
            sqlPrep.setInt(1, idProduto);
            
            ResultSet rs = sqlPrep.executeQuery();

            if (rs.next()){
                return rs.getString("descricao_produto");
            }else{
                return "";
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "Erro02";
        }
    }
}