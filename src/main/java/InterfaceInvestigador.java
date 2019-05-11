import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class InterfaceInvestigador {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceInvestigador window = new InterfaceInvestigador();
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
	public InterfaceInvestigador() {
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
		
		JButton btnCriar = new JButton("Criar");
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
		
		JLabel lblCulturas = new JLabel("Culturas");
		lblCulturas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCulturas.setBounds(58, 92, 76, 22);
		frame.getContentPane().add(lblCulturas);
		
		JLabel lblInvestigadorPanel = new JLabel("Investigador Panel");
		lblInvestigadorPanel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInvestigadorPanel.setBounds(252, 31, 192, 25);
		frame.getContentPane().add(lblInvestigadorPanel);
	}
}
