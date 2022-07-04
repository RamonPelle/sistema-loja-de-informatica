package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.CategoriasDao;
import br.edu.ifcvideira.DAOs.MarcaDao;
import br.edu.ifcvideira.beans.Categorias;
import br.edu.ifcvideira.beans.Marca;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Toolkit;
import java.awt.event.MouseMotionAdapter;

public class CategoriasView extends JFrame {

	private JPanel contentPane;
	public static JTextField textDescricaoMarca;
	public static JTextField textDescricaoCategoria;
	private JTextField textMargemLucro;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	private List<Object> categorias = new ArrayList<Object>();
	private List<Object> marca = new ArrayList<Object>();
	
	Categorias ct = new Categorias();
	CategoriasDao ctDao = new CategoriasDao();
	Marca mr = new Marca();
	MarcaDao mrDao = new MarcaDao();
	
	
	
	public static JTextField textCodigoCategoria;
	public static JTextField textCodigoMarca;
	private JTextField textBuscarDescricaoC;
	private JTextField textBuscarCodigoC;
	private JTextField textBuscarDescricaoM;
	private JTextField textBuscarCodigoM;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoriasView frame = new CategoriasView();
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
	public CategoriasView() {
		setTitle("Cadastrar marcas e categorias");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaCategoria();
				atualizarTabelaMarca();
				limparCategoria();
				limparMarca();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 879, 529);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		textDescricaoMarca = new JTextField();
		textDescricaoMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		textDescricaoMarca.setBounds(198, 71, 146, 20);
		contentPane.add(textDescricaoMarca);
		textDescricaoMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMarca.setBounds(178, 33, 46, 14);
		contentPane.add(lblMarca);
		
		JLabel lblDescricaoDaMarca = new JLabel("Descri\u00E7\u00E3o da marca");
		lblDescricaoDaMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescricaoDaMarca.setBounds(58, 74, 128, 14);
		contentPane.add(lblDescricaoDaMarca);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCategorias.setBounds(630, 30, 82, 20);
		contentPane.add(lblCategorias);
		
		JLabel lblDescricaoDaCategoria = new JLabel("Descri\u00E7\u00E3o da categoria");
		lblDescricaoDaCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescricaoDaCategoria.setBounds(491, 70, 128, 14);
		contentPane.add(lblDescricaoDaCategoria);
		
		textDescricaoCategoria = new JTextField();
		textDescricaoCategoria.setColumns(10);
		textDescricaoCategoria.setBounds(657, 70, 146, 20);
		contentPane.add(textDescricaoCategoria);
		
		JLabel lblMargemDeLuci = new JLabel("Margem de lucro");
		lblMargemDeLuci.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMargemDeLuci.setBounds(494, 102, 128, 14);
		contentPane.add(lblMargemDeLuci);
		
		textMargemLucro = new JTextField();
		textMargemLucro.setColumns(10);
		textMargemLucro.setBounds(657, 100, 146, 20);
		contentPane.add(textMargemLucro);
		
		//cadastar marca
		JButton btnCadastrarMarca = new JButton("Cadastrar");
		
