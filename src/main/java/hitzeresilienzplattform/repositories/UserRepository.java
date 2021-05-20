package hitzeresilienzplattform.repositories;

import hitzeresilienzplattform.entities.SensorDaten;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface UserRepository extends CrudRepository<SensorDaten, Long> {
}
