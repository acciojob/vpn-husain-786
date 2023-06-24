package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        CountryName countryName1 = null;
        for (CountryName cn: CountryName.values()){
            if (cn.name().equalsIgnoreCase(countryName)){
                countryName1 = cn;
                break;
            }
        }
        if (countryName1 == null){
            throw new Exception("Invalid Country Code!!!!!");
        }

        Country country = countryRepository3.findByCountryName();

        User user = new User();
        user = userRepository3.save(user);

        user.setUsername(username);
        user.setPassword(password);
        user.setOriginalIp(country.getCode()+"."+user.getId());

        country.setUser(user);
        user.setCountry(country);

        return userRepository3.save(user);
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) throws Exception {
        Optional<User> userOptional = userRepository3.findById(userId);
        if (!userOptional.isPresent()){
            throw new Exception("User is Missing!!!!!");
        }

        Optional<ServiceProvider> serviceProviderOptional = serviceProviderRepository3.findById(serviceProviderId);
        if (!serviceProviderOptional.isPresent()){
            throw new Exception("Service Provider is not present!!!");
        }

        User user = userOptional.get();
        ServiceProvider serviceProvider = serviceProviderOptional.get();

        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUserList().add(user);

        serviceProviderRepository3.save(serviceProvider);

        return userRepository3.save(user);
    }
}
