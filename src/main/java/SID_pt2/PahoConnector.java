package SID_pt2;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

public class PahoConnector implements MqttCallback {

	MqttClient client;
	private final int qos = 0;
	private String topic = "/sid_lab_2019_2";
	private boolean loop = true;
	String clientID = MqttClient.generateClientId();
	int i=0;
	DB database;
	ArrayList<Double> listTemp = new ArrayList<Double>();
	ArrayList<Double> listHum = new ArrayList<Double>();
	
	public PahoConnector() {
		
	}
	public void runApp() {
		  
		try {
			System.out.println("entrei conecao");
			client = new MqttClient("tcp://broker.mqttdashboard.com", clientID, new MemoryPersistence()); 				
	    	client.setCallback(this);
	    	MqttConnectOptions options = new MqttConnectOptions();
	    	options.setAutomaticReconnect(true);
	    	options.setCleanSession(true);
	    	options.setConnectionTimeout(10);
	    	client.connect(options);
	    	client.subscribe(topic, qos); 
	    	
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
		try {
			
			MongoClient mongoClient = new MongoClient( "localhost",27017);
//			MongoCredential credential;
//		    credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
		 	database = mongoClient.getDB("sensores");
//		 	database.createCollection("SensorTemperatura", null);
//		 	database.createCollection("SensorLuminosidade", null);
		 	System.out.println("entrei mongo");

		} catch(Exception e) {
			//System.out.println(e);
		}
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception {

			String value = new String(message.getPayload());			
//			System.out.println(value);
			System.out.println();
			if (i>0) {
				System.out.println("entrei00");
				String clean = value.replace("{", "").replace("}", "").replace("\"", "").replace("sens", "").replace("eth", "");
				String [] value_aux = clean.split(",");
				String [] temp_aux = value_aux[0].split(":");
	//			char[] array = new char [5];
	//			value.getChars(8, 13, array, 0);
	//			String temperatura = String.valueOf(array);
				String temperatura = temp_aux[1];
	//			System.out.println(temperatura);
	//			char[] array1 = new char [5];
	//			value.getChars(22, 27, array1, 0);
	//			System.out.println(message);
				String [] cell_aux = value_aux[4].split(":");
				String cell = cell_aux[1];
				String []data_aux = value_aux[2].split(":");
				String data = data_aux[1];
				String []hora_aux = value_aux[3].split(":");
				String hora = hora_aux[1];
				String minutos = hora_aux[2];
				String segundos = hora_aux[3];
				String horaf = new String(hora + ":" + minutos + ":" + segundos);
				String hora_data = new String(data + " " + horaf);
	//			System.out.println(humidade);
				if (filtrarTemperatura(temperatura)==true) {
					toMongoTem(temperatura,hora_data);
					System.out.println("entrei0");
				}
				if (filtrarHumidade(cell)==true) {
					System.out.println("entrei1");
					toMongoHum(cell,hora_data);
				}
				
			}
			
			i++;
			try{ Thread.sleep(5000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
				
	}

	
	public boolean filtrarTemperatura(String t) {
		boolean valueAccepted=true;
		double d = Double.parseDouble(t);
		listTemp.add(d);
		double valorAnteriorUP = listTemp.get(0)*1.5;
		double valorAnteriorDOWN = listTemp.get(0)*0.5;
		if(listTemp.size()==1) {
			valueAccepted=true;
		} else if(listTemp.size()>1) {
			if(d > valorAnteriorUP || d < valorAnteriorDOWN) {
				valueAccepted=false;
				listTemp.remove(1);
			}
			else {
				valueAccepted=true;
				listTemp.remove(0);
				listTemp.set(0, d);
			}
		}
		return valueAccepted;
	}
	
	public boolean filtrarHumidade(String h) {
		boolean valueAccepted=true;
		double d = Double.parseDouble(h);
		listHum.add(d);
		double valorAnteriorUP = listHum.get(0)*2.5;
		double valorAnteriorDOWN = listHum.get(0)/2.5;
		if(listHum.size()==1) {
			valueAccepted=true;
		} else if(listHum.size()>1) {
			if(d > valorAnteriorUP || d < valorAnteriorDOWN) {
				valueAccepted=false;
				listHum.remove(1);
			}
			else {
				valueAccepted=true;
				listHum.remove(0);
				listHum.set(0, d);
			}
		}
		return valueAccepted;		
	}
	
	public void toMongoTem(String temp,String data) {
		Map<String, Object> documentMap = new HashMap<String, Object>();
		DBCollection collectionTemp = database.getCollection("SensorTemperatura");
//	 	BasicDBObject documentT = new BasicDBObject();
//	 	documentT.put("Temperatura", temp);
//	 	BasicDBObject documentHD = new BasicDBObject();
//	 	documentHD.put("DataHoraMedicao", data);
//	 	collectionTemp.insert(documentHD);
//	 	collectionTemp.insert(documentT);
		documentMap.put("ValorMedicao", temp);
		documentMap.put("DataHoraMedicao", data);
		collectionTemp.insert(new BasicDBObject(documentMap));
	 	
	}
	
	public void toMongoHum(String humidade, String data) {
		DBCollection collectionHum = database.getCollection("SensorLuminosidade");
//		BasicDBObject documentH = new BasicDBObject();
//		documentH.put("Luminosidade", humidade);
//		BasicDBObject documentHD = new BasicDBObject();
//	 	documentHD.put("DataHoraMedicao", data);
//	 	collectionHum.insert(documentHD);
//		collectionHum.insert(documentH);
		System.out.println("Entrieii");
		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("ValorMedicao", humidade);
		documentMap.put("DataHoraMedicao", data);
		collectionHum.insert(new BasicDBObject(documentMap));
	}
	
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost because: " + cause);
		System.exit(1);		
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}
}

