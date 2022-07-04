package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MenuView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	
	 
	
	
	public MenuView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1000, 640);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		ClientesView clienteV = new ClientesView();
		EstoqueView estoqueV = new EstoqueView();
		FornecedoresView fornecedorV = new FornecedoresView();
		CategoriasView categoriaV = new CategoriasView();
		FuncionariosView funcionarioV = new FuncionariosView();
		ProdutosView produtoV = new ProdutosView(); 
		
		//cliente
		JLabel lCliente = new JLabel("");
		lCliente.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent arg0) {
					clienteV.setVisible(true);
					dispose();
					
					
				}
		});
		lCliente.setVerticalAlignment(SwingConstants.TOP);
		lCliente.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/clienteFinal.png")));
		lCliente.setBounds(50, 50, 200, 200);
		contentPane.add(lCliente);
		
		//estoque
		JLabel lEstoque = new JLabel("");
		lEstoque.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				estoqueV.setVisible(true);
				dispose();
				
			}
		});
		lEstoque.setVerticalAlignment(SwingConstants.TOP);
		lEstoque.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/aestoque.png")));
		lEstoque.setBounds(375, 50, 200, 200);
		contentPane.add(lEstoque);
		
		//fornecedor
		JLabel lFornecedor = new JLabel("");
		lFornecedor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fornecedorV.setVisible(true);
				dispose();
				
			}
		});
		lFornecedor.setVerticalAlignment(SwingConstants.TOP);
		lFornecedor.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/afornecedor.png")));
		lFornecedor.setBounds(700, 50, 200, 200);
		contentPane.add(lFornecedor);
		
		//funcionario
		JLabel lFuncionario = new JLabel("");
		lFuncionario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				funcionarioV.setVisible(true);
				dispose();
			}
		});
		lFuncionario.setVerticalAlignment(SwingConstants.TOP);
		lFuncionario.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/afuncionario.png")));
		lFuncionario.setBounds(50, 314, 200, 200);
		contentPane.add(lFuncionario);
		
		
		//produto
		JLabel lProduto = new JLabel("");
		lProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				produtoV.setVisible(true);
				dispose();
				
			}
		});
		lProduto.setVerticalAlignment(SwingConstants.TOP);
		lProduto.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/aproduto.png")));
		lProduto.setBounds(375, 314, 200, 200);
		contentPane.add(lProduto);
		
		//
		JLabel lCategoriaMarca = new JLabel("");
		lCategoriaMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lCategoriaMarca.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/marcaFinal.png")));
		lCategoriaMarca.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				categoriaV.setVisible(true);
				dispose();
			}
		});
		lCategoriaMarca.setBounds(663, 281, 283, 251);
		contentPane.add(lCategoriaMarca);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 996, 635);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setBounds(946, 0, 50, 33);
		panel.add(panel_1);
		
		JLabel label = new JLabel("X");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 25));
		label.setBackground(Color.BLUE);
		label.setBounds(17, 2, 17, 29);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MenuView.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
		label_1.setBounds(898, 0, 50, 33);
		panel.add(label_1);
	}
}
