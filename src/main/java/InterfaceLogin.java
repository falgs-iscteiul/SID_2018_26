import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class InterfaceLogin {

	private JFrame frame;
	private JTextField username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceLogin window = new InterfaceLogin();
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
	public InterfaceLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(110, 134, 200, 27);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(110, 192, 200, 27);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(376, 136, 229, 25);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(375, 194, 230, 27);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name= username.getText();
				String pass= passwordField.getText();
				
				if(name.equals("admin") && pass.equals("admin")) {
					JOptionPane.showMessageDialog(frame, "Login admin com sucesso");
					username.setText(null);
					passwordField.setText(null);
					InterfaceAdmin.main(null);
				}
				else if(name.equals("investigador") && pass.equals("investigador")) {
					JOptionPane.showMessageDialog(frame, "Login investigador com sucesso");
					username.setText(null);
					passwordField.setText(null);
					InterfaceInvestigador.main(null);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Password ou username inválido ");
				}
			}
		});
		btnLogin.setBounds(376, 246, 137, 27);
		frame.getContentPane().add(btnLogin);
		
	}
}
