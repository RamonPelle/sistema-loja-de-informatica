package br.edu.ifcvideira.controllers.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.ClientesDao;
import br.edu.ifcvideira.DAOs.FuncionariosDao;
import br.edu.ifcvideira.beans.Clientes;

public class ClientesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textCpf;
	private JTextField textCelular;
	private JTextField textTelefone;
	private JTextField textBuscarNomeC;
	private JTextField textBuscarCodigoC;
	private JTable table;

	private List<Object> clientes = new ArrayList<Object>();
	
	Clientes cl = new Clientes();
	ClientesDao clDao = new ClientesDao();
	FuncionariosDao fuDao = new FuncionariosDao();
	
	
	private JTextField textCodigo;
	private JTextField textEndereco;
	private JTextField textNome;
	public static JTextField textNomeFunc;
	private JPanel contentPane;
	public static JTextField textIdFunc;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesView frame = new ClientesView();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	public ClientesView() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTabela();
				limpar();
			}
		});
		setTitle("Cadastro Clientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		textCpf.setBounds(313, 58, 211, 20);
		contentPane.add(textCpf);
		
		JLabel label_2 = new JLabel("CPF:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(242, 58, 59, 20);
		contentPane.add(label_2);
		
		JButton btnAlterarCliente = new JButton("Alterar");
		btnAlterarCliente.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAlterarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					try {
						  
						//atribuição dos valores dos campos para o objeto cliente
						cl.setIdCliente(Integer.parseInt(textCodigo.getText()));
						cl.setNomeCliente(textNome.getText());
						cl.setCpfCliente(textCpf.getText());
						cl.setTelefoneCliente(textTelefone.getText());
						cl.setCelularCliente(textCelular.getText());
						long time = System.currentTimeMillis();
						Timestamp timestamp = new Timestamp(time);
						System.out.println(timestamp);
						cl.setEnderecoCliente(textEndereco.getText());
						cl.setDataCadastroCliente(timestamp);
						System.out.println(timestamp);
					//	cl.setFuncionariosIdFuncionario(Integer.parseInteger(textRenda.getText());
						// chamada do método de alteração na classe Dao
						clDao.AlterarCliente(cl);
						
		
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					atualizarTabela();
					limpar();
				}

			}
		});
		btnAlterarCliente.setBackground(SystemColor.controlHighlight);
		btnAlterarCliente.setBounds(212, 383, 89, 23);

		contentPane.add(btnAlterarCliente);
	
		//cadatrar cliente
		JButton btnCadastrarCliente = new JButton("Cadastrar");
		btnCadastrarCliente.setForeground(new Color(0, 128, 0));
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//atribuição dos valores dos campos para o objeto cliente
					
					cl.setNomeCliente(textNome.getText());
					cl.setCpfCliente(textCpf.getText());
					cl.setTelefoneCliente(textTelefone.getText());
					cl.setCelularCliente(textCelular.getText());
					cl.setEnderecoCliente(textEndereco.getText());
					cl.setFuncionariosIdFuncionario(Integer.parseInt(textIdFunc.getText()));
					
					long time = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(time);
					cl.setDataCadastroCliente(timestamp);
					System.out.println(timestamp);
				//	cl.setFuncionariosIdFuncionario(Integer.parseInteger(textRenda.getText());
					clDao.CadastrarCliente(cl);
				
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				atualizarTabela();
				limpar();
			}
		});
		btnCadastrarCliente.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCadastrarCliente.setBounds(362, 383, 89, 23);
		contentPane.add(btnCadastrarCliente);
		
		JButton btnLimparCliente = new JButton("Limpar");
		btnLimparCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimparCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimparCliente.setBounds(219, 175, 89, 23);
		contentPane.add(btnLimparCliente);
		
		JButton btnExcluirCliente = new JButton("Excluir");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
							+ table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
				
						try {
						
							//atribuição do valor do campo código para o objeto cliente
							cl.setIdCliente(Integer.parseInt(textCodigo.getText()));
							
							// chamada do método de exclusão na classe Dao passando como parâmetro o código do cliente para ser excluído
							clDao.deletarCliente(cl);
							
						
							atualizarTabela();
							limpar();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}}
					}
				
		});
		btnExcluirCliente.setForeground(new Color(204, 0, 0));
		btnExcluirCliente.setBackground(SystemColor.controlHighlight);
		btnExcluirCliente.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExcluirCliente.setBounds(65, 383, 89, 23);
		contentPane.add(btnExcluirCliente);
		
		JLabel lblBuscarCliente = new JLabel("Buscar cliente");
		lblBuscarCliente.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscarCliente.setBounds(226, 435, 107, 20);
		contentPane.add(lblBuscarCliente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 211, 502, 159);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome", "CPF", "Telefone", "Celular", "Endereço", "ID Func."
			}
		));
		
		JLabel label_6 = new JLabel("C\u00F3digo:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 12));
		label_6.setBounds(80, 468, 46, 20);
		contentPane.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textBuscarCodigoC = new JTextField();
		textBuscarCodigoC.setBounds(136, 468, 315, 20);
		contentPane.add(textBuscarCodigoC);
		textBuscarCodigoC.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textBuscarCodigoC.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textBuscarCodigoC.getText(), 0));  
				}  
			}
		});
		textBuscarCodigoC.setColumns(10);
		
		textBuscarNomeC = new JTextField();
		textBuscarNomeC.setBounds(136, 495, 315, 20);
		contentPane.add(textBuscarNomeC);
		textBuscarNomeC.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro); 
				
				if (textBuscarNomeC.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscarNomeC.getText(), 1));  
				}  
				
			}
		});
		textBuscarNomeC.setColumns(10);
		JLabel label_7 = new JLabel("Nome:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 12));
		label_7.setBounds(80, 496, 46, 20);
		contentPane.add(label_7);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEndereo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereo.setBounds(272, 86, 59, 20);
		contentPane.add(lblEndereo);
		
		textEndereco = new JTextField();
		textEndereco.setColumns(10);
		textEndereco.setBounds(346, 86, 178, 20);
		contentPane.add(textEndereco);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(65, 419, 429, 23);
		contentPane.add(separator);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Arial", Font.PLAIN, 14));
		lblClientes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClientes.setBounds(242, 13, 59, 20);
		contentPane.add(lblClientes);
		
		JButton button = new JButton("Limpar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBuscarNomeC.setText(null);
				textBuscarCodigoC.setText(null);
				
			
			}});
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBounds(212, 528, 89, 23);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 554, 558);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(ClientesView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label_3.setBounds(455, 0, 50, 33);
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
		panel_2.setBounds(506, 0, 50, 33);
		panel.add(panel_2);
		
		JLabel label = new JLabel("X");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 25));
		label.setBackground(Color.BLUE);
		label.setBounds(17, 2, 17, 29);
		panel_2.add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(526, 112, 16, 16);
		panel.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			new BuscarFuncionario().setVisible(true);
			BuscarFuncionario.flag=0;
			}
		});
		lblNewLabel.setIcon(new ImageIcon(ClientesView.class.getResource("/br/edu/ifcvideira/imgs/lupa.png")));
		
		textNomeFunc = new JTextField();
		textNomeFunc.setEditable(false);
		textNomeFunc.setBounds(392, 110, 131, 20);
		panel.add(textNomeFunc);
		textNomeFunc.setColumns(10);
		textIdFunc = new JTextField();
		textIdFunc.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
