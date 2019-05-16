import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class InterfaceCriarMedicoes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCriarMedicoes frame = new InterfaceCriarMedicoes();
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
	public InterfaceCriarMedicoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCriarAdministrador = new JLabel("Criar Medicoes");
		lblCriarAdministrador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCriarAdministrador.setBounds(139, 13, 207, 22);
		contentPane.add(lblCriarAdministrador);
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(12, 79, 100, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Nome");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(12, 132, 100, 16);
		contentPane.add(lblPassword);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategoria.setBounds(12, 185, 100, 16);
		contentPane.add(lblCategoria);
		
		textField = new JTextField();
		textField.setBounds(161, 79, 146, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 132, 146, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 185, 146, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String  email = textField.getText();
				String  nome = textField_1.getText();
				String  categoria = textField_2.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					PreparedStatement inserted = con.prepareStatement("INSERT INTO investigador (Email, NomeInvestigador, CategoriaProfessional) VALUES ('"+email+"','"+nome+"','"+categoria+"')");
					inserted.executeUpdate();
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					System.out.println("Inserido com sucesso");
				}
			}
		});
		btnCriar.setBounds(161, 241, 97, 25);
		contentPane.add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(290, 241, 97, 25);
		contentPane.add(btnCancelar);
	}
}
