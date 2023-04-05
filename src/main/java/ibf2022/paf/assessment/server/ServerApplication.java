package ibf2022.paf.assessment.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import ibf2022.paf.assessment.server.Payload.taskPayload;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoService;

@SpringBootApplication
// public class ServerApplication implements CommandLineRunner{
public class ServerApplication{
	
	// @Autowired
	// TodoService todoSvc;

	// @Autowired
	// TaskRepository taskRepo;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}


	
}