		btnCadastrarMarca.setForeground(new Color(0, 128, 0));
		btnCadastrarMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mr.setDescricaoMarca(textDescricaoMarca.getText());
					mrDao.CadastrarMarca(mr);
					long time = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(time);
					System.out.println(timestamp);
					
			
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());	
				}
				atualizarTabelaMarca();
				limparMarca();
				
			}
		});
		btnCadastrarMarca.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCadastrarMarca.setBounds(284, 314, 89, 23);
		contentPane.add(btnCadastrarMarca);
		
		JScrollPane spMarca = new JScrollPane();
		spMarca.setBounds(40, 194, 334, 109);
		contentPane.add(spMarca);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaMarca();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Descrição"
			}
		));
		spMarca.setViewportView(table);
		
		JScrollPane spCategorias = new JScrollPane();
		spCategorias.setBounds(491, 193, 334, 110);
		contentPane.add(spCategorias);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaCategoria();
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Descrição", "Margem de Lucro"
			}
		));
		spCategorias.setViewportView(table_1);
		
		//alterar marca
		JButton btnAlterarMarca = new JButton("Alterar");
		btnAlterarMarca.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAlterarMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					mr.setDescricaoMarca(textDescricaoMarca.getText());
					mr.setIdMarca(Integer.parseInt(textCodigoMarca.getText()));
					long time = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(time);
					System.out.println(timestamp);
					mrDao.AlterarMarca(mr);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());	
				}
				atualizarTabelaMarca();
				limparMarca();
				
			}
		});

		btnAlterarMarca.setBounds(169, 314, 89, 23);
		contentPane.add(btnAlterarMarca);
		
		//excluir marca
		JButton btnExcluirMarca = new JButton("Excluir");
		btnExcluirMarca.setForeground(new Color(204, 0, 0));
		btnExcluirMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
							+ table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
			try { 
				mr.setDescricaoMarca(textDescricaoMarca.getText());
				mr.setIdMarca(Integer.parseInt(textCodigoMarca.getText()));
				mrDao.deletarMarca(mr);
			}	
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}
				
				atualizarTabelaMarca();
				limparMarca();
			}
			}});	
		btnExcluirMarca.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExcluirMarca.setBounds(50, 314, 89, 23);
		contentPane.add(btnExcluirMarca);
		

				//cadastrar Categorias
		JButton btnCadastrarCategorias = new JButton("Cadastrar");
		btnCadastrarCategorias.setForeground(new Color(0, 128, 0));
		btnCadastrarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ct.setDescricaoCategorias(textDescricaoCategoria.getText());
					ct.setMargemLucroCategorias(Double.parseDouble(textMargemLucro.getText()));
					long time = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(time);
					System.out.println(timestamp);
					ctDao.CadastrarCategorias(ct);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaCategoria();
				limparCategoria();
			}
		});
		
		
		btnCadastrarCategorias.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCadastrarCategorias.setBounds(725, 314, 89, 23);
		contentPane.add(btnCadastrarCategorias);
		
		//alterar categorias
		JButton btnAlterarCategorias = new JButton("Alterar");
		btnAlterarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ct.setDescricaoCategorias(textDescricaoCategoria.getText());
					ct.setMargemLucroCategorias(Double.parseDouble(textMargemLucro.getText()));
					ct.setIdCategorias(Integer.parseInt(textCodigoCategoria.getText()));
					long time = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(time);
					System.out.println(timestamp);
					ctDao.AlterarCategorias(ct);
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());			
					}
				atualizarTabelaCategoria();
				limparCategoria();
			}
		});
		btnAlterarCategorias.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAlterarCategorias.setBounds(615, 314, 89, 23);
		contentPane.add(btnAlterarCategorias);
		
		
		//limpar categorias
		JButton btnLimparCategorias = new JButton("Limpar");
		btnLimparCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCategoria();
			}
		});
		btnLimparCategorias.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimparCategorias.setBounds(608, 164, 89, 23);
		contentPane.add(btnLimparCategorias);
		
		
		//excluir categorias
		JButton btnExcluirCategorias = new JButton("Excluir");
		btnExcluirCategorias.setForeground(new Color(204, 0, 0));
		btnExcluirCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table_1.getValueAt(table_1.getSelectedRow(), 0) + "   -   "
							+ table_1.getValueAt(table_1.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
			try { 
				//ct.setDescricaoCategorias(String.valueOf(textDescricaoCategoria));
				ct.setIdCategorias(Integer.parseInt(textCodigoCategoria.getText()));
				//ct.setMargemLucroCategorias(Double.parseDouble(textMargemLucro.getText()));
				ctDao.deletarCategorias(ct);

			}	
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
					}
				
				atualizarTabelaCategoria();
				limparCategoria();
				}
			}});
		
		
		btnExcluirCategorias.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExcluirCategorias.setBounds(509, 314, 89, 23);
		contentPane.add(btnExcluirCategorias);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodigo.setBounds(495, 135, 46, 14);
		contentPane.add(lblCodigo);
		
		textCodigoCategoria = new JTextField();
		textCodigoCategoria.setEditable(false);
		textCodigoCategoria.setColumns(10);
		textCodigoCategoria.setBounds(658, 131, 146, 20);
		contentPane.add(textCodigoCategoria);
		
		JLabel lblCodigo_1 = new JLabel("Codigo");
		lblCodigo_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodigo_1.setBounds(58, 99, 128, 14);
		contentPane.add(lblCodigo_1);
		
		textCodigoMarca = new JTextField();
		textCodigoMarca.setEditable(false);
		textCodigoMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		textCodigoMarca.setColumns(10);
		textCodigoMarca.setBounds(198, 96, 146, 20);
		contentPane.add(textCodigoMarca);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(491, 363, 334, 14);
		contentPane.add(separator_1);
		
		JLabel lblBuscarCategorias = new JLabel("Buscar categorias");
		lblBuscarCategorias.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarCategorias.setBounds(597, 390, 128, 20);
		contentPane.add(lblBuscarCategorias);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescrio.setBounds(530, 423, 70, 14);
		contentPane.add(lblDescrio);
		
		textBuscarDescricaoC = new JTextField();
		textBuscarDescricaoC.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table_1.setRowSorter(filtro); 
				
				if (textBuscarDescricaoC.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscarDescricaoC.getText(), 1));  
				}  
				
			}
		});
		textBuscarDescricaoC.setColumns(10);
		textBuscarDescricaoC.setBounds(608, 423, 146, 20);
		contentPane.add(textBuscarDescricaoC);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigo.setBounds(530, 450, 46, 14);
		contentPane.add(lblCdigo);
		
		textBuscarCodigoC = new JTextField();
		textBuscarCodigoC.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table_1.setRowSorter(filtro);
				
				if (textBuscarCodigoC.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textBuscarCodigoC.getText(), 0));  
				}  
				
			}
		});
		textBuscarCodigoC.setColumns(10);
		textBuscarCodigoC.setBounds(608, 449, 146, 20);
		contentPane.add(textBuscarCodigoC);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(88, 450, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Descri\u00E7\u00E3o:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(88, 423, 70, 14);
		contentPane.add(label_1);
		
		textBuscarDescricaoM = new JTextField();
		textBuscarDescricaoM.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro); 
				
				if (textBuscarDescricaoM.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscarDescricaoM.getText(), 1));  
				}  
				
			}
		});
		textBuscarDescricaoM.setColumns(10);
		textBuscarDescricaoM.setBounds(166, 423, 146, 20);
		contentPane.add(textBuscarDescricaoM);
		
		textBuscarCodigoM = new JTextField();
		textBuscarCodigoM.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (textBuscarCodigoM.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(textBuscarCodigoM.getText(), 0));  
				}  
			}
		});
		textBuscarCodigoM.setColumns(10);
		textBuscarCodigoM.setBounds(166, 449, 146, 20);
		contentPane.add(textBuscarCodigoM);
		
		JLabel lblBuscarMarca = new JLabel("Buscar marca");
		lblBuscarMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarMarca.setBounds(155, 390, 128, 20);
		contentPane.add(lblBuscarMarca);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(39, 363, 334, 14);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(436, 33, 21, 477);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 875, 525);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLimparBuscarM = new JButton("Limpar");
		btnLimparBuscarM.setBounds(154, 489, 89, 23);
		panel.add(btnLimparBuscarM);
		btnLimparBuscarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					textBuscarDescricaoM.setText(null);
					textBuscarCodigoM.setText(null);
					
					try {
						textBuscarCodigoM.setText(null);
						
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
					atualizarTabelaMarca();
					limparMarca();
				}
			
		});
		btnLimparBuscarM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnLimparBuscarC = new JButton("Limpar");
		btnLimparBuscarC.setBounds(610, 489, 89, 23);
		panel.add(btnLimparBuscarC);
		btnLimparBuscarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBuscarDescricaoC.setText(null);
				textBuscarCodigoC.setText(null);
				
				try {
					textBuscarCodigoC.setText(null);
					
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				atualizarTabelaCategoria();
				limparCategoria();

			}
		});
		btnLimparBuscarC.setFont(new Font("Arial", Font.PLAIN, 12));
		//limpar marca
		JButton btnLimparMarca = new JButton("Limpar");
		btnLimparMarca.setBounds(168, 137, 89, 23);
		panel.add(btnLimparMarca);
		btnLimparMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparMarca();
			}
			
		});
		btnLimparMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("");
	
		lblNewLabel.setBounds(777, 0, 50, 33);
		lblNewLabel.setIcon(new ImageIcon(CategoriasView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		panel.add(lblNewLabel);
		
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
		panel_2.setBounds(827, -1, 50, 33);
		panel.add(panel_2);
		
		JLabel label_2 = new JLabel("X");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Arial", Font.PLAIN, 25));
		label_2.setBackground(Color.BLUE);
		label_2.setBounds(17, 2, 17, 29);
		panel_2.add(label_2);
		
		
	}
	//Calsse para limpar marca
	public void limparMarca() {
		textDescricaoMarca.setText(null);
		textMargemLucro.setText(null);

		try {
			textCodigoMarca.setText(String.valueOf(mrDao.RetornarProximoCodigoMarca()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	//classe atualizar table marca
	public void atualizarTabelaMarca() {
		try {
			marca = mrDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=marca.size(); x++)
			{
				model.addRow((Object[]) marca.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	//set campos marca
	
	public void setCamposFromTabelaMarca() {
		textCodigoMarca.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		textDescricaoMarca.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
	
	}
	
	//CLAsse para limpar categoria
	public void limparCategoria() {
		textCodigoCategoria.setText(null);
		textDescricaoCategoria.setText(null);
		textMargemLucro.setText(null);

		try {
			textCodigoCategoria.setText(String.valueOf(ctDao.RetornarProximoCodigoCategoria()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	//Calsse para atualizar tabela CAtegoria
	public void atualizarTabelaCategoria() {
		try {
			categorias = ctDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setNumRows(0);
		for (int x=0; x!=categorias.size(); x++)
			{
				model.addRow((Object[]) categorias.get(x));
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	//set campos categoria
	public void setCamposFromTabelaCategoria() {
		textCodigoCategoria.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0)));
		textDescricaoCategoria.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
		textMargemLucro.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
	
	}
}
