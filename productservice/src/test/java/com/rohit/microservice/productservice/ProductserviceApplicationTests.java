package com.rohit.microservice.productservice;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.shaded.org.hamcrest.Matchers;

import io.restassured.RestAssured;

//@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT )
class ProductserviceApplicationTests {
	
	@ServiceConnection
	static MongoDBContainer container=new MongoDBContainer("mongodb:latest");
	
	@LocalServerPort
	private Integer port;
	
	@BeforeEach
	void setUp() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=port;
    }
	static {
		container.start();
	}
	
	@Test
    void createProductTest() {
		String requestBody="""
				{
				"name":"iphone",
				"description":"smartphonde",
				"price":45000
				}
				""";
		RestAssured.given().contentType("application/json")
		.body(requestBody)
		.when()
		.post("/api/products")
		.then()
		.statusCode(201)
		.body("id",Matchers.notNullValue())
		.body("name",Matchers.equalTo("iphone"))
		.body("description",Matchers.equalTo("smartphonde"))
		.body("price",Matchers.equalTo(45000));
		
	}
	@Test
	void contextLoads() {
	}

}
