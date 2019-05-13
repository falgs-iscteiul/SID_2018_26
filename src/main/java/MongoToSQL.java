import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
	
	
	public static void main(String[] args) {
		MongoClient mongoClient1 = new MongoClient("localhost",27017); 

		MongoDatabase database = mongoClient1.getDatabase("sensores");
		MongoCollection<Document> collectionTemp = database.getCollection("SensorTemperatura");
		MongoCollection<Document> collectionLumi = database.getCollection("SensorLuminosidade");
		
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","alexSID","projetoSID");
			FindIterable<Document> cursorLumi = (FindIterable<Document>) collectionLumi.find();
		for (Document d : cursorLumi) {
			String m = d.toJson().replace("{", "").replace("}", "").replace("\"", "");
			String[] separar = m.split(",");
			String id_aux = separar[0];
			String[] separar_id_aux = id_aux.split(":");
			String id = separar_id_aux[2];
			String vm_aux = separar[1];
			String[] separar_vm_aux = vm_aux.split(":");
			String vm = separar_vm_aux[1];
			String dhm = aux(d);
			Statement stmt=con.createStatement();  
			int rs=stmt.executeUpdate("INSERT INTO medicaoluminosidade VALUES(" + "\"" + dhm + "\"" + "," + "\"" + vm + "\"" + "," + "\"" + id + "\"" + ")");
			idsLumi.add(id);
		}

		Statement stmt_to_check2 = con.createStatement();
		ResultSet check2 = stmt_to_check2.executeQuery("SELECT IdMedicao from medicaoluminosidade");
		while(check2.next()) {
			String id_check2 = check2.getString("IdMedicao").replace(" ", "");
			for(Document ds : cursorLumi) {
				String ss = ds.getObjectId("_id").toString().replace(" ", ""
						+ "");
				System.out.println(ss);
				System.out.println(id_check2);
				if(ss.equals(id_check2)) {
					System.out.println("entrei2");
					
					collectionLumi.deleteOne(ds);
				}
			}
		}
	}
}