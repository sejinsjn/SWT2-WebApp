package hitzeresilienzplattform.mqttserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.entities.SensorItem;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

public class MessageHandler implements MqttCallback {

    private String json;
    private int counter = 0;
    private Sensor b000 = new Sensor(), b001 = new Sensor(), b002 = new Sensor();

    public MessageHandler() {
        this.b000.setTitle("Baum-000");
        this.b001.setTitle("Baum-001");
        this.b002.setTitle("Baum-002");
    }

    public void connectionLost(Throwable throwable) {
        System.err.println(throwable);
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {this.json = new String(mqttMessage.getPayload());
        SensorDaten daten = new ObjectMapper().readValue(this.json, SensorDaten.class);

        if(counter <= 4)
            addSensorItem(b000, daten, counter);

        if(counter > 4 && counter <= 9)
            addSensorItem(b001, daten, counter);

        if(counter > 9)
            addSensorItem(b002, daten, counter);

        if(counter == 14){
            final String uri = "http://localhost:8080/create";
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForEntity(uri, b000, Sensor.class, SensorItem.class);
            restTemplate.postForEntity(uri, b001, Sensor.class, SensorItem.class);
            restTemplate.postForEntity(uri, b002, Sensor.class, SensorItem.class);

            System.out.println(b000 + "\n" + b001 + "\n" + b002);
            b000.setSensors(new LinkedList<>());
            b001.setSensors(new LinkedList<>());
            b002.setSensors(new LinkedList<>());
        }

        counter = (counter + 1) % 15;
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) { }

    public void addSensorItem(Sensor sensor, SensorDaten daten, int counter){
        SensorItem item = new SensorItem();
        item.setId(daten.getSensorId());
        item.setTimestamp(daten.getTimestamp());
        item.setValue(daten.getValue());
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
