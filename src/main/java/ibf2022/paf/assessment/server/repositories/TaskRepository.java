package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 6
@Repository
public class TaskRepository {
    @Autowired
    private JdbcTemplate template;

    private String INSERT_TASK_SQL = "insert into task (description, priority, due_date, user_id) values (?, ?, ?, ?)";


    public Integer insertTask(Task task, String userId){
        Integer added = 0;

        added = template.update(INSERT_TASK_SQL, task.getDescription(), task.getPriority(),task.getDueDate(), userId);

        return added;

    }

}
