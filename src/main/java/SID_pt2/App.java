package SID_pt2;

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
import java.util.Calendar;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.CommandResult;
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
import java.util.TimerTask;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
	
	int i = 1;
	
	public static String aux(Document d) {
		String ano;
		String mes = "00";
		String dia;
		String hora;
//		java.util.Date dsc = d.getDate("DataHoraMedicao");
//		String s = (String) dsc.toString();
		String s = d.getString("DataHoraMedicao");
		String [] aux = s.split(" ");
		hora = aux[1];
		String [] data_aux = aux[0].split("/");
		ano = data_aux[2];
		mes = data_aux[1];
		dia = data_aux[0];
		String date = new String(ano + "-" + mes + "-" + dia + " " + hora);
		return date;
	}	
		
	
	public TimerTask Migrate() {
		
		MongoClient mongoClient1 = new MongoClient("localhost",27017); 

		MongoDatabase database = mongoClient1.getDatabase("sensores");
		MongoCollection<Document> collectionTemp = database.getCollection("SensorTemperatura");
		MongoCollection<Document> collectionLumi = database.getCollection("SensorLuminosidade");
		
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlmain?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		
		FindIterable<Document> cursorTemp = (FindIterable<Document>) collectionTemp.find();
		for (Document d : cursorTemp) {
//			System.out.println(d);
			String m = d.toJson().replace("{", "").replace("}", "").replace("\"", "");
//			System.out.println(m);
			String[] separar = m.split(",");
			String id_aux = separar[0];
			String[] separar_id_aux = id_aux.split(":");
			String id = separar_id_aux[2];
			String vm_aux = separar[2];
			String[] separar_vm_aux = vm_aux.split(":");
			String vm = separar_vm_aux[1];
			String dhm = aux(d);
			System.out.println(m);
			Statement stmt=con.createStatement();  
			int rs=stmt.executeUpdate("INSERT INTO medicaotemperatura VALUES(" + "\"" + dhm + "\"" + "," + "\"" + vm + "\"" + "," 
					 + "null" + "," + "\"" + id + "\"" + ")");
		}
		
		Statement stmt_to_check = con.createStatement();
		ResultSet check = stmt_to_check.executeQuery("SELECT IdMigracao from migracaoaux");
		while(check.next()) {
			String id_check = check.getString("IdMigracao").replace(" ", "");
			for(Document ds : cursorTemp) {
				String ss = ds.getObjectId("_id").toString().replace(" ", "");
				System.out.println(ss);
				System.out.println(id_check);
				if(ss.equals(id_check)) {
					Statement stmt=con.createStatement();  
					int rs=stmt.executeUpdate("DELETE FROM migracaoaux");
					collectionTemp.deleteOne(ds);
				} else {
					File file = new File("C://Users//filip//Desktop//SID//ficheiroTemp" + i + ".txt");
					if(file.createNewFile()) {
						System.out.println("entrei aqui");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						FileWriter writer = new FileWriter(file);
						writer.write("Não foi enviada uma medição de temperatura para o mysql no instante " + dtf.format(now));
						writer.close();
						
						i++;
					}
				}
			}
		}
				
		FindIterable<Document> cursorLumi = (FindIterable<Document>) collectionLumi.find();
		for (Document d : cursorLumi) {
			System.out.println(d);
			String m = d.toJson().replace("{", "").replace("}", "").replace("\"", "");
			System.out.println(m);
			String[] separar = m.split(",");
			String id_aux = separar[0];
			String[] separar_id_aux = id_aux.split(":");
			String id = separar_id_aux[2];
			String vm_aux = separar[2];
			String[] separar_vm_aux = vm_aux.split(":");
			String vm = separar_vm_aux[1];
			String dhm = aux(d);
			System.out.println(dhm);
			
			Statement stmt=con.createStatement();  
			int rs=stmt.executeUpdate("INSERT INTO medicaoluminosidade VALUES(" + "\"" + dhm + "\"" + "," + "null" + "," + "\"" + id + "\"" + "," 
	 + "\"" + vm + "\"" + ")");
		}
		
		Statement stmt_to_check2 = con.createStatement();
		ResultSet check2 = stmt_to_check2.executeQuery("SELECT IdMigracao from migracaoaux");
		while(check2.next()) {
			String id_check2 = check2.getString("IdMigracao").replace(" ", "");
			for(Document ds : cursorLumi) {
				String ss = ds.getObjectId("_id").toString().replace(" ", "" + "");
				System.out.println(ss);
				System.out.println(id_check2);
				if(ss.equals(id_check2)) {
					Statement stmt=con.createStatement();  
					int rs=stmt.executeUpdate("DELETE FROM migracaoaux");
					collectionLumi.deleteOne(ds);
				} else {
					File file = new File("C://Users//Alexandre//Desktop//SID//ficheiroLumi" + i + ".txt");
					if(file.createNewFile()) {
						System.out.println("entrei aqui");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						FileWriter writer = new FileWriter(file);
						writer.write("Não foi enviada uma medição de luminosidade para o mysql no instante " + dtf.format(now));
						writer.close();
						i++;
					}
				}					
			}
		}
		
		}catch(Exception e){ 
			System.out.println(e);
		}
		mongoClient1.close();
		return null;
		
		}
   
		public static void main(String[] args) {
			App app = new App();
			app.Migrate();
		}
}