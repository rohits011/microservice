package com.rohit.microservice.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rohit.microservice.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
