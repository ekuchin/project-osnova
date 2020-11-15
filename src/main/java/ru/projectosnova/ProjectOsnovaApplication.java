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

	}

}