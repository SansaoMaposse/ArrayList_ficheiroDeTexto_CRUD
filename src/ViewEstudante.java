import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ViewEstudante extends JFrame implements ActionListener, MouseListener {

	private JFrame frame;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtNota1;
	private JTextField txtNota2;
	private JTable listagem;

	JButton btnAdicionar, btnListar, btnActualizar, btnRemover;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEstudante window = new ViewEstudante();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewEstudante() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 887, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(432, 11, 246, 198);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(53, 11, 119, 33);
		btnAdicionar.addActionListener(this);
		panel_1.add(btnAdicionar);

		btnListar = new JButton("Listar");
		btnListar.setBounds(53, 55, 119, 38);
		btnListar.addActionListener(this);
		panel_1.add(btnListar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(53, 104, 119, 34);
		btnActualizar.addActionListener(this);
		panel_1.add(btnActualizar);

		btnRemover = new JButton("Remover");
		btnRemover.setBounds(53, 149, 119, 34);
		btnRemover.addActionListener(this);
		panel_1.add(btnRemover);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 391, 198);
		panel.setBorder(
				new TitledBorder(null, "Dados Do Estudante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(212, 26, 150, 26);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(212, 63, 150, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtNota1 = new JTextField();
		txtNota1.setBounds(212, 100, 153, 26);
		panel.add(txtNota1);
		txtNota1.setColumns(10);

		txtNota2 = new JTextField();
		txtNota2.setBounds(212, 137, 150, 26);
		panel.add(txtNota2);
		txtNota2.setColumns(10);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(38, 38, 48, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(38, 69, 48, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nota 1");
		lblNewLabel_2.setBounds(38, 106, 48, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nota 2");
		lblNewLabel_3.setBounds(38, 143, 48, 14);
		panel.add(lblNewLabel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "listagem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 220, 668, 159);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 648, 137);
		panel_2.add(scrollPane);

		listagem = new JTable();
		listagem.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome", "Nota 1", "Nota 2", "M\u00E9dia", "Situa\u00E7\u00E3o" }));
		scrollPane.setViewportView(listagem);
		listagem.addMouseListener(this);
	}

	public void limparCaixas() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtNota1.setText("");
		txtNota2.setText("");
	}

	public void listarEstudante() {
		DefaultTableModel listarNaTabela = (DefaultTableModel) listagem.getModel();
		try {
			ArrayList<Estudante> listaDeEstudantes = ControllerEstudante.listaDeEstudante();
			for (Estudante estudante : listaDeEstudantes) {
				listarNaTabela.addRow(new Object[] { estudante.getCodigo(), estudante.getNome(), estudante.getNota1(),
						estudante.getNota2(), estudante.calcularMedia(),
						estudante.situacao(estudante.calcularMedia()) });
				/*
				 * Formas de Criar um Array int idades[] = {1, 2, 3}; int idade[] = new int[4];
				 * idade[0] = 2; String [] idadess = new String[]{"a", "b"};
				 */
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public void limparTabela() {
		while (listagem.getRowCount() > 0) {
			DefaultTableModel tabela = (DefaultTableModel) listagem.getModel();
			tabela.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAdicionar) {
			int codigo = Integer.parseInt(txtCodigo.getText());
			String nome = txtNome.getText();
			double nota1 = Double.parseDouble(txtNota1.getText());
			double nota2 = Double.parseDouble(txtNota2.getText());

			try {
				ControllerEstudante.adicionar(codigo, nome, nota1, nota2);
				JOptionPane.showConfirmDialog(null, "Adicionado com Sucesso.");
				limparCaixas();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
		}
		if (e.getSource() == btnListar) {
			limparTabela();
			listarEstudante();
		}
		if (e.getSource() == btnActualizar) {
			int codigo = Integer.parseInt(txtCodigo.getText());
			String nome = txtNome.getText();
			double nota1 = Double.parseDouble(txtNota1.getText());
			double nota2 = Double.parseDouble(txtNota2.getText());

			try {
				ControllerEstudante.actualizar(codigo, nome, nota1, nota2);
				JOptionPane.showConfirmDialog(null, "Actualizado com Sucesso.");
				limparCaixas();
				limparTabela();
				listarEstudante();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}

		}
		if(e.getSource()== btnRemover) {
			int codigo = Integer.parseInt(txtCodigo.getText());
		
			try {
				ControllerEstudante.remover(codigo);
				JOptionPane.showConfirmDialog(null, "Remomvido com Sucesso.");
				limparCaixas();
				limparTabela();
				listarEstudante();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (listagem.getSelectedRow() != -1) {
			int indice = listagem.getSelectedRow();
			DefaultTableModel tabela = (DefaultTableModel) listagem.getModel();
			txtCodigo.setText((tabela.getValueAt(indice, 0).toString()));
			txtNome.setText((tabela.getValueAt(indice, 1).toString()));
			txtNota1.setText((tabela.getValueAt(indice, 2).toString()));
			txtNota2.setText((tabela.getValueAt(indice, 3).toString()));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
