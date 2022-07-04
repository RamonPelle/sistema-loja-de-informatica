package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.ProdutosDao;
import br.edu.ifcvideira.beans.Produtos;

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

public class BuscarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigoProduto;
	private JTextField textDescricaoProduto;
	private JTextField textBuscarCodigoProduto;
	private JTextField textBuscarDescricaoProduto;
	private JTable table;
	public int valor=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarProduto frame = new BuscarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Produtos pr = new Produtos();
	ProdutosDao prDao = new ProdutosDao();
	private List<Object> Produtos = new ArrayList<Object>();
	/**
	 * Create the frame.
	 */
	public BuscarProduto() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaProduto();
				
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 545);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProduto.setBounds(21, 57, 83, 14);
		contentPane.add(lblProduto);
		
		textCodigoProduto = new JTextField();
		textCodigoProduto.setColumns(10);
		textCodigoProduto.setBounds(102, 55, 46, 20);
		contentPane.add(textCodigoProduto);
		
		textDescricaoProduto = new JTextField();
		textDescricaoProduto.setEditable(false);
		textDescricaoProduto.setColumns(10);
		textDescricaoProduto.setBounds(160, 55, 165, 20);
		contentPane.add(textDescricaoProduto);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				valor=Integer.parseInt(textCodigoProduto.getText());
				EstoqueView.TextIdProd.setText(""+valor);
				EstoqueView.textNomeProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				dispose();
			}
		});
		btnOK.setBounds(335, 54, 89, 23);
		contentPane.add(btnOK);
		
		JLabel lblBuscarProduto = new JLabel("Buscar Produto");
		lblBuscarProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarProduto.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarProduto.setBounds(10, 87, 414, 33);
		contentPane.add(lblBuscarProduto);
		
		textBuscarCodigoProduto = new JTextField();
		textBuscarCodigoProduto.setColumns(10);
		textBuscarCodigoProduto.setBounds(167, 147, 153, 20);
		contentPane.add(textBuscarCodigoProduto);
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo Produto");
		lblCdigoProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoProduto.setBounds(20, 149, 137, 14);
		contentPane.add(lblCdigoProduto);
		
		JLabel lblDescrioProduto = new JLabel("Descri\u00E7\u00E3o Produto");
		lblDescrioProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescrioProduto.setBounds(20, 185, 153, 14);
		contentPane.add(lblDescrioProduto);
		
		textBuscarDescricaoProduto = new JTextField();
		textBuscarDescricaoProduto.setColumns(10);
		textBuscarDescricaoProduto.setBounds(167, 183, 153, 20);
		contentPane.add(textBuscarDescricaoProduto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 275, 414, 217);
		contentPane.add(scrollPane);
		
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
				"Código Produto", "Descrição Produto"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BuscarProduto.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label.setBounds(349, 0, 50, 33);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
			}
		});
		panel.setLayout(null);
		panel.setBackground(Color.BLUE);
		panel.setBounds(400, 0, 50, 33);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("X");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.PLAIN, 25));
		label_1.setBackground(new Color(240, 240, 240));
		label_1.setBounds(17, 2, 17, 29);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(2, 2, 446, 541);
		contentPane.add(panel_1);

		
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
				textDescricaoProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				
			}
		}


