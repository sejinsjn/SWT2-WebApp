package hitzeresilienzplattform.mqttserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.entities.SensorItem;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.LinkedList;
import java.util.List;

public class MessageHandler implements MqttCallback {

    private String json;
    private int counter = 0;
    private Sensor b000 = new Sensor("Baum-000"), b001 = new Sensor("Baum-001"), b002 = new Sensor("Baum-002");


    public void connectionLost(Throwable throwable) {
        System.err.println(throwable);
        System.out.println("Connection to MQTT broker lost!");
    }

    public String getJson(){ return this.json; }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {this.json = new String(mqttMessage.getPayload());
        SensorDaten daten = new ObjectMapper().readValue(this.json, SensorDaten.class);

        if(counter <= 4)
            addSensorItem(b000, daten, counter);

        if(counter > 4 && counter <= 9)
            addSensorItem(b001, daten, counter);

        if(counter > 9)
            addSensorItem(b002, daten, counter);

        if(counter == 14)
            System.out.println(b000 + "\n" + b001 + "\n" + b002);

        counter = (counter + 1) % 15;
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) { }

    public void addSensorItem(Sensor sensor, SensorDaten daten, int counter){
        SensorItem item = new SensorItem(daten.getSensorId(), daten.getTimestamp(), daten.getValue());
        switch(counter % 5){
            case 0:
                item.setName("Helligkeit");
                sensor.getSensors().add(item);
                break;
            case 1:
                item.setName("Niederschlag");
                sensor.getSensors().add(item);
                break;
            case 2:
                item.setName("Luftfeuchtigkeit");
                sensor.getSensors().add(item);
                break;
            case 3:
                item.setName("Bodenfeuchtigkeit");
                sensor.getSensors().add(item);
                break;
            case 4:
                item.setName("Temperatur");
                sensor.getSensors().add(item);
                break;
        }
    }
}
