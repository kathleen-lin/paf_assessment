package ibf2022.paf.assessment.server.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.Payload.taskPayload;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    TodoService todoSvc;

    @PostMapping("/task")
    public ModelAndView postSave(@RequestBody String payload){

        taskPayload entryInfo = new taskPayload();

        String[] information = payload.split("&");
        // for (int i = 0; i < information.length; i++){
        //     System.out.println(information[i]);
        // }

        List<Task> tasks = new LinkedList<Task>();
        
        for (int i = 1; i<information.length-2; i+=3){
            Task task = new Task();
            task.setDescription(information[i].split("=")[1].replace("+", " "));
            task.setPriority(Integer.parseInt(information[i+1].split("=")[1]));
            task.setDueDate(information[i+2].split("=")[1]);
            tasks.add(task);
        }
        entryInfo.setUsername(information[0].split("=")[1]);
        entryInfo.setTasks(tasks);

        // for (int i = 0; i < tasks.size(); i++){
        //     System.out.println("task" + i+1 + ": " + tasks.get(i).getDescription());
        //     System.out.println("task" + i+1 + ": " + tasks.get(i).getPriority());
        //     System.out.println("task" + i+1 + ": " + tasks.get(i).getDueDate());
        // }
        
        ModelAndView mv = new ModelAndView();

        Boolean upsertOps = todoSvc.upsertTask(entryInfo);

        if (upsertOps){
            mv.addObject("username", entryInfo.getUsername());
            mv.addObject("taskCount", tasks.size());
            mv.setViewName("result");
            mv.setStatus(HttpStatusCode.valueOf(200));

            return mv;

        } else {
            mv.setViewName("error");
            mv.setStatus(HttpStatusCode.valueOf(500));
            return mv;
        }
        


    }

    
}
