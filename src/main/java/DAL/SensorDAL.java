package DAL;

import hitzeresilienzplattform.entities.Sensor;

import java.util.List;

public interface SensorDAL {

    List<Sensor> getAllSensors();

    Sensor addNewSensor(Sensor sensor);
}
