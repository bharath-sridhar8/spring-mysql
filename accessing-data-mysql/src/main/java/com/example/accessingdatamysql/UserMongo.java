package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.Id;

public class UserMongo {

    public UserMongo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserMongo(String name, String email) {
        this.name = name;
        this.email = email;
    }

//    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @GeneratedValue(generator = "uuid")
    @Id
//    @org.springframework.data.annotation.Id
    private String id;

    @Override
    public String toString() {
        return "UserMongo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String name;
    private String email;
}
