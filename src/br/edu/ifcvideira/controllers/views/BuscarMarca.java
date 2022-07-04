package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.MarcaDao;
import br.edu.ifcvideira.beans.Marca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class BuscarMarca extends JFrame {

	private JPanel contentPane;
	private JTextField textCdMarca;
	private JTextField textDescricao;
	private JTextField textCodigoProduto;
	private JTextField textDescricaoMarca;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel label;
	private JPanel panel;
	private JLabel label_1;
	private JPanel panel_1;
	public int valor=0;
	/**
	 * Launch the application.
	 */
	private List<Object> marca = new ArrayList<Object>();

	Marca mr = new Marca();
	MarcaDao mrDao = new MarcaDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarMarca frame = new BuscarMarca();
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
	public BuscarMarca() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				atualizarTabelaMarca();

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 530);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMarca.setBounds(21, 62, 68, 14);
		contentPane.add(lblMarca);
		
		textCdMarca = new JTextField();
		textCdMarca.setColumns(10);
		textCdMarca.setBounds(87, 59, 46, 20);
		contentPane.add(textCdMarca);
		
		textDescricao = new JTextField();
		textDescricao.setEditable(false);
		textDescricao.setColumns(10);
		textDescricao.setBounds(143, 59, 177, 20);
		contentPane.add(textDescricao);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(335, 58, 89, 23);
		contentPane.add(btnOK);
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				valor=Integer.parseInt(textCdMarca.getText());
				ProdutosView.textIdMarca.setText(""+valor);
				ProdutosView.textMarca.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				dispose();
			}
		});		
		JLabel lblBuscarMarca = new JLabel("Buscar Marca");
		lblBuscarMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarMarca.setBounds(10, 92, 414, 33);
		contentPane.add(lblBuscarMarca);
		
		textCodigoProduto = new JTextField();
		textCodigoProduto.setColumns(10);
		textCodigoProduto.setBounds(164, 151, 204, 20);
		contentPane.add(textCodigoProduto);
		
		textDescricaoMarca = new JTextField();
		textDescricaoMarca.setColumns(10);
		textDescricaoMarca.setBounds(164, 187, 204, 20);
		contentPane.add(textDescricaoMarca);
		
		JLabel lblCodigoProduto = new JLabel("C\u00F3digo produto");
		lblCodigoProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodigoProduto.setBounds(68, 153, 102, 14);
		contentPane.add(lblCodigoProduto);
		
		JLabel lblDescrição = new JLabel("Descri\u00E7\u00E3o Marca");
		lblDescrição.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescrição.setBounds(68, 189, 153, 14);
		contentPane.add(lblDescrição);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 257, 414, 217);
		contentPane.add(scrollPane);
		
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
				"Código Produto", "Categoria"
			}
		));
		scrollPane.setViewportView(table);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(BuscarMarca.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
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
		panel_1.setBounds(2, 2, 446, 526);
		contentPane.add(panel_1);

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
			textCdMarca.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			textDescricao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		
		}
}
