package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) throws Exception{
        Optional<Admin> adminOptional = adminRepository1.findById(adminId);
        if (!adminOptional.isPresent()){
            throw new Exception("Admin Not Found!!!");
        }

        Admin admin = adminOptional.get();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);

        admin.getServiceProviderList().add(serviceProvider);

        admin = adminRepository1.save(admin);

        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        Optional<ServiceProvider> serviceProviderOptional = serviceProviderRepository1.findById(serviceProviderId);
        if (!serviceProviderOptional.isPresent()){
            throw new Exception("Service Provider Not Found!!!");
        }

        ServiceProvider serviceProvider = serviceProviderOptional.get();

        CountryName countryName1 = null;
        boolean isPresent = false;
        // check whether the given country name is present in the CountryName enum or not....
        for (CountryName cn: CountryName.values()){
            if (cn.name().equalsIgnoreCase(countryName)){
                isPresent = true;
                countryName1 = cn;
                break;
            }
        }
        if (!isPresent){
            throw new Exception("Country not found");
        }

        // adding attributes of country..........
        Country country = new Country();
        country.setCountryName(countryName1);
        country.setCode(countryName1.toCode());
        country.setServiceProvider(serviceProvider);

        serviceProvider.getCountryList().add(country);

        serviceProvider = serviceProviderRepository1.save(serviceProvider);

        return serviceProvider;
    }
}
