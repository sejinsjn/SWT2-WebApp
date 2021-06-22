package hitzeresilienzplattform.service;

import hitzeresilienzplattform.entities.Baum;

import java.util.List;

public interface ISensorService {
    Baum addSensor(Baum baum);
    List<Baum> getAllSensor();
    List<Baum> findBySensorsTimestampGreaterThan(long timestamp);
}
