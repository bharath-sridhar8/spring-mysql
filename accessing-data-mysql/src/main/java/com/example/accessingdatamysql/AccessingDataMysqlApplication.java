package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

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
@EnableScheduling
@EnableAsync
public class AccessingDataMysqlApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AccessingDataMysqlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

	@Autowired
	private UserMongoRepository userMongoRepository;

	@Autowired
	private AsyncService asyncService;

	@Override
	public void run(String... args) throws Exception {
//		userMongoRepository.save(new UserMongo("user_1", "user_1@abc.com"));
		userMongoRepository.save(new UserMongo("user_2", "user_2@abc.com"));
		userMongoRepository.findAll().stream().forEach(user -> {
			System.out.println(user);
		});
//		userMongoRepository.findByName("user_1").forEach(user -> System.out.println(user));
//		userMongoRepository.findByEmail("user_2@abc.com").forEach(user -> System.out.println(user));

		long start = System.currentTimeMillis();

		CompletableFuture<GithubUser> page1 = asyncService.getUser("PivotalSoftware");
		CompletableFuture<GithubUser> page2 = asyncService.getUser("CloudFoundry");
		CompletableFuture<GithubUser> page3 = asyncService.getUser("Spring-Projects");

		CompletableFuture.allOf(page1, page2, page3).join();

		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + page1.get());
		logger.info("--> " + page2.get());
		logger.info("--> " + page3.get());
	}
}
