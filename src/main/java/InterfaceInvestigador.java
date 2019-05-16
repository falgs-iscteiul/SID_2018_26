import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
	public InterfaceInvestigador() throws Exception {
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
		
		JLabel lblAdministratorPanel = new JLabel("Investigador Panel");
		lblAdministratorPanel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdministratorPanel.setBounds(252, 27, 201, 25);
		frame.getContentPane().add(lblAdministratorPanel);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceCriarMedicoes md1 = new InterfaceCriarMedicoes();
				md1.main(null);
			}
		});
		btnCriar.setBounds(58, 135, 97, 25);
		frame.getContentPane().add(btnCriar);

		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					String query = "SELECT * FROM medicoes";
				    Statement st = con.createStatement();
				    ResultSet rs = st.executeQuery(query);
				      
				    while (rs.next())
				      {
				       int numeroMedicao = rs.getInt("NumeroMedicao");
				       Timestamp data = rs.getTimestamp("DataHoraMedicao");
				       double valorMedicao = rs.getDouble("ValorMedicao");
				       System.out.format("%s, %s, %s\n", numeroMedicao, data, valorMedicao);
				      }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVer.setBounds(58, 173, 97, 25);
		frame.getContentPane().add(btnVer);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(58, 211, 97, 25);
		frame.getContentPane().add(btnEditar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceApagarMedicoes apagarAd = new InterfaceApagarMedicoes();
				InterfaceApagarMedicoes.main(null);
			}
		});
		btnApagar.setBounds(58, 249, 97, 25);
		frame.getContentPane().add(btnApagar);
		
		JLabel lblUtilizadores = new JLabel("Medições");
		lblUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUtilizadores.setBounds(50, 88, 150, 22);
		frame.getContentPane().add(lblUtilizadores);
		
		JButton button = new JButton("Criar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceCriarCulturas criarV = new InterfaceCriarCulturas();
				InterfaceCriarCulturas.main(null);
			}
		});
		button.setBounds(536, 135, 97, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Ver");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					String query = "SELECT * FROM cultura";
				    Statement st = con.createStatement();
				    ResultSet rs = st.executeQuery(query);
				      
				    while (rs.next())
				      {
				       int id = rs.getInt("IdCultura");
				       String nomeCultura = rs.getString("NomeCultura");
				       String descricaoCultura = rs.getString("DescricaoCultura");
				       String email = rs.getString("Email_Investigador");
				       System.out.format("%s, %s, %s, %s\n", id, nomeCultura, descricaoCultura, email);
				      }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(536, 173, 97, 25);
		frame.getContentPane().add(button_1);
		
		
		JButton button_3 = new JButton("Editar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		button_3.setBounds(536, 211, 97, 25);
		frame.getContentPane().add(button_3);
		
		JLabel lblVariveis = new JLabel("Culturas");
		lblVariveis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVariveis.setBounds(536, 88, 82, 22);
		frame.getContentPane().add(lblVariveis);
	}
}
