package hitzeresilienzplattform.mqttserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hitzeresilienzplattform.entities.MetaDaten;
import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.entities.SensorItem;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
        System.out.println("Connection to MQTT broker lost!");
        System.err.println(throwable);
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        this.json = new String(mqttMessage.getPayload());
        SensorDaten daten = new ObjectMapper().readValue(this.json, SensorDaten.class);
        double[] geoko = new double[2];
        boolean sensorstatus = false;

        switch(counter){
            case 0:
                geoko[0] = 51.510879;
                geoko[1] = 7.464040;
                if(daten != null)
                    sensorstatus = true;
                b000.setMetadaten(addMetaDate(sensorstatus, geoko));
                break;
            case 5:
                geoko[0] = 51.512875;
                geoko[1] = 7.471388;
                if(daten != null)
                    sensorstatus = true;
                b001.setMetadaten(addMetaDate(sensorstatus, geoko));
                break;
            case 10:
                geoko[0] = 51.514774;
                geoko[1] = 7.454719;
                if(daten != null)
                    sensorstatus = true;
                b002.setMetadaten(addMetaDate(sensorstatus, geoko));
                break;
        }

        if (counter <= 4)
            addSensorItem(b000, daten, counter);

        if (counter > 4 && counter <= 9)
            addSensorItem(b001, daten, counter);

        if (counter > 9)
            addSensorItem(b002, daten, counter);

        if (counter == 14) {
            final String uri = "http://localhost:8080/create";
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForEntity(uri, b000, Sensor.class, SensorItem.class, MetaDaten.class);
            restTemplate.postForEntity(uri, b001, Sensor.class, SensorItem.class, MetaDaten.class);
            restTemplate.postForEntity(uri, b002, Sensor.class, SensorItem.class, MetaDaten.class);

            System.out.println(b000 + "\n" + b001 + "\n" + b002);

            b000.setSensors(new LinkedList<>());
            b001.setSensors(new LinkedList<>());
            b002.setSensors(new LinkedList<>());
        }

        counter = (counter + 1) % 15;
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public void addSensorItem(Sensor sensor, SensorDaten daten, int counter) {
        SensorItem item = new SensorItem();
        item.setId(daten.getSensorId());
        item.setTimestamp(daten.getTimestamp());
        item.setValue(daten.getValue());

        switch (counter % 5) {
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

    public MetaDaten addMetaDate(boolean sensorstatus, double[] geoko){
        MetaDaten metadaten = new MetaDaten();

        metadaten.setSensorstatus(sensorstatus);
        metadaten.setGeokoordinaten(geoko);

        return metadaten;
    }
}