package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        Optional<User> userOptional = userRepository2.findById(userId);
        if (!userOptional.isPresent()){
            throw new Exception("User not Found!!!");
        }
        User user = userOptional.get();

        if (user.getCountry().getCountryName().name().equalsIgnoreCase(countryName)){
            // here the user country ans the countryMentioned are same then no need of connection.....
            return user;
        }

        // gtting the serviceProviderList for the particular user, and checking whether it is connected to
        // any servicerovider or not if connected to any then will throw an exception....
        if (user.getConnectionList().size() > 0){
            throw new Exception("Already connected");
        }

        Connection connection = new Connection();
        connection = connectionRepository2.save(connection);
        connection.setUser(user);
        user.getConnectionList().add(connection);

        return userRepository2.save(user);
    }

    @Override
    public User disconnect(int userId) throws Exception {
        return null;
    }

    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        return null;
    }
}
