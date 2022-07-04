package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.FuncionariosDao;
import br.edu.ifcvideira.beans.Funcionarios;

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

public class FuncionariosView extends JFrame {

	private JPanel contentPane;
	public JTextField textCodigoFuncionario;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textRG;
	private JTextField textTelefone;
	private JTextField textCelular;
	private JTextField textDataDeAdmissao;

	private List<Object> funcionarios = new ArrayList<Object>();
	/**
	 * Launch the application.
	 */
	Funcionarios fu = new Funcionarios();
	FuncionariosDao fuDao = new FuncionariosDao();
	private JTable table;
	private JTextField textBuscarNomeFuncionario;
	private JTextField textBuscarCodigoFuncionario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionariosView frame = new FuncionariosView();
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
	public FuncionariosView() {
		setBackground(Color.BLUE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaFuncionarios();
				limparFuncionarios();
			}
		});
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		//excluir funcionario
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
					fu.setNomeFuncionario(String.valueOf(textNome.getText()));
					fu.setCpfFuncionario(String.valueOf(textCPF.getText()));
					fu.setIdFuncionario(Integer.parseInt(textCodigoFuncionario.getText()));
					fu.setRgFuncionario(String.valueOf(textRG.getText()));
					fu.setCelularFuncionario(String.valueOf(textCelular.getText()));
					fu.setTelefoneFuncionario(String.valueOf(textTelefone.getText()));
					fu.setDataAdmissao(String.valueOf(textDataDeAdmissao.getText()));
					
					fuDao.deletarFuncionario(fu);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}
				atualizarTabelaFuncionarios();
				limparFuncionarios();	
				
			}
			}});
		btnExcluir.setForeground(new Color(204, 0, 0));
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluir.setBounds(87, 371, 89, 23);
		contentPane.add(btnExcluir);
		
		
		//alterar funcionarios
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						fu.setNomeFuncionario(String.valueOf(textNome.getText()));
						fu.setCpfFuncionario(String.valueOf(textCPF.getText()));
						fu.setIdFuncionario(Integer.parseInt(textCodigoFuncionario.getText()));
						fu.setRgFuncionario(String.valueOf(textRG.getText()));
						fu.setCelularFuncionario(String.valueOf(textCelular.getText()));
						fu.setTelefoneFuncionario(String.valueOf(textTelefone.getText()));
						fu.setDataAdmissao(String.valueOf(textDataDeAdmissao.getText()));
						
						
						fuDao.AlterarFuncionario(fu);
					} 
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());			
						}
					atualizarTabelaFuncionarios();
					limparFuncionarios();
				}
			});
			
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAlterar.setBounds(243, 371, 89, 23);
		contentPane.add(btnAlterar);
		//cadastrar Funcionarios
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fu.setNomeFuncionario(String.valueOf(textNome.getText()));
					fu.setCpfFuncionario(String.valueOf(textCPF.getText()));
					fu.setRgFuncionario(String.valueOf(textRG.getText()));
					fu.setCelularFuncionario(String.valueOf(textCelular.getText()));
					fu.setTelefoneFuncionario(String.valueOf(textTelefone.getText()));
					fu.setDataAdmissao(String.valueOf(textDataDeAdmissao.getText()));
					
					fuDao.CadastrarFuncionario(fu);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaFuncionarios();
				limparFuncionarios();
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 128, 0));
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastrar.setBounds(392, 371, 89, 23);
		contentPane.add(btnCadastrar);
		
		JLabel lblFuncionarios = new JLabel("Funcion\u00E1rios");
		lblFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionarios.setFont(new Font("Arial", Font.PLAIN, 14));
		lblFuncionarios.setBounds(235, 26, 97, 17);
		contentPane.add(lblFuncionarios);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo:");
		lblCdigoFuncionrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoFuncionrio.setBounds(38, 75, 54, 23);
		contentPane.add(lblCdigoFuncionrio);
		
		textCodigoFuncionario = new JTextField();
		textCodigoFuncionario.setEditable(false);
		textCodigoFuncionario.setColumns(10);
		textCodigoFuncionario.setBounds(124, 75, 97, 20);
		contentPane.add(textCodigoFuncionario);
		
		JLabel lblNomeFuncionrio = new JLabel("Nome:");
		lblNomeFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNomeFuncionrio.setBounds(38, 109, 54, 23);
		contentPane.add(lblNomeFuncionrio);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(124, 108, 97, 20);
		contentPane.add(textNome);
		
		JLabel lblCpfFuncionrio = new JLabel("CPF:");
		lblCpfFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCpfFuncionrio.setBounds(38, 140, 54, 23);
		contentPane.add(lblCpfFuncionrio);
		
		textCPF = new JTextField();
		textCPF.setColumns(10);
		textCPF.setBounds(124, 142, 97, 20);
		contentPane.add(textCPF);
		
		JLabel lblRgFuncionrio = new JLabel("RG:");
		lblRgFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRgFuncionrio.setBounds(38, 174, 54, 23);
		contentPane.add(lblRgFuncionrio);
		
		textRG = new JTextField();
		textRG.setColumns(10);
		textRG.setBounds(124, 176, 97, 20);
		contentPane.add(textRG);
		
		JLabel lblTelefoneFuncionrio = new JLabel("Telefone:");
		lblTelefoneFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelefoneFuncionrio.setBounds(263, 75, 116, 23);
		contentPane.add(lblTelefoneFuncionrio);
		
		textTelefone = new JTextField();
		textTelefone.setColumns(10);
		textTelefone.setBounds(392, 75, 97, 20);
		contentPane.add(textTelefone);
		
		JLabel lblCelularFuncionrio = new JLabel("Celular:");
		lblCelularFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCelularFuncionrio.setBounds(263, 109, 118, 23);
		contentPane.add(lblCelularFuncionrio);
		
		textCelular = new JTextField();
		textCelular.setColumns(10);
		textCelular.setBounds(392, 109, 97, 20);
		contentPane.add(textCelular);
		
		JLabel lblDataDeAdimio = new JLabel("Data de Admiss\u00E3o:");
		lblDataDeAdimio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataDeAdimio.setBounds(263, 137, 108, 31);
		contentPane.add(lblDataDeAdimio);
		
		textDataDeAdmissao = new JTextField();
		textDataDeAdmissao.setColumns(10);
		textDataDeAdmissao.setBounds(392, 141, 97, 20);
		contentPane.add(textDataDeAdmissao);
		
		//limpar funcionario
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFuncionarios();
				
				
			}
		});
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.setBounds(235, 209, 89, 23);
		contentPane.add(btnLimpar);
		
		JScrollPane spFuncionarios = new JScrollPane();
		spFuncionarios.setBounds(38, 245, 484, 113);
		contentPane.add(spFuncionarios);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaFuncionarios();
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Código", "Nome", "CPF", "RG", "Telefone", "Celular", "Data Admissão"
				}
			));
		spFuncionarios.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		separator.setBounds(12, 407, 523, 11);
		contentPane.add(separator);
		
		textBuscarNomeFuncionario = new JTextField();
		textBuscarNomeFuncionario.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro); 
				
				if (textBuscarNomeFuncionario.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscarNomeFuncionario.getText(), 1));  
				}  
				
			}
		});
		textBuscarNomeFuncionario.setColumns(10);
		textBuscarNomeFuncionario.setBounds(192, 483, 301, 20);
		contentPane.add(textBuscarNomeFuncionario);
		
		textBuscarCodigoFuncionario = new JTextField();
		textBuscarCodigoFuncionario.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textBuscarCodigoFuncionario.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textBuscarCodigoFuncionario.getText(), 0));  
				}  
			}
		});
		textBuscarCodigoFuncionario.setColumns(10);
		textBuscarCodigoFuncionario.setBounds(193, 450, 298, 20);
		contentPane.add(textBuscarCodigoFuncionario);
		
		JLabel label = new JLabel("C\u00F3digo Funcion\u00E1rio");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(38, 449, 108, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nome Funcion\u00E1rio");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(38, 482, 118, 23);
		contentPane.add(label_1);
		
		JLabel lblBuscarFuncionrios = new JLabel("Buscar Funcion\u00E1rios");
		lblBuscarFuncionrios.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarFuncionrios.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarFuncionrios.setBounds(54, 420, 424, 17);
		contentPane.add(lblBuscarFuncionrios);
		
		JButton btnLimparBuscar = new JButton("Limpar");
		btnLimparBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					textBuscarCodigoFuncionario.setText(null);
					
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaFuncionarios();
				limparFuncionarios();
			}
		});
		btnLimparBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimparBuscar.setBounds(235, 516, 89, 23);
		contentPane.add(btnLimparBuscar);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 576, 551);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(FuncionariosView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label_3.setBounds(478, -2, 50, 33);
		panel.add(label_3);
		
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
		panel_2.setBounds(526, 0, 50, 33);
		panel.add(panel_2);
		
		JLabel label_4 = new JLabel("X");
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial", Font.PLAIN, 25));
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(17, 2, 17, 29);
		panel_2.add(label_4);
	
		
		//metodo LimparFun	
	}
	public void limparFuncionarios() {
		textNome.setText(null);
		textCPF.setText(null);
		textCodigoFuncionario.setText(null);
		textRG.setText(null);
		textCelular.setText(null);;
		textTelefone.setText(null);
		textDataDeAdmissao.setText(null);
		try {
			textCodigoFuncionario.setText(String.valueOf(fuDao.RetornarProximoCodigoFuncionario()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//metodo atualiazarTabelaFun
	public void atualizarTabelaFuncionarios() {
		try {
			funcionarios = fuDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=funcionarios.size(); x++)
			{
				model.addRow((Object[]) funcionarios.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	//set campos funcionarios
		public void setCamposFromTabelaFuncionarios() {
			textCodigoFuncionario.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			textNome.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
			textCPF.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
			textRG.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
			textCelular.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
			textTelefone.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)));
			textDataDeAdmissao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 6)));
		}
}
