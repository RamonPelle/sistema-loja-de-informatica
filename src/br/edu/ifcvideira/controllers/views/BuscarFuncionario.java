package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.FuncionariosDao;
import br.edu.ifcvideira.beans.Funcionarios;

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

public class BuscarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textCdFunc;
	private JTextField textNmefunc;
	private JTextField textCodFuncionario;
	private JTextField textNomeFunc;
	private JTable table;
	private JLabel label;
	private JPanel panel;
	private JLabel label_1;
	private JPanel panel_1;
	private List<Object> funcionarios = new ArrayList<Object>();
	public int valor=0;
	public static int flag=0;
	/**
	 * Launch the application.
	 */
	Funcionarios fu = new Funcionarios();
	FuncionariosDao fuDao = new FuncionariosDao();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarFuncionario frame = new BuscarFuncionario();
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
	public BuscarFuncionario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabelaFuncionarios();

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 539);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFuncionrio.setBounds(21, 52, 68, 14);
		contentPane.add(lblFuncionrio);
		
		textCdFunc = new JTextField();
		textCdFunc.setColumns(10);
		textCdFunc.setBounds(99, 50, 46, 20);
		contentPane.add(textCdFunc);
		
		textNmefunc = new JTextField();
		textNmefunc.setEditable(false);
		textNmefunc.setColumns(10);
		textNmefunc.setBounds(160, 50, 165, 20);
		contentPane.add(textNmefunc);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				valor=Integer.parseInt(textCdFunc.getText());
	
				if(flag==0) {
					
					ClientesView.textIdFunc.setText(""+valor);
					ClientesView.textNomeFunc.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				}else if(flag==1) {
					
					ProdutosView.textIdFunc.setText(""+valor);
					ProdutosView.textFuncionario.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				}
	
		
				dispose();

			}
		});
		btnOK.setBounds(335, 49, 89, 23);
		contentPane.add(btnOK);
		
		JLabel lblBuscarFuncionrio = new JLabel("Buscar Funcion\u00E1rio");
		lblBuscarFuncionrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarFuncionrio.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarFuncionrio.setBounds(10, 82, 414, 33);
		contentPane.add(lblBuscarFuncionrio);
		
		textCodFuncionario = new JTextField();
		textCodFuncionario.setColumns(10);
		textCodFuncionario.setBounds(217, 143, 153, 20);
		contentPane.add(textCodFuncionario);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoFuncionrio.setBounds(70, 145, 137, 14);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblNomeFuncionario = new JLabel("Nome Funcion\u00E1rio");
		lblNomeFuncionario.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNomeFuncionario.setBounds(70, 181, 153, 14);
		contentPane.add(lblNomeFuncionario);
		
		textNomeFunc = new JTextField();
		textNomeFunc.setColumns(10);
		textNomeFunc.setBounds(217, 179, 153, 20);
		contentPane.add(textNomeFunc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 275, 414, 217);
		contentPane.add(scrollPane);
		
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
				"Código Funcionário", "Nome Funcionário"
			}
		));
		scrollPane.setViewportView(table);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(BuscarFuncionario.class.getResource("/br/edu/ifcvideira/imgs/logo.png")));
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
		panel_1.setBounds(2, 2, 446, 535);
		contentPane.add(panel_1);

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
			textCdFunc.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			textNmefunc.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));

		}
	}