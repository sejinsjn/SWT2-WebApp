package hitzeresilienzplattform.entities;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Baum")
public class MetaDaten {

    @Id
    private String id;
    @JsonIgnore
    private double geokoordinaten[] = new double[2];
    @JsonIgnore
    private boolean sensorstatus;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public double[] getGeokoordinaten() { return geokoordinaten; }

    public void setGeokoordinaten(double[] geokoordinaten) { this.geokoordinaten = geokoordinaten; }

    public boolean isSensorstatus() { return sensorstatus; }

    public void setSensorstatus(boolean sensorstatus) { this.sensorstatus = sensorstatus; }

    @Override
    public String toString(){ return this.geokoordinaten + ", " + this.sensorstatus;}
}
