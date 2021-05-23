package hitzeresilienzplattform.repositories;

import hitzeresilienzplattform.entities.Sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, Integer> {
}
