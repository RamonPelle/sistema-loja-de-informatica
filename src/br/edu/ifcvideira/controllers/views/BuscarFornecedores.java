package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.FornecedoresDao;
import br.edu.ifcvideira.beans.Fornecedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;

public class BuscarFornecedores extends JFrame {

	private JPanel contentPane;
	private JTextField textCdFornece;
	private JTextField textNmeFornecedor;
	private JTextField textCodigoFornec;
	private JTextField textNomeFornecedores;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JPanel panel;
	private JLabel label_1;
	private JPanel panel_1;
	public int valor=0;
	private List<Object> Fornecedores = new ArrayList<Object>();
	/**
	 * Launch the application.
	 */
	Fornecedores fo = new Fornecedores();
	FornecedoresDao foDao = new FornecedoresDao();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarFornecedores frame = new BuscarFornecedores();
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
	public BuscarFornecedores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaFornecedores();

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 535);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFornecedor.setBounds(21, 46, 83, 14);
		contentPane.add(lblFornecedor);
		
		textCdFornece = new JTextField();
		textCdFornece.setColumns(10);
		textCdFornece.setBounds(102, 44, 46, 20);
		contentPane.add(textCdFornece);
		
		textNmeFornecedor = new JTextField();
		textNmeFornecedor.setEditable(false);
		textNmeFornecedor.setColumns(10);
		textNmeFornecedor.setBounds(160, 44, 165, 20);
		contentPane.add(textNmeFornecedor);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				valor=Integer.parseInt(textCdFornece.getText());
				ProdutosView.textIdForn.setText(""+valor);
				ProdutosView.textFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				dispose();
			}
		});
		btnOK.setBounds(335, 43, 89, 23);
		contentPane.add(btnOK);
		
		JLabel lblBuscarFornecedores = new JLabel("Buscar Fornecedor");
		lblBuscarFornecedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarFornecedores.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarFornecedores.setBounds(10, 76, 414, 33);
		contentPane.add(lblBuscarFornecedores);
		
		textCodigoFornec = new JTextField();
		textCodigoFornec.setColumns(10);
		textCodigoFornec.setBounds(218, 136, 153, 20);
		contentPane.add(textCodigoFornec);
		
		JLabel lblCdigoFornecedor = new JLabel("C\u00F3digo Fornecedor");
		lblCdigoFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoFornecedor.setBounds(71, 138, 137, 14);
		contentPane.add(lblCdigoFornecedor);
		
		JLabel lblNomeFornecedor = new JLabel("Nome Fornecedor");
		lblNomeFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNomeFornecedor.setBounds(71, 174, 153, 14);
		contentPane.add(lblNomeFornecedor);
		
		textNomeFornecedores = new JTextField();
		textNomeFornecedores.setColumns(10);
		textNomeFornecedores.setBounds(218, 172, 153, 20);
		contentPane.add(textNomeFornecedores);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 279, 414, 217);
		contentPane.add(scrollPane);
		
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
				"Código Fornecedor", "Nome Fornecedor"
			}
		));
		scrollPane.setViewportView(table);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(BuscarFornecedores.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label.setBounds(349, 0, 50, 33);
		contentPane.add(label);
		
		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
			}
		});
		panel.setLayout(null);
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(400, 0, 50, 33);
		contentPane.add(panel);
		
		label_1 = new JLabel("X");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.PLAIN, 25));
		label_1.setBackground(Color.BLUE);
		label_1.setBounds(17, 2, 17, 29);
		panel.add(label_1);
		
		panel_1 = new JPanel();
		panel_1.setBounds(2, 2, 446, 531);
		contentPane.add(panel_1);

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
			textCdFornece.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			textNmeFornecedor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
			
		}
}
