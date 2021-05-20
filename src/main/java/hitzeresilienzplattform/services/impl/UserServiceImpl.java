package hitzeresilienzplattform.services.impl;

import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.repositories.UserRepository;
import hitzeresilienzplattform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public List<SensorDaten> getAllUsers() {
        List<SensorDaten> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public SensorDaten createUser(SensorDaten user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
