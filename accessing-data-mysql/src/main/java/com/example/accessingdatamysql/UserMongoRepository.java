package com.example.accessingdatamysql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
    List<UserMongo> findByName(String name);
    List<UserMongo> findByEmail(String email);
}
