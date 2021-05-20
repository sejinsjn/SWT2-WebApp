package hitzeresilienzplattform.services;

import hitzeresilienzplattform.entities.SensorDaten;

import java.util.List;

public interface UserService {
    List<SensorDaten> getAllUsers();
    SensorDaten createUser(SensorDaten user);
    void deleteUser(long userId);
}
