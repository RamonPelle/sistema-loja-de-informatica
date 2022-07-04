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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.CategoriasDao;
import br.edu.ifcvideira.beans.Categorias;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class BuscarCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigoProduto;
	private JTextField textDescricaoCategoria;
	private JTextField textIdCat;
	private JTextField textDescCat;
	private JTable table;
	private JLabel label;
	private JPanel panel;
	private JLabel label_1;	
	public int valor=0;
	/**
	 * Launch the application.
	 */
	private List<Object> categorias = new ArrayList<Object>();
	Categorias ct = new Categorias();
	CategoriasDao ctDao = new CategoriasDao();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarCategoria frame = new BuscarCategoria();
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
	public BuscarCategoria() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaCategoria();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel lblBuscarCategoria = new JLabel("Buscar Categoria");
		lblBuscarCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarCategoria.setBounds(10, 97, 414, 33);
		lblBuscarCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(lblBuscarCategoria);
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo produto");
		lblCdigoProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoProduto.setBounds(65, 157, 102, 14);
		contentPane.add(lblCdigoProduto);
		
		textCodigoProduto = new JTextField();
		textCodigoProduto.setBounds(161, 155, 204, 20);
		contentPane.add(textCodigoProduto);
		textCodigoProduto.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Descri\u00E7\u00E3o");
		lblCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCategoria.setBounds(65, 193, 153, 14);
		contentPane.add(lblCategoria);
		
		textDescricaoCategoria = new JTextField();
		textDescricaoCategoria.setBounds(161, 191, 204, 20);
		contentPane.add(textDescricaoCategoria);
		textDescricaoCategoria.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				valor=Integer.parseInt(textIdCat.getText());
				ProdutosView.textIdCat.setText(""+valor);
				ProdutosView.textCategoria.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				dispose();
			}
		});
		btnOK.setBounds(335, 63, 89, 23);
		contentPane.add(btnOK);
		
		JLabel lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 67, 68, 14);
		contentPane.add(lblNewLabel);
		
		textIdCat = new JTextField();
		textIdCat.setBounds(87, 64, 46, 20);
		contentPane.add(textIdCat);
		textIdCat.setColumns(10);
		
		textDescCat = new JTextField();
		textDescCat.setEditable(false);
		textDescCat.setBounds(143, 64, 177, 20);
		contentPane.add(textDescCat);
		textDescCat.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 265, 414, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaCategoria();
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
		label.setIcon(new ImageIcon(BuscarCategoria.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(2, 2, 446, 506);
		contentPane.add(panel_1);

	}
	//Calsse para atualizar tabela CAtegoria
	public void atualizarTabelaCategoria() {
		try {
			categorias = ctDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
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
		textIdCat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		textDescCat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		
	
	}
}
