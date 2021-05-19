package hitzeresilienzplattform.mqttserver;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER ==");
    String topicSWT = "SWT_SMART_CITY_SENSORS";
    String topicTest = "test01";
    MqttClient client=new MqttClient("tcp://compress.seelab.fh-dortmund.de:1883", MqttClient.generateClientId());
    MessageHandler handler = new MessageHandler();
    client.setCallback( handler );
    MqttConnectOptions opt = new MqttConnectOptions();
    opt.setUserName("swt2");
    opt.setPassword("sose2021".toCharArray());

    client.connect(opt);

    client.subscribe(topicTest);
  }
}