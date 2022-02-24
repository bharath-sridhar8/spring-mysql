package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// sql in the root of the classpath is executed on startup if Hibernate creates the schema from scratch
// (that is, if the ddl-auto property is set to create or create-drop).
// https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization
// Liquibase is better still.

// Spring Boot by default returns index.html in public/static/webapp folder
// when / is hit, given that we have overridden the default / in any controller.

// @EnableWebMvc annotation switches off all the things that Spring Boot does for you in WebMvcAutoConfiguration
// EnableMongoRepositories is not needed, similar to how EnableJPARepositories was not needed.
// JPA is relational, meant for relational databases (ORM). Spring Data JPA is parallel to Spring Data MongoDB.
// spring-boot-starter-data-mongodb was needed. Not just spring-boot-mongodb.
@SpringBootApplication
public class AccessingDataMysqlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

	@Autowired
	private UserMongoRepository userMongoRepository;

	@Override
	public void run(String... args) throws Exception {
		userMongoRepository.save(new UserMongo("user_1", "user_1@abc.com"));
		userMongoRepository.save(new UserMongo("user_2", "user_2@abc.com"));
		userMongoRepository.findAll().stream().forEach(user -> {
			System.out.println(user);
		});
		userMongoRepository.findByName("user_1").forEach(user -> System.out.println(user));
		userMongoRepository.findByEmail("user_2@abc.com").forEach(user -> System.out.println(user));
	}
}
