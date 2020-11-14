package ru.projectosnova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import ru.projectosnova.config.*;
import ru.projectosnova.store.Store;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
public class ProjectOsnovaApplication implements CommandLineRunner{

	@Autowired
	Config config;

	public static void main(String[] args) {
		SpringApplication.run(ProjectOsnovaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	System.out.println("Hello, Osnova");

	//config.getConnections().forEach(System.out::println);
	//config.getTypes().forEach(System.out::println);

	Store store = config.getStore("zef","visit");

	String upd = "{\"street\":\"Айвазовского\"}";

	//boolean result = store.update("5fa6d3dde7ac6574acd9e18c",upd,true);
	//System.out.println(result);

	//store.findAllAsList("visit").forEach(System.out::println);

	}

}