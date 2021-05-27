package hitzeresilienzplattform.entities;


public class SensorDaten  {

    private String sensorId;
    private String timestamp;
    private double value;

    public String getSensorId() { return sensorId; }

    public void setSensorId(String sensorId) { this.sensorId = sensorId; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }
}
