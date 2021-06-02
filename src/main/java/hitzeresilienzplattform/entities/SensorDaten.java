package hitzeresilienzplattform.entities;


public class SensorDaten  {

    private String sensorId;
    private long timestamp;
    private double value;

    public String getSensorId() { return sensorId; }

    public void setSensorId(String sensorId) { this.sensorId = sensorId; }

    public long getTimestamp() { return timestamp; }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }
}
