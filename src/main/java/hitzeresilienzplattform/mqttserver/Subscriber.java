package hitzeresilienzplattform.mqttserver;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER ==");

    MqttClient client=new MqttClient("tcp://compress.seelab.fh-dortmund.de:1883", MqttClient.generateClientId());
    client.setCallback( new SimpleMqttCallBack() );
    MqttConnectOptions opt = new MqttConnectOptions();
    opt.setUserName("swt2");
    opt.setPassword("sose2021".toCharArray());

    client.connect(opt);

    client.subscribe("fhdo/fb4/swt2");
  }
}