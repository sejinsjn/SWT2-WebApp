package hitzeresilienzplattform.service;

import hitzeresilienzplattform.entities.Baum;
import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sensorService")
public class SensorServiceImpl implements ISensorService {

    private SensorRepository sensorRepository;

    @Override
    public Baum addSensor(Baum baum) {
       return sensorRepository.save(baum);
    }

    @Override
    public List<Baum> getAllSensor() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Baum> findBySensorsTimestampGreaterThan(long timestamp) {
        return sensorRepository.findBySensorsTimestampGreaterThan(timestamp);
    }

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository)
    {
        this.sensorRepository = sensorRepository;
    }
}
