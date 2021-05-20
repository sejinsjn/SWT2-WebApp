package hitzeresilienzplattform.entities;

import java.util.List;

public class SensorItem {

    private String name;
    private String id;
    private String timestamp;
    private double value;

    public SensorItem(String name, String id, String timestamp, double value) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    public SensorItem(String id, String timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

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
