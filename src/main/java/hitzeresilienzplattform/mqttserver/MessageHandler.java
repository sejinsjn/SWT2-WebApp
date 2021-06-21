package hitzeresilienzplattform.mqttserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hitzeresilienzplattform.entities.Baum;
import hitzeresilienzplattform.entities.MetaDaten;
import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.entities.Sensor;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

public class MessageHandler implements MqttCallback {

    private String json;
    private int counter = 0;
    private Baum b000, b001, b002;

    public MessageHandler() {
        initBaume();
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
        System.err.println(throwable);
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        this.json = new String(mqttMessage.getPayload());
        SensorDaten daten = new ObjectMapper().readValue(this.json, SensorDaten.class);
        List<Sensor> b000S = b000.getSensors(), b001S = b001.getSensors(), b002S = b002.getSensors();


        if(idExists(b000S, daten.getSensorId()) != -1){
            int id = idExists(b000S, daten.getSensorId());
            b000S.get(id).setTimestamp(daten.getTimestamp());
            b000S.get(id).setValue(daten.getValue());
            b000.setSensors(b000S);
            counter++;
        }else{
            if(idExists(b001S, daten.getSensorId()) != -1){
                int id = idExists(b001S, daten.getSensorId());
                b001S.get(id).setTimestamp(daten.getTimestamp());
                b001S.get(id).setValue(daten.getValue());
                b001.setSensors(b001S);
                counter++;
            }else{
                if(idExists(b002S, daten.getSensorId()) != -1){
                    int id = idExists(b002S, daten.getSensorId());
                    b002S.get(id).setTimestamp(daten.getTimestamp());
                    b002S.get(id).setValue(daten.getValue());
                    b002.setSensors(b002S);
                    counter++;
                }
            }
        }

        if (counter == 14) {
            final String uri = "http://localhost:8080/create";
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForEntity(uri, b000, Baum.class, Sensor.class, MetaDaten.class);
            restTemplate.postForEntity(uri, b001, Baum.class, Sensor.class, MetaDaten.class);
            restTemplate.postForEntity(uri, b002, Baum.class, Sensor.class, MetaDaten.class);

            System.out.println(b000 + "\n" + b001 + "\n" + b002);

            initBaume();
        }

        counter = (counter + 1) % 15;
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public int idExists(List<Sensor> sensors, String id){
        for(Sensor s : sensors)
            if (s.getId().equals(id))
                return sensors.indexOf(s);
        return -1;
    }

    public MetaDaten addMetaDate(boolean sensorstatus, double[] geoko){
        MetaDaten metadaten = new MetaDaten();

        metadaten.setSensorstatus(sensorstatus);
        metadaten.setGeokoordinaten(geoko);

        return metadaten;
    }

    public Baum init(String title, String[] sensorsID, double[] geoko){
        Baum b = new Baum();
        Sensor s = new Sensor();
        List<Sensor> sensors = new LinkedList<>();

        b.setTitle(title);

        s.setName("Helligkeit");
        s.setId(sensorsID[0]);
        sensors.add(s);
        s = new Sensor();
        s.setName("Niederschlag");
        s.setId(sensorsID[1]);
        sensors.add(s);
        s = new Sensor();
        s.setName("Luftfeuchtigkeit");
        s.setId(sensorsID[2]);
        sensors.add(s);
        s = new Sensor();
        s.setName("Bodenfeuchtigkeit");
        s.setId(sensorsID[3]);
        sensors.add(s);
        s = new Sensor();
        s.setName("Temperatur");
        s.setId(sensorsID[4]);
        sensors.add(s);

        b.setSensors(sensors);
        b.setMetadaten(addMetaDate(true, geoko));

        return b;
    }

    public void initBaume(){
        String[] b000ID = {"32a50497-ce45-47df-87a8-494773598b37", "b4724410-29cf-46e6-8f7d-07508c7c025a", "39da0032-7262-4d37-bbba-0943c6fcdfa0",
                "8f1dc4f5-21bd-49d3-8bbb-9015b04293a4", "678ef2cf-a46c-42f3-85dc-65b92c6fc291"};
        double[] b000GK = {51.510879, 7.464040};
        this.b000 = init("Baum-000", b000ID, b000GK);
        String[] b001ID = {"911a4d2d-6729-4a00-9800-96eabbf5099b", "bda4274f-af4e-4485-b58b-b7f036296d64", "fb90470c-12a2-407c-ac3a-d4efa3ee4241",
                "6583c19b-15f7-42a9-84a2-cbc7734ca536", "0e69a0d6-5445-4371-852d-4677799bcb01"};
        double[] b001GK = {51.512875, 7.471388};
        this.b001 = init("Baum-001", b001ID, b001GK);
        String[] b002ID = {"e749193c-6ecc-443c-a70e-c774d235dea7", "556931c7-cf87-440a-af3f-58a676f70afb", "d9b9625a-d8f2-4b1e-a346-3285f56fbcbd",
                "1238e8e3-11d3-42e1-ade7-329b44964c78", "dd9655f2-d896-47e5-9a0c-4f75b88e1acd"};
        double[] b002GK = {51.514774, 7.454719};
        this.b002 = init("Baum-002", b002ID, b002GK);
    }
}