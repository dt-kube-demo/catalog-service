package com.ewolff.microservice.catalog;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "catalog", path = "catalog")
public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByName(@Param("name") String name);

	List<Item> findByNameContaining(@Param("name") String name);

}
