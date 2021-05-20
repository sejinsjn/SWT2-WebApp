package hitzeresilienzplattform.entities;

import net.minidev.json.annotate.JsonIgnore;

public class SensorDaten  {

    private String sensorId;
    private String timestamp;
    private double value;
    @JsonIgnore
    private String geoKo;

    public String getSensorId() { return sensorId; }

    public void setSensorId(String sensorId) { this.sensorId = sensorId; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public String getGeoKo() { return geoKo; }

    public void setGeoKo(String geoKo) { this.geoKo = geoKo; }
}
