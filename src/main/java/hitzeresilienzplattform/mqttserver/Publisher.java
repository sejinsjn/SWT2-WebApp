package hitzeresilienzplattform.mqttserver;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

  public static void main(String[] args) throws MqttException {

    String messageString = "Hello World from Java!";

    if (args.length == 2 ) {
      messageString = args[1];
    }

    System.out.println("== START PUBLISHER ==");

    MqttClient client = new MqttClient("tcp://compress.seelab.fh-dortmund.de:1883", MqttClient.generateClientId());
    MqttConnectOptions opt = new MqttConnectOptions();
    opt.setUserName("swt2");
    opt.setPassword("sose2021".toCharArray());

    client.connect(opt);
    MqttMessage message = new MqttMessage();
    message.setPayload(messageString.getBytes());
    client.publish("fhdo/fb4/swt2", message);

    System.out.println("\tMessage '"+ messageString +"' to 'iot_data'");

    client.disconnect();

    System.out.println("== END PUBLISHER ==");
  }
}