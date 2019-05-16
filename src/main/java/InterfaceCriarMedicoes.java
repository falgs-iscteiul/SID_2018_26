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


public class InterfaceCriarMedicoes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 492, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCriarVariaveis = new JLabel("Criar Variaveis");
		lblCriarVariaveis.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCriarVariaveis.setBounds(123, 13, 207, 16);
		contentPane.add(lblCriarVariaveis);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(12, 79, 100, 16);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(161, 77, 146, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String  nome = textField.getText();
				int id = 0;
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					String query = "SELECT * FROM variavel";

				      // create the java statement
				      Statement st = con.createStatement();
				      
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query);
				      
				      // iterate through the java resultset
				      while (rs.next())
				      {
				       id = rs.getInt("IdVariavel");
				      }
					id++;
					PreparedStatement inserted = con.prepareStatement("INSERT INTO variavel (IdVariavel, NomeVariavel) VALUES ('"+id+"','"+nome+"')");
					inserted.executeUpdate();
					textField.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					System.out.println("Inserido com sucesso");
				}
			}
		});
		btnCriar.setBounds(161, 185, 97, 25);
		contentPane.add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(290, 185, 97, 25);
		contentPane.add(btnCancelar);
	}
}
