package uberlite.dao;
import java.util.List;
import java.util.Optional;

import uberlite.model.Ride;
import uberlite.model.User;

public interface UserDAO {
    void addUser(User user);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    void addRide(User user, Ride ride);
}
