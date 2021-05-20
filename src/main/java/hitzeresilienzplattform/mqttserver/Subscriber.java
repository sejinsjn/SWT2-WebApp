package hitzeresilienzplattform.mqttserver;

import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.entities.SensorItem;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.LinkedList;
import java.util.List;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER ==");
    String topicSWT = "SWT_SMART_CITY_SENSORS";
    MqttClient client=new MqttClient("tcp://compress.seelab.fh-dortmund.de:1883", MqttClient.generateClientId());
    MessageHandler handler = new MessageHandler();
    client.setCallback( handler );
    MqttConnectOptions opt = new MqttConnectOptions();
    opt.setUserName("swt2");
    opt.setPassword("sose2021".toCharArray());

    client.connect(opt);



    client.subscribe(topicSWT);
  }
}