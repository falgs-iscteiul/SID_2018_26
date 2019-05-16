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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class InterfaceCriarCulturas extends JFrame {

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
					InterfaceCriarCulturas frame = new InterfaceCriarCulturas();
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
	public InterfaceCriarCulturas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCriarAdministrador = new JLabel("Criar Culturas");
		lblCriarAdministrador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCriarAdministrador.setBounds(139, 13, 207, 22);
		contentPane.add(lblCriarAdministrador);
		
		JLabel lblUsername = new JLabel("Nome Cultura");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(12, 79, 150, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Descrição Cultura");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(12, 132, 150, 26);
		contentPane.add(lblPassword);
		
		JLabel lblCategoria = new JLabel("Email");
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
				String  nome = textField.getText();
				String  descricao = textField_1.getText();
				String  email = textField_2.getText();
				int id = 0;
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					String query = "SELECT * FROM cultura";
				      // create the java statement
				      Statement st = con.createStatement();
				      
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query);
				      
				      // iterate through the java resultset
				      while (rs.next())
				      {
				       id = rs.getInt("IdCultura");
				      }
					id++;
					PreparedStatement inserted = con.prepareStatement("INSERT INTO cultura (IdCultura, NomeCultura, DescricaoCultura, Email_Investigador) VALUES ('"+id+"', '"+nome+"','"+descricao+"','"+email+"')");
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
