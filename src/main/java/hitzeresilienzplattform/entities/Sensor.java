package hitzeresilienzplattform.entities;


import java.util.List;

public class Sensor{

    private String title;
    private List<SensorItem> sensors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SensorItem> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorItem> sensors) {
        this.sensors = sensors;
    }
}