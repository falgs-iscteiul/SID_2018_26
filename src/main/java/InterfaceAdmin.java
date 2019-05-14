import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;

public class InterfaceAdmin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAdmin window = new InterfaceAdmin();
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
	public InterfaceAdmin() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 732, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdministratorPanel = new JLabel("Administrator Panel");
		lblAdministratorPanel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdministratorPanel.setBounds(252, 27, 201, 25);
		frame.getContentPane().add(lblAdministratorPanel);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceCriarAdmin adm1 = new InterfaceCriarAdmin();
				adm1.main(null);
			}
		});
		btnCriar.setBounds(58, 135, 97, 25);
		frame.getContentPane().add(btnCriar);

		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(58, 173, 97, 25);
		frame.getContentPane().add(btnVer);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(58, 211, 97, 25);
		frame.getContentPane().add(btnEditar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(58, 249, 97, 25);
		frame.getContentPane().add(btnApagar);
		
		JLabel lblUtilizadores = new JLabel("Utilizadores");
		lblUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUtilizadores.setBounds(50, 88, 105, 22);
		frame.getContentPane().add(lblUtilizadores);
		
		JButton button = new JButton("Criar");
		button.setBounds(536, 135, 97, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Ver");
		button_1.setBounds(536, 173, 97, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Editar");
		button_2.setBounds(536, 211, 97, 25);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Apagar");
		button_3.setBounds(536, 249, 97, 25);
		frame.getContentPane().add(button_3);
		
		JLabel lblVariveis = new JLabel("Vari\u00E1veis");
		lblVariveis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVariveis.setBounds(536, 88, 82, 22);
		frame.getContentPane().add(lblVariveis);
	}
}