try{
					
					textNomeFunc.setText(fuDao.RetornarNomeFuncionario(Integer.parseInt(textIdFunc.getText())));
				} catch(Exception a) {
				      				      
				}
			}
		});
		textIdFunc.setColumns(10);
		textIdFunc.setBounds(360, 110, 22, 20);
		panel.add(textIdFunc);
		
		JLabel label_4 = new JLabel("Telefone:");
		label_4.setBounds(30, 138, 59, 20);
		panel.add(label_4);
		label_4.setFont(new Font("Arial", Font.PLAIN, 12));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(30, 110, 59, 20);
		panel.add(lblCelular);
		lblCelular.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(30, 82, 59, 20);
		panel.add(lblID);
		lblID.setFont(new Font("Arial", Font.PLAIN, 12));
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(30, 54, 59, 20);
		panel.add(label_1);
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textNome = new JTextField();
		textNome.setBounds(99, 53, 147, 20);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(99, 82, 147, 20);
		panel.add(textCodigo);
		textCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		textCodigo.setText("0");
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		
		textCelular = new JTextField();
		textCelular.setBounds(99, 110, 147, 20);
		panel.add(textCelular);
		textCelular.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(99, 138, 147, 20);
		panel.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblIdFuncionrio = new JLabel("ID funcion\u00E1rio:");
		lblIdFuncionrio.setBounds(274, 110, 89, 20);
		panel.add(lblIdFuncionrio);
		lblIdFuncionrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
	}

	public void setCamposFromTabela() {
		textCodigo.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		textNome.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		textCpf.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		textTelefone.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
		textCelular.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
		textEndereco.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)));
	}

	public void limpar() {
		textCpf.setText(null);
		textNome.setText(null);
		textCelular.setText(null);
		textTelefone.setText(null);
		textEndereco.setText(null);
		textNomeFunc.setText(null);

		try {
			textCodigo.setText(String.valueOf(clDao.RetornarProximoCodigoCliente()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void atualizarTabela() {
		try {
			clientes = clDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=clientes.size(); x++)
			{
				model.addRow((Object[]) clientes.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}




