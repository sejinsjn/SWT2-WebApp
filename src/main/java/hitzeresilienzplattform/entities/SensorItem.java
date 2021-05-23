package hitzeresilienzplattform.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "Sensor")
public class SensorItem {

    private String name;
    @Id
    private String id;
    private String timestamp;
    private double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) { this.id = id; }

    public String getId() { return id; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public double getValue() {  return value; }

    public void setValue(double value) { this.value = value; }

    @Override
    public String toString(){
        return this.name + ", " + this.id + ", " + this.timestamp + ", " + this.value;
    }
}
