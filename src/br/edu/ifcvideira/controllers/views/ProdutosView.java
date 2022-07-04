package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.CategoriasDao;
import br.edu.ifcvideira.DAOs.FornecedoresDao;
import br.edu.ifcvideira.DAOs.FuncionariosDao;
import br.edu.ifcvideira.DAOs.MarcaDao;
import br.edu.ifcvideira.DAOs.ProdutosDao;
import br.edu.ifcvideira.beans.Categorias;
import br.edu.ifcvideira.beans.Marca;
import br.edu.ifcvideira.beans.Produtos;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JCheckBox;

public class ProdutosView extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigoBusca;
	private JTextField textPrecoCompra;
	private JTextField textDescricao;
	private JTextField textObservacao;
	private JTable table;
	public static JTextField textCodigoProduto;
	public static JTextField textDescricaoBusca;
	public static JTextField textIdCat;
	public static JTextField textIdForn;
	public static JTextField textIdMarca;
	public static JTextField textIdFunc;
	public static JTextField textCategoria;
	public static JTextField textFornecedor;
	public static JTextField textMarca;
	public static JTextField textFuncionario;

	/**
	 * Launch the application.
	 */
	private List<Object> Produtos = new ArrayList<Object>();
	
	
	Produtos pr = new Produtos();
	FornecedoresDao foDao = new FornecedoresDao();
	MarcaDao maDao = new MarcaDao();
	CategoriasDao caDao = new CategoriasDao();
	FuncionariosDao fuDao = new FuncionariosDao();
	ProdutosDao prDao = new ProdutosDao();
	private JTextField textPrecoVenda;

	//double precoCompra;
	//double precoVenda;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutosView frame = new ProdutosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public ProdutosView() {
		setBackground(Color.BLUE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaProduto();
				limparProduto();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 749, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 745, 595);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produtos");
		lblProduto.setBounds(329, 16, 67, 17);
		panel.add(lblProduto);
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnLimparBusca = new JButton("Limpar");
		btnLimparBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDescricaoBusca.setText(null);
				textCodigoBusca.setText(null);
				
			}
		});
		btnLimparBusca.setBounds(326, 552, 89, 23);
		panel.add(btnLimparBusca);
		btnLimparBusca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textDescricaoBusca = new JTextField();
		textDescricaoBusca.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro); 
				
				if (textDescricaoBusca.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textDescricaoBusca.getText(), 1));  
				}  
				
			}
		});
		textDescricaoBusca.setBounds(369, 503, 127, 20);
		panel.add(textDescricaoBusca);
		textDescricaoBusca.setColumns(10);
		
		textCodigoBusca = new JTextField();
		textCodigoBusca.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textCodigoBusca.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textCodigoBusca.getText(), 0));  
				} 
			}
		});
		textCodigoBusca.setBounds(369, 472, 127, 20);
		panel.add(textCodigoBusca);
		textCodigoBusca.setColumns(10);
		
		JLabel lblCdigoProduto = new JLabel("Busca Produto");
		lblCdigoProduto.setBounds(322, 446, 109, 14);
		panel.add(lblCdigoProduto);
		lblCdigoProduto.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 431, 683, 20);
		panel.add(separator);
		separator.setForeground(Color.BLACK);
		
		JLabel lblPreo = new JLabel("C\u00F3digo:");
		lblPreo.setBounds(244, 474, 87, 14);
		panel.add(lblPreo);
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDescrioi = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrioi.setBounds(244, 505, 87, 14);
		panel.add(lblDescrioi);
		lblDescrioi.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pr.setIdProdutos(Integer.parseInt(textCodigoProduto.getText()));
					pr.setCompraProduto(Double.parseDouble(textPrecoCompra.getText()));
					pr.setVendaProduto(Double.parseDouble(textPrecoVenda.getText()));
					pr.setDescricaoProduto(String.valueOf(textDescricao.getText()));
					pr.setObservacaoProduto(String.valueOf(textObservacao.getText()));
					pr.setIdCategorias(Integer.parseInt(textIdCat.getText()));
					pr.setFornecedoresIdFornecedor(Integer.parseInt(textIdForn.getText()));
					pr.setMarcaIdMarca(Integer.parseInt(textIdMarca.getText()));
					pr.setFuncionariosIdFuncionario(Integer.parseInt(textIdFunc.getText()));
					prDao.AlterarProdutos(pr);
					
					
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());			
					}
				atualizarTabelaProduto();
				limparProduto();
			}
			});
		btnAlterar.setBounds(329, 395, 89, 23);
		panel.add(btnAlterar);
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() != -1){
						Object[] options3 = {"Excluir", "Cancelar"};
						if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
								+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
								+ table.getValueAt(table.getSelectedRow(), 1), null,
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
					try {
						pr.setIdProdutos(Integer.parseInt(textCodigoProduto.getText()));
						
						
						prDao.deletarProdutos(pr);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
						}
						else{
							JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
						}
					atualizarTabelaProduto();
					limparProduto();	
					
				};
			}});
		btnExcluir.setBounds(88, 395, 89, 23);
		panel.add(btnExcluir);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluir.setForeground(new Color(255, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MenuView().setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setBounds(695, 0, 50, 33);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textPrecoVenda = new JTextField();
		textPrecoVenda.setColumns(10);
		textPrecoVenda.setBounds(224, 105, 60, 20);
		panel.add(textPrecoVenda);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(17, 2, 17, 29);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setVerticalAlignment(SwingConstants.TOP);
		panel_1.add(lblX);
		lblX.setFont(new Font("Arial", Font.PLAIN, 25));
		lblX.setBackground(Color.BLUE);
		JCheckBox cbVenda = new JCheckBox("");
		cbVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbVenda.isSelected() && (textIdCat.getText()!="")) {
					textPrecoVenda.setText("");
					textPrecoVenda.setEditable(true);
			}else {
				textPrecoVenda.setEditable(false);
				try {
					 textPrecoVenda.setText(""+(Double.parseDouble(textPrecoCompra.getText())+Double.parseDouble(textPrecoCompra.getText())
						*caDao.RetornarMargemLucro(Integer.parseInt(textIdCat.getText()))/100));
						
					} catch (Exception b){
						
					}
			}
		}
	});
		cbVenda.setSelected(true);
		cbVenda.setBounds(292, 105, 31, 20);
		panel.add(cbVenda);
		//cadastrar
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(563, 395, 89, 23);
		panel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						pr.setIdProdutos(Integer.parseInt(textCodigoProduto.getText()));
						pr.setCompraProduto(Double.parseDouble(textPrecoCompra.getText()));
						if(cbVenda.isSelected()==true) {
							//pr.setVendaProduto(Double.parseDouble(textPrecoVenda.getText()));
						}else {
							pr.setVendaProduto(Double.parseDouble(textPrecoVenda.getText()));
						}
						
						
						pr.setDescricaoProduto(String.valueOf(textDescricao.getText()));
						pr.setObservacaoProduto(String.valueOf(textObservacao.getText()));
						pr.setIdCategorias(Integer.parseInt(textIdCat.getText()));
						pr.setFornecedoresIdFornecedor(Integer.parseInt(textIdForn.getText()));
						pr.setMarcaIdMarca(Integer.parseInt(textIdMarca.getText()));
						pr.setFuncionariosIdFuncionario(Integer.parseInt(textIdFunc.getText()));

						prDao.CadastrarProduto(pr);
					
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());	
					}
					atualizarTabelaProduto();
					limparProduto();
					
				}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastrar.setForeground(new Color(0, 100, 0));
		
		JScrollPane scrollPaneProduto = new JScrollPane();
	
		scrollPaneProduto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneProduto.setBounds(31, 263, 702, 110);
		panel.add(scrollPaneProduto);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setCamposFromTabelaProduto();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Descrição","R$ Compra", "R$ Venda",  "Observação", "Categoria", "Fornecedor", "Marca", "Funcionário"
			}
		));
		scrollPaneProduto.setViewportView(table);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			limparProduto();
			}
		});
		btnLimpar.setBounds(329, 227, 89, 23);
		panel.add(btnLimpar);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(393, 183, 75, 14);
		panel.add(lblFuncionrio);
		lblFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblMarcaaa = new JLabel("Marca");
		lblMarcaaa.setBounds(393, 145, 75, 14);
		panel.add(lblMarcaaa);
		lblMarcaaa.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(393, 108, 75, 14);
		panel.add(lblFornecedor);
		lblFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblCategorias = new JLabel("Categoria");
		lblCategorias.setBounds(393, 69, 74, 14);
		panel.add(lblCategorias);
		lblCategorias.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textObservacao = new JTextField();
		textObservacao.setBounds(124, 177, 189, 20);
		panel.add(textObservacao);
		textObservacao.setColumns(10);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		lblObservao.setBounds(27, 180, 74, 14);
		panel.add(lblObservao);
		lblObservao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(27, 142, 74, 14);
		panel.add(lblDescrio);
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textDescricao = new JTextField();
		textDescricao.setBounds(124, 139, 189, 20);
		panel.add(textDescricao);
		textDescricao.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("R$ compra / R$ venda:");
		lblNewLabel.setBounds(27, 105, 137, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textPrecoCompra = new JTextField();

		textPrecoCompra.setBounds(158, 105, 60, 20);
		panel.add(textPrecoCompra);
		textPrecoCompra.setColumns(10);
		
		JLabel label = new JLabel("C\u00F3digo Produto");
		label.setBounds(27, 66, 87, 14);
		panel.add(label);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textCodigoProduto = new JTextField();
		textCodigoProduto.setEditable(false);
		textCodigoProduto.setBounds(124, 64, 189, 20);
		panel.add(textCodigoProduto);
		textCodigoProduto.setColumns(10);
		
		textIdCat = new JTextField();
		textIdCat.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
					try{
						textCategoria.setText(caDao.RetornarNomeCategorias(Integer.parseInt((textIdCat.getText())))); 
				} catch(Exception e) {
				      				      
				}
			}
		});
		textIdCat.setBounds(474, 67, 22, 20);
		panel.add(textIdCat);
		textIdCat.setColumns(10);
		
		textIdForn = new JTextField();
		textIdForn.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					
					textFornecedor.setText(foDao.RetornarNomeFornecedor(Integer.parseInt(textIdForn.getText())));
				} catch(Exception e) {
				      				      
				}
			}
		});
		textIdForn.setColumns(10);
		textIdForn.setBounds(474, 104, 22, 20);
		panel.add(textIdForn);
		
		textIdMarca = new JTextField();
		textIdMarca.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try{
					
					textMarca.setText(maDao.RetornarNomeMarca(Integer.parseInt(textIdMarca.getText())));
				} catch(Exception b) {
				      				      
				}
			}
		});
		textIdMarca.setColumns(10);
		textIdMarca.setBounds(474, 141, 22, 20);
		panel.add(textIdMarca);
		
		textIdFunc = new JTextField();
		textIdFunc.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
try{
					
					textFuncionario.setText(fuDao.RetornarNomeFuncionario(Integer.parseInt(textIdFunc.getText())));
				} catch(Exception a) {
				      				      
				}
			}
		});
		textIdFunc.setColumns(10);
		textIdFunc.setBounds(474, 179, 22, 20);
		panel.add(textIdFunc);
		
		textCategoria = new JTextField();
		textCategoria.setEditable(false);
		textCategoria.setColumns(10);
		textCategoria.setBounds(508, 66, 173, 20);
		panel.add(textCategoria);
		
		textFornecedor = new JTextField();
		textFornecedor.setEditable(false);
		textFornecedor.setColumns(10);
		textFornecedor.setBounds(508, 104, 173, 20);
		panel.add(textFornecedor);
		
		textMarca = new JTextField();
		textMarca.setEditable(false);
		textMarca.setColumns(10);
		textMarca.setBounds(508, 142, 173, 20);
		panel.add(textMarca);
		
		textFuncionario = new JTextField();
		textFuncionario.setEditable(false);
		textFuncionario.setColumns(10);
		textFuncionario.setBounds(508, 177, 173, 20);
		panel.add(textFuncionario);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarProduto bp = new BuscarProduto();
				bp.setVisible(true);
			}
		});
		label_2.setIcon(new ImageIcon(ProdutosView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label_2.setBounds(647, 0, 50, 33);
		panel.add(label_2);
		
		JLabel lblCat = new JLabel("New label");
		lblCat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarCategoria bc = new BuscarCategoria();
				bc.setVisible(true);
			}
		});
		lblCat.setIcon(new ImageIcon(ProdutosView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		lblCat.setBounds(693, 68, 16, 16);
		panel.add(lblCat);
		
		JLabel lblForn = new JLabel("New label");
		lblForn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarFornecedores bforn = new BuscarFornecedores();
				bforn.setVisible(true);
			}
		});
		lblForn.setIcon(new ImageIcon(ProdutosView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		lblForn.setBounds(693, 107, 16, 16);
		panel.add(lblForn);
		
		JLabel lblMarca = new JLabel("New label");
		lblMarca.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarMarca bm = new BuscarMarca();
				bm.setVisible(true);
			}
		});
		lblMarca.setIcon(new ImageIcon(ProdutosView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		lblMarca.setBounds(693, 141, 16, 16);
		panel.add(lblMarca);
		
		JLabel lblFunc = new JLabel("New label");
		lblFunc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarFuncionario bfunc = new BuscarFuncionario();
				BuscarFuncionario.flag = 1;
				bfunc.setVisible(true);
			}
		});
		lblFunc.setIcon(new ImageIcon(ProdutosView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		lblFunc.setBounds(693, 182, 16, 16);
		panel.add(lblFunc);

		

		
	
		//metodo Limpar 
	}
	public void limparProduto() {
		
		textCategoria.setText(null);
		textCodigoProduto.setText(null);
		textDescricao.setText(null);
		textDescricaoBusca.setText(null);
		textFornecedor.setText(null);
		textFuncionario.setText(null);
		textIdCat.setText(null);
		textIdForn.setText(null);
		textIdFunc.setText(null);
		textIdMarca.setText(null);
		textMarca.setText(null);
		textObservacao.setText(null);
		textPrecoCompra.setText(null);
		textPrecoVenda.setText(null);
		textCodigoBusca.setText(null);
		
		
		try {
			textCodigoProduto.setText(String.valueOf(prDao.RetornarProximoCodigoProduto()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//metodo atualiazarTabela
	public void atualizarTabelaProduto() {
		try {
			Produtos = prDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=Produtos.size(); x++)
			{
				model.addRow((Object[]) Produtos.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	//set campos 
		public void setCamposFromTabelaProduto() {
			textCodigoProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0 )));
			textDescricao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
			textPrecoCompra.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
			textPrecoVenda.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));			
			textObservacao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
			textIdCat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)));
			textIdForn.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 6)));
			textIdMarca.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 7)));
			textIdFunc.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 8)));
		}
	}
