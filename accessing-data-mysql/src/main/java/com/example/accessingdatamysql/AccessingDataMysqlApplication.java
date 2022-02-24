package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// sql in the root of the classpath is executed on startup if Hibernate creates the schema from scratch
// (that is, if the ddl-auto property is set to create or create-drop).
// https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization
// Liquibase is better still.

// Spring Boot by default returns index.html in public/static/webapp folder
// when / is hit, given that we have overridden the default / in any controller.

// @EnableWebMvc annotation switches off all the things that Spring Boot does for you in WebMvcAutoConfiguration
@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

}
