import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoToSQL {

	public static String aux(Document d) {
		String ano;
		String mes = "00";
		String dia;
		String hora;
		java.util.Date dsc = d.getDate("DataHoraMedicao");
		String s = (String) dsc.toString();
		String [] aux = s.split(" ");
		ano = aux[5];
		dia = aux[2];
		hora = aux[3];
		if(aux[1].equals("Jan")) {
			mes = "01";
		} else if(aux[1].equals("Fev")) {
			mes = "02";
		} else if(aux[1].equals("Mar")) {
			mes = "03";
		} else if(aux[1].equals("Apr")) {
			mes = "04";
		} else if(aux[1].equals("May")) {
			mes = "05";
		} else if(aux[1].equals("Jun")) {
			mes = "06";
		} else if(aux[1].equals("Jul")) {
			mes = "07";
		} else if(aux[1].equals("Aug")) {
			mes = "08";
		} else if(aux[1].equals("Sep")) {
			mes = "09";
		} else if(aux[1].equals("Oct")) {
			mes = "10";
		} else if(aux[1].equals("Nov")) {
			mes = "11";
		} else if(aux[1].equals("Dez")) {
			mes = "12";
		}
		String date = new String(ano + "-" + mes + "-" + dia + " " + hora);
		return date;
	}
	
	MongoClient mongoClient1 = new MongoClient("localhost",27017); 

	MongoDatabase database = mongoClient1.getDatabase("sensores");
	MongoCollection<Document> collectionTemp = database.getCollection("SensorTemperatura");
	
	try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","alexSID","projetoSID");
	
	FindIterable<Document> cursorTemp = (FindIterable<Document>) collectionTemp.find();
	for (Document d : cursorTemp) {
//		System.out.println(d);
		String m = d.toJson().replace("{", "").replace("}", "").replace("\"", "");
//		System.out.println(m);
		String[] separar = m.split(",");
		String id_aux = separar[0];
		String[] separar_id_aux = id_aux.split(":");
		String id = separar_id_aux[2];
//		System.out.println(id);
		String vm_aux = separar[1];
		String[] separar_vm_aux = vm_aux.split(":");
		String vm = separar_vm_aux[1];
//		System.out.println(vm);
		String dhm = aux(d);
//		System.out.println(dhm);
		Statement stmt=con.createStatement();  
		int rs=stmt.executeUpdate("INSERT INTO medicaotemperatura VALUES(" + "\"" + dhm + "\"" + "," + "\"" + vm + "\"" + "," + "\"" + id + "\"" + ")");
	}	
		
	}catch(Exception e){ 
		System.out.println(e);
	}
	
	
	mongoClient1.close();
}
}
