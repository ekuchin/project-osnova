package ru.projectosnova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.projectosnova.config.*;

@SpringBootApplication
public class ProjectOsnovaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProjectOsnovaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}