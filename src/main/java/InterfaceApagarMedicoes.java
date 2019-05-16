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


public class InterfaceApagarMedicoes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceApagarMedicoes frame = new InterfaceApagarMedicoes();
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
	public InterfaceApagarMedicoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCriarAdministrador = new JLabel("Apagar Investigador");
		lblCriarAdministrador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCriarAdministrador.setBounds(139, 13, 215, 25);
		contentPane.add(lblCriarAdministrador);
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(12, 79, 100, 16);
		contentPane.add(lblUsername);

		textField = new JTextField();
		textField.setBounds(161, 79, 146, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String  email = textField.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					PreparedStatement deleted = con.prepareStatement("DELETE FROM investigador WHERE Email = '" + email + "';");
					deleted.executeUpdate();
					textField.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					System.out.println("Apagado com sucesso");
				}
			}
		});
		btnApagar.setBounds(161, 241, 97, 25);
		contentPane.add(btnApagar);
		
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
