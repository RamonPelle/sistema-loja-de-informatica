package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.FornecedoresDao;
import br.edu.ifcvideira.beans.Fornecedores;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.ImageIcon;

public class FornecedoresView extends JFrame {

	private JPanel contentPane;
	public JTextField textCodigoFornecedor;
	private JTextField textNomeFornecedor;
	private JTextField textCNPJ;
	private JTextField textEmailFornecedor;

	private List<Object> Fornecedores = new ArrayList<Object>();
	/**
	 * Launch the application.
	 */
	Fornecedores fo = new Fornecedores();
	FornecedoresDao foDao = new FornecedoresDao();
	private JTable table;
	private JTextField textBuscarNomeFornecedor;
	private JTextField textBuscarCodigoFornecedor;
	private JTextField textTelefoneFornecedor;
	private JTextField textEnderecoFornecedor;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FornecedoresView frame = new FornecedoresView();
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
	
	public FornecedoresView() {
		setBackground(Color.BLUE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaFornecedores();
				limparFornecedores();
			}
		});
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		//excluir Fornecedor
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
					fo.setNomeFornecedor(String.valueOf(textNomeFornecedor.getText()));
					fo.setCnpjFornecedor(String.valueOf(textCNPJ.getText()));
					fo.setIdFornecedor(Integer.parseInt(textCodigoFornecedor.getText()));
					fo.setTelefoneFornecedor(String.valueOf(textTelefoneFornecedor.getText()));
					fo.setEmailFornecedor(String.valueOf(textEmailFornecedor.getText()));
					fo.setEnderecoFornecedor(String.valueOf(textEnderecoFornecedor.getText()));

					foDao.deletarFornecedor(fo);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}
				atualizarTabelaFornecedores();
				limparFornecedores();	
				
			}
			}});
		btnExcluir.setForeground(new Color(204, 0, 0));
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluir.setBounds(87, 348, 89, 23);
		contentPane.add(btnExcluir);
		
		
		//alterar Fornecedores
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						fo.setNomeFornecedor(String.valueOf(textNomeFornecedor.getText()));
						fo.setCnpjFornecedor(String.valueOf(textCNPJ.getText()));
						fo.setIdFornecedor(Integer.parseInt(textCodigoFornecedor.getText()));
						fo.setTelefoneFornecedor(String.valueOf(textTelefoneFornecedor.getText()));
						fo.setEmailFornecedor(String.valueOf(textEmailFornecedor.getText()));
						fo.setEnderecoFornecedor(String.valueOf(textEnderecoFornecedor.getText()));

						
						foDao.AlterarFornecedor(fo);
					} 
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());			
						}
					atualizarTabelaFornecedores();
					limparFornecedores();
				}
			});
			
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAlterar.setBounds(243, 348, 89, 23);
		contentPane.add(btnAlterar);
		//cadastrar Fornecedores
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fo.setNomeFornecedor(String.valueOf(textNomeFornecedor.getText()));
					fo.setCnpjFornecedor(String.valueOf(textCNPJ.getText()));
					fo.setIdFornecedor(Integer.parseInt(textCodigoFornecedor.getText()));
					fo.setTelefoneFornecedor(String.valueOf(textTelefoneFornecedor.getText()));
					fo.setEmailFornecedor(String.valueOf(textEmailFornecedor.getText()));
					fo.setEnderecoFornecedor(String.valueOf(textEnderecoFornecedor.getText()));
					foDao.CadastrarFornecedor(fo);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaFornecedores();
				limparFornecedores();
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 128, 0));
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastrar.setBounds(392, 348, 89, 23);
		contentPane.add(btnCadastrar);
		
		JLabel lblFornecedores = new JLabel("Fornecedores");
		lblFornecedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedores.setFont(new Font("Arial", Font.PLAIN, 14));
		lblFornecedores.setBounds(235, 26, 97, 17);
		contentPane.add(lblFornecedores);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo:");
		lblCdigoFuncionrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoFuncionrio.setBounds(38, 75, 54, 23);
		contentPane.add(lblCdigoFuncionrio);
		
		textCodigoFornecedor = new JTextField();
		textCodigoFornecedor.setEditable(false);
		textCodigoFornecedor.setColumns(10);
		textCodigoFornecedor.setBounds(124, 75, 97, 20);
		contentPane.add(textCodigoFornecedor);
		
		JLabel lblNomeFuncionrio = new JLabel("Nome:");
		lblNomeFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNomeFuncionrio.setBounds(38, 107, 54, 23);
		contentPane.add(lblNomeFuncionrio);
		
		textNomeFornecedor = new JTextField();
		textNomeFornecedor.setColumns(10);
		textNomeFornecedor.setBounds(124, 108, 97, 20);
		contentPane.add(textNomeFornecedor);
		
		JLabel lblCpfFuncionrio = new JLabel("CNPJ:");
		lblCpfFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCpfFuncionrio.setBounds(38, 140, 54, 23);
		contentPane.add(lblCpfFuncionrio);
		
		textCNPJ = new JTextField();
		textCNPJ.setColumns(10);
		textCNPJ.setBounds(124, 142, 97, 20);
		contentPane.add(textCNPJ);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmail.setBounds(263, 71, 108, 31);
		contentPane.add(lblEmail);
		
		textEmailFornecedor = new JTextField();
		textEmailFornecedor.setColumns(10);
		textEmailFornecedor.setBounds(392, 76, 97, 20);
		contentPane.add(textEmailFornecedor);
		
		//limpar Fornecedor
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFornecedores();
				
				
			}
		});
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.setBounds(235, 181, 89, 23);
		contentPane.add(btnLimpar);
		
		JScrollPane spFornecedores = new JScrollPane();
		spFornecedores.setBounds(38, 222, 484, 113);
		contentPane.add(spFornecedores);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaFornecedores();
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Código", "Nome", "CNPJ", "Email", "Telefone", "Endereço"
				}
			));
		spFornecedores.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		separator.setBounds(12, 384, 523, 11);
		contentPane.add(separator);
		
		textBuscarNomeFornecedor = new JTextField();
		textBuscarNomeFornecedor.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro); 
				
				if (textBuscarNomeFornecedor.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscarNomeFornecedor.getText(), 1));  
				}  
				
			}
		});
		textBuscarNomeFornecedor.setColumns(10);
		textBuscarNomeFornecedor.setBounds(192, 460, 301, 20);
		contentPane.add(textBuscarNomeFornecedor);
		
		textBuscarCodigoFornecedor = new JTextField();
		textBuscarCodigoFornecedor.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textBuscarCodigoFornecedor.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textBuscarCodigoFornecedor.getText(), 0));  
				}  
			}
		});
		textBuscarCodigoFornecedor.setColumns(10);
		textBuscarCodigoFornecedor.setBounds(193, 427, 298, 20);
		contentPane.add(textBuscarCodigoFornecedor);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigo.setBounds(68, 426, 108, 23);
		contentPane.add(lblCdigo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNome.setBounds(68, 459, 118, 23);
		contentPane.add(lblNome);
		
		JLabel lblBuscarFuncionrios = new JLabel("Buscar Fornecedores");
		lblBuscarFuncionrios.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarFuncionrios.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarFuncionrios.setBounds(54, 397, 424, 17);
		contentPane.add(lblBuscarFuncionrios);
		
		JButton btnLimparBuscar = new JButton("Limpar");
		btnLimparBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					textBuscarCodigoFornecedor.setText(null);
					
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaFornecedores();
				limparFornecedores();
			}
		});
		btnLimparBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimparBuscar.setBounds(235, 493, 89, 23);
		contentPane.add(btnLimparBuscar);
		
		JLabel label_3 = new JLabel("Telefone:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 12));
		label_3.setBounds(263, 107, 116, 23);
		contentPane.add(label_3);
		
		textTelefoneFornecedor = new JTextField();
		textTelefoneFornecedor.setColumns(10);
		textTelefoneFornecedor.setBounds(392, 108, 97, 20);
		contentPane.add(textTelefoneFornecedor);
		
		JLabel label = new JLabel("Endere\u00E7o:");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(263, 140, 118, 23);
		contentPane.add(label);
		
		textEnderecoFornecedor = new JTextField();
		textEnderecoFornecedor.setColumns(10);
		textEnderecoFornecedor.setBounds(392, 140, 97, 20);
		contentPane.add(textEnderecoFornecedor);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 576, 526);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FornecedoresView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label_1.setBounds(477, 0, 50, 33);
		panel.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MenuView().setVisible(true);
				dispose();
			}
		});
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(65, 105, 225));
		panel_2.setBounds(528, 0, 50, 33);
		panel.add(panel_2);
		
		JLabel label_2 = new JLabel("X");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Arial", Font.PLAIN, 25));
		label_2.setBackground(Color.BLUE);
		label_2.setBounds(17, 2, 17, 29);
		panel_2.add(label_2);
	
		
		//metodo Limpar fornecedores
	}
	public void limparFornecedores() {
		textNomeFornecedor.setText(null);
		textCNPJ.setText(null);
		textCodigoFornecedor.setText(null);
		textTelefoneFornecedor.setText(null);
		textEmailFornecedor.setText(null);
		textEnderecoFornecedor.setText(null);
		try {
			textCodigoFornecedor.setText(String.valueOf(foDao.RetornarProximoCodigoFornecedor()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//metodo atualiazarTabelaFor
	public void atualizarTabelaFornecedores() {
		try {
			Fornecedores = foDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=Fornecedores.size(); x++)
			{
				model.addRow((Object[]) Fornecedores.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	//set campos Fornecedores
		public void setCamposFromTabelaFornecedores() {
			textCodigoFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			textNomeFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
			textCNPJ.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
			textEmailFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
			textTelefoneFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
			textEnderecoFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)));
		}
}
