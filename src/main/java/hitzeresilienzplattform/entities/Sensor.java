package hitzeresilienzplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Sensor implements Serializable {

    @Id
    @GeneratedValue
    private String title;
    private List<SensorItem> sensors;

    public Sensor(String title, List<SensorItem> sensors) {
        this.title = title;
        this.sensors = sensors;
    }

    public Sensor(String title) {
        this.title = title;
        this.sensors = new LinkedList();
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<SensorItem> getSensors() { return sensors; }

    public void setSensors(List<SensorItem> sensors) { this.sensors = sensors; }

    @Override
    public String toString(){
        String s = this.title + "\n";
        for (SensorItem item : sensors)
            s += item.toString() + "\n";
        return s;
    }
}