package com.innometrics;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationTests {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void keyDoesNotExits() {
        when().get("/innometrics/internal/counter/jim")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }


    @Test
    public void postSuccess() {
        when().post("/innometrics/internal/counter/jane")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterName", equalTo("jane")).body("counterValue", equalTo(1));
    }

    @Test
    public void postSuccessWithIncremented() {
        when().post("/innometrics/internal/counter/joe")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterName", equalTo("joe")).body("counterValue", equalTo(1));
        when().post("/innometrics/internal/counter/joe")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterName", equalTo("joe")).body("counterValue", equalTo(2));
    }

    @Test
    public void getList() {
        when().post("/innometrics/internal/counter/kim")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterName", equalTo("kim")).body("counterValue", equalTo(1));
        when().post("/innometrics/internal/counter/paris")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterName", equalTo("paris")).body("counterValue", equalTo(1));
        when().get("/innometrics/internal/counter/list")
                .then()
                .statusCode(HttpStatus.SC_OK).body("counterNames", hasItems("kim", "paris"));
    }

}
