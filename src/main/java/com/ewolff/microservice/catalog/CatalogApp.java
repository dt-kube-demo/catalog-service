package com.ewolff.microservice.catalog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Component
public class CatalogApp {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogApp(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@PostConstruct
	public void generateTestData() {
		itemRepository.save(new Item("iPod", 42.0));
		itemRepository.save(new Item("iPod touch", 21.0));
		itemRepository.save(new Item("iPod nano", 1.0));
		itemRepository.save(new Item("Apple TV", 100.0));
		itemRepository.save(new Item("iPad Pro", 500.0));
		itemRepository.save(new Item("iPad Air", 400.0));
		itemRepository.save(new Item("iPad Mini", 300.0));
		itemRepository.save(new Item("iPhone X", 1000.0));
		itemRepository.save(new Item("iPhone 8", 800.0));
		itemRepository.save(new Item("Mac Pro", 2000.0));
		itemRepository.save(new Item("Mac Air", 1200.0));
		itemRepository.save(new Item("Mac Mini", 1000.0));
		itemRepository.save(new Item("Apple Watch", 500.0));
		itemRepository.save(new Item("iMac", 2000.0));
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogApp.class, args);
	}

}
