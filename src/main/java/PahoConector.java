
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

public class PahoConector implements MqttCallback {

	IMqttClient client;
	private final int qos = 0;
	private String topic = "/sid_lab_2019_2";
	private boolean loop = true;
	public PahoConector() {
		
	}
	public void runApp() {
		  
		try {
			
			
		  	IMqttClient client = new MqttClient("iot.eclipse.org:443/ws", " js-utility-PLOYZ"); 	
		  	
		  	/*MongoClient mongoClient = new MongoClient( "localhost",27017);
		 	MongoDatabase database = mongoClient.getDatabase("mydb");
		 	*/
		 	
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
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		while(loop==true) {
			CountDownLatch receivedSignal = new CountDownLatch(10);

			String value = new String(message.getPayload());
			System.out.println(String.format("[%s] %s", topic, value));
			String array[] = value.split(":");
			String value1 = array[2];
			String[] array2 = value1.split("}");
			String temperatura = array2[0];
			//System.out.println(temperatura);
			
			receivedSignal.countDown();
			receivedSignal.await(5, TimeUnit.SECONDS);
		}

	}
	
	public void toMongo(int temp) {
		
		
		
	}
	
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost because: " + cause);
		System.exit(1);		
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}
}


