package com.driver.model;

import javax.persistence.*;

// Note: Do not write @Enumerated annotation above CountryName in this model.
@Entity
@Table
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CountryName countryName;
    private String code;
    //    private String originalCountry;
    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;
    @OneToOne
    @JoinColumn
    private User user;

    public Country() {
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public String getCode() {
        return code;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public User getUser() {
        return user;
    }
}