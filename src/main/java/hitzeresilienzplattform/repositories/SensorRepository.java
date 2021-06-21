package hitzeresilienzplattform.repositories;

import hitzeresilienzplattform.entities.Baum;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends MongoRepository<Baum, Integer> {

    public List<Baum> findBySensorsTimestampGreaterThan(long timestamp);
}
