package uberlite.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

import uberlite.model.Ride;
import uberlite.model.User;

public class UserDAOImpl implements UserDAO {
    private List<User> users;
    
    private Map<User, List<Ride>> userRides;



    public UserDAOImpl() {
        this.users = new ArrayList<>();
        this.userRides = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
    
    @Override
    public void addRide(User user, Ride ride) {
        List<Ride> rides = userRides.getOrDefault(user, new ArrayList<>());
        rides.add(ride);
        userRides.put(user, rides);
    }
}


