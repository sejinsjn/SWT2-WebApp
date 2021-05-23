package DAL;

import hitzeresilienzplattform.entities.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorDALImpl implements SensorDAL{

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Sensor> getAllSensors() {  return template.findAll(Sensor.class); }

    @Override
    public Sensor addNewSensor(Sensor sensor) {
        template.save(sensor);
        return sensor;
    }
}
