package hitzeresilienzplattform.services;

import hitzeresilienzplattform.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUser(long userId);
}
