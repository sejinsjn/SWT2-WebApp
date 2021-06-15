package hitzeresilienzplattform.repositories;

import hitzeresilienzplattform.entities.Sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, Integer> {

    public List<Sensor> findBySensorsTimestampGreaterThan(long timestamp);
}
