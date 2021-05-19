package hitzeresilienzplattform.mqttserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hitzeresilienzplattform.entities.Sensor;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class MessageHandler implements MqttCallback {

    private String json = "";

    public void connectionLost(Throwable throwable) {
        System.err.println(throwable);
        System.out.println("Connection to MQTT broker lost!");
    }

    public String getJson(){ return this.json; }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        this.json = new String(mqttMessage.getPayload());

        List<Sensor> users = new ObjectMapper().readValue(this.json, new TypeReference<List<Sensor>>() {});

        System.out.println(users.get(0).getSensors().get(0).getName() + " " + users.get(1).getSensors().get(1).getName());
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) { }
}
