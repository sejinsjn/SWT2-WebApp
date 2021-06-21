package hitzeresilienzplattform.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Document (collection = "Sensor")
public class Baum implements Serializable{

    @Id
    private String id;
    private String title;
    private List<Sensor> sensors = new LinkedList();
    private MetaDaten metadaten;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<Sensor> getSensors() { return sensors; }

    public void setSensors(List<Sensor> sensors) { this.sensors = sensors; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public MetaDaten getMetadaten() { return metadaten; }

    public void setMetadaten(MetaDaten metadaten) { this.metadaten = metadaten; }

    @Override
    public String toString(){
        String s = this.title + "\n";
        for (Sensor item : sensors)
            s += item.toString() + "\n";
        return s;
    }
}