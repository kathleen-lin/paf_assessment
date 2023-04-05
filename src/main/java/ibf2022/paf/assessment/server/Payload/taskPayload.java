package ibf2022.paf.assessment.server.Payload;

import java.util.List;

import ibf2022.paf.assessment.server.models.Task;

public class taskPayload {

    private String username;

    private List<Task> tasks;

    public taskPayload() {
    }

    public taskPayload(String username, List<Task> tasks) {
        this.username = username;
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }    
    
}
