package hitzeresilienzplattform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Meta;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Document (collection = "Sensor")
public class Sensor implements Serializable{

    @Id
    private String id;
    private String title;
    private List<SensorItem> sensors = new LinkedList();
    private MetaDaten metadaten;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<SensorItem> getSensors() { return sensors; }

    public void setSensors(List<SensorItem> sensors) { this.sensors = sensors; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public MetaDaten getMetadaten() { return metadaten; }

    public void setMetadaten(MetaDaten metadaten) { this.metadaten = metadaten; }

    @Override
    public String toString(){
        String s = this.title + "\n";
        for (SensorItem item : sensors)
            s += item.toString() + "\n";
        return s;
    }
}