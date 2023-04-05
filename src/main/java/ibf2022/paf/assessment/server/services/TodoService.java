package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.Payload.taskPayload;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7
@Service
public class TodoService {

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    UserRepository userRepo;

    @Transactional
    public Boolean upsertTask(taskPayload payload){

        Boolean proceed = true;
        String username = payload.getUsername();
        List<Task> tasks = payload.getTasks();

        // check if user exist
        while (proceed){

            Optional<User> optUser = userRepo.findUserByUsername(username);

            if (optUser.isEmpty()){

                // create new user in MySQL
                User newUser = new User(username);
                String userId = userRepo.insertUser(newUser);
                if (userId.equals("Error")){
                    proceed = false;
                    break;
                }
                // insert list of tasks
                for (int i = 0; i < tasks.size(); i++){
                    Integer inserted = taskRepo.insertTask(tasks.get(i), userId);
                    if (inserted < 1){
                        proceed = false;
                        break;
                    }
                }
                break;
            }
            else {
                // get user
                User existingUser = optUser.get();
                for (int i = 0; i < tasks.size(); i++){
                    Integer taskInserted = taskRepo.insertTask(tasks.get(i), existingUser.getUserId());
                    if (taskInserted < 1){
                        proceed = false;
                        break;
                    }
                }
                break;
            }

        }
        return proceed;
    }
}
