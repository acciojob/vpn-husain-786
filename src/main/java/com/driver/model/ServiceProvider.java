package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn
    private Admin admin;

    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Connection> connectionList;

    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Country> countryList = new ArrayList<>();
    @ManyToMany
    @JoinColumn
    private List<User> users = new ArrayList<>();

    public ServiceProvider() {
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Admin getAdmin() {
        return admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}