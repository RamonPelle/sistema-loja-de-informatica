package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.EstoqueDao;
import br.edu.ifcvideira.DAOs.ProdutosDao;
import br.edu.ifcvideira.beans.Estoque;

import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EstoqueView extends JFrame {

	private JPanel contentPane;
	public static JTextField textNomeProduto;
	private JTable table;
	private JTextField textIdProdutoBusca;
	private JTextField textQtdEntrada;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static JTextField TextIdProd;
	private JTextField textQtdSaida;
	private JTextField textIdEstoque;

	/**
	 * Launch the application.
	 */
	private List<Object> estoque = new ArrayList<Object>();
	
	Estoque es = new Estoque();
	EstoqueDao esDao = new EstoqueDao();
	ProdutosDao prDao = new ProdutosDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstoqueView frame = new EstoqueView();
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
	public EstoqueView() {
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				atualizarTabelaEstoque();
				limparEstoque();
			}
		});
		
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 576, 505);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLimparBusca = new JButton("Limpar");
		btnLimparBusca.setBounds(240, 469, 89, 23);
		panel.add(btnLimparBusca);
		btnLimparBusca.setBackground(SystemColor.controlHighlight);
		btnLimparBusca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblIdProduto_1 = new JLabel("ID produto:");
		lblIdProduto_1.setBounds(167, 436, 106, 20);
		panel.add(lblIdProduto_1);
		lblIdProduto_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdProduto_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textIdProdutoBusca = new JTextField();
		textIdProdutoBusca.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textIdProdutoBusca.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textIdProdutoBusca.getText(), 0));  
				}  
			}
		});
		textIdProdutoBusca.setBounds(285, 436, 109, 20);
		panel.add(textIdProdutoBusca);
		textIdProdutoBusca.setColumns(10);
		
		JLabel lblBuscarMovimentao = new JLabel("Buscar estoque");
		lblBuscarMovimentao.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscarMovimentao.setBounds(240, 403, 109, 20);
		panel.add(lblBuscarMovimentao);
		lblBuscarMovimentao.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 370, 429, 20);
		panel.add(separator);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(226, 319, 89, 23);
		btnAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {

						es.setQuantidadeEntradaEstoque(Double.parseDouble(textQtdEntrada.getText()));
					    es.setQuantidadeSaidaEstoque(Double.parseDouble(textQtdSaida.getText()));						
						es.setIdProduto(Integer.parseInt(TextIdProd.getText()));
						es.setIdEstoque(Integer.parseInt(textIdEstoque.getText()));
						esDao.AlterarEstoque(es);
					} 
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());			
						}
					atualizarTabelaEstoque();
					limparEstoque();
				}
			});
		panel.add(btnAlterar);
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAlterar.setBackground(SystemColor.controlHighlight);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(79, 319, 89, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
							+ table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
				try {
					es.setQuantidadeEntradaEstoque(Double.parseDouble(textQtdEntrada.getText()));
					es.setQuantidadeSaidaEstoque(Double.parseDouble(textQtdSaida.getText()));
					es.setIdEstoque(Integer.parseInt(textIdEstoque.getText()));
					es.setIdProduto(Integer.parseInt(TextIdProd.getText()));


					esDao.deletarEstoque(es);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}
				atualizarTabelaEstoque();
				limparEstoque();	
				
			}
			}});
		panel.add(btnExcluir);
		btnExcluir.setForeground(new Color(204, 0, 0));
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExcluir.setBackground(SystemColor.controlHighlight);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					es.setQuantidadeEntradaEstoque(Double.parseDouble(textQtdEntrada.getText()));
					es.setQuantidadeSaidaEstoque(Double.parseDouble(textQtdSaida.getText()));
					es.setIdEstoque(Integer.parseInt(textIdEstoque.getText()));
					es.setIdProduto(Integer.parseInt(TextIdProd.getText()));
					long time = System.currentTimeMillis();	
					Timestamp timestamp = new Timestamp(time);
					es.setDataMovimentacaoEstoque(timestamp);
					
					esDao.CadastrarEstoque(es);
			
					System.out.println(timestamp);
					
			
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());	
				}
				atualizarTabelaEstoque();
				limparEstoque();
			
				
			}
		});
		btnCadastrar.setBounds(376, 319, 89, 23);
		panel.add(btnCadastrar);
		btnCadastrar.setBackground(SystemColor.controlHighlight);
		btnCadastrar.setForeground(new Color(0, 128, 0));
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 190, 467, 116);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaEstoque();
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Quant. Entrada", "Quant. Saída", "Movimentação", "Id Produto"
				}
			));
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(234, 134, 89, 23);
		panel.add(btnLimpar);
		btnLimpar.setBackground(SystemColor.controlHighlight);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblQuantia = new JLabel("Quantidade entrada:");
		lblQuantia.setBounds(12, 62, 134, 20);
		panel.add(lblQuantia);
		lblQuantia.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantia.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textQtdEntrada = new JTextField();
		textQtdEntrada.setBounds(129, 62, 121, 20);
		panel.add(textQtdEntrada);
		textQtdEntrada.setColumns(10);
		
		JLabel textIdProduto = new JLabel("ID produto:");
		textIdProduto.setBounds(304, 96, 68, 20);
		panel.add(textIdProduto);
		textIdProduto.setHorizontalAlignment(SwingConstants.LEFT);
		textIdProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textNomeProduto = new JTextField();
		textNomeProduto.setBounds(404, 96, 121, 20);
		panel.add(textNomeProduto);
		textNomeProduto.setEditable(false);
		textNomeProduto.setColumns(10);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(251, 29, 59, 20);
		panel.add(lblEstoque);
		lblEstoque.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstoque.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(284, 60, 18, 50);
		panel.add(separator_1);
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EstoqueView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label.setBounds(477, -1, 50, 33);
		panel.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MenuView().setVisible(true);
				dispose();
			}
		});
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(65, 105, 225));
		panel_2.setBounds(528, 0, 50, 34);
		panel.add(panel_2);
		
		JLabel label_1 = new JLabel("X");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.PLAIN, 25));
		label_1.setBackground(Color.BLUE);
		label_1.setBounds(17, 2, 17, 29);
		panel_2.add(label_1);
		
		TextIdProd = new JTextField();
		TextIdProd.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {			
					try{
										
						textNomeProduto.setText(prDao.RetornarNomeProduto(Integer.parseInt((TextIdProd.getText()))));
						} catch(Exception a) {
									      				      
									}
								}
							});
		
		TextIdProd.setColumns(10);
		TextIdProd.setBounds(372, 95, 22, 20);
		panel.add(TextIdProd);
		
		textQtdSaida = new JTextField();
		textQtdSaida.setColumns(10);
		textQtdSaida.setBounds(129, 95, 121, 20);
		panel.add(textQtdSaida);
		
		JLabel lblQuantidadeSada = new JLabel("Quantidade sa\u00EDda:");
		lblQuantidadeSada.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantidadeSada.setFont(new Font("Arial", Font.PLAIN, 12));
		lblQuantidadeSada.setBounds(12, 95, 121, 20);
		panel.add(lblQuantidadeSada);
		
		textIdEstoque = new JTextField();
		textIdEstoque.setEditable(false);
		textIdEstoque.setColumns(10);
		textIdEstoque.setBounds(370, 62, 121, 20);
		panel.add(textIdEstoque);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Arial", Font.PLAIN, 12));
		lblId.setBounds(304, 62, 30, 20);
		panel.add(lblId);
		
		JLabel label_2 = new JLabel("New label");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarProduto bp = new BuscarProduto();
				bp.setVisible(true);
			}
		});
		label_2.setIcon(new ImageIcon(EstoqueView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		label_2.setBounds(537, 98, 16, 16);
		panel.add(label_2);
		

		
	}
	//CLAsse para limpar 
	public void limparEstoque() {
		textQtdEntrada.setText(null);
		textQtdSaida.setText(null);
		textIdEstoque.setText(null);
		TextIdProd.setText(null);

		try {
			textIdEstoque.setText(String.valueOf(esDao.RetornarProximoCodigoEstoque()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	//Calsse para atualizar tabela 
	public void atualizarTabelaEstoque() {
		try {
			estoque = esDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=estoque.size(); x++)
			{
				model.addRow((Object[]) estoque.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}


	//set campos estoque
	public void setCamposFromTabelaEstoque() {
		textIdEstoque.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		textQtdEntrada.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		textQtdSaida.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		TextIdProd.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));

	}
}
