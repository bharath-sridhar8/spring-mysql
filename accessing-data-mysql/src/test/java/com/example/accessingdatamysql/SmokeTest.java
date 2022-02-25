package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// A nice feature of the Spring Test support is that the application context is cached between tests.

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void controllerExists() {
        assertThat(homeController).isNotNull();
    }
}
