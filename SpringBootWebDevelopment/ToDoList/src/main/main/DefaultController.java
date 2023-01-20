package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class DefaultController {

    private final TaskRepository taskRepository;

    @Value("${someParameter.value}")
    private Integer someParameter;

    public DefaultController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks :: add);

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy :: HH:mm:ss"));
        model.addAttribute("tasks", tasks)
                .addAttribute("tasksCount", tasks.size())
                .addAttribute("someParameter", someParameter)
                .addAttribute("date", date)
                .addAttribute("random", (Math.random() * 10000));

        return "index";
    }

//    @RequestMapping("/")
//    public String index() {
//        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy :: HH:mm:ss"));
//        return "<div><H1>" + date + "</H1><br><H2>" + (Math.random() * 10000) + "</H2></div>";
//    }
}
