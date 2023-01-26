package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PatchMapping(value = "/tasks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> edit(@RequestBody Map<String, Object> updates, @PathVariable int id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            for (String update : updates.keySet()) {
                updateTask(update, updates.get(update), task);
            }
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> delete(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/tasks/")
    public ResponseEntity<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return new ResponseEntity(tasks, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/tasks/")
    public ResponseEntity<Task> add(Task task) {
        Task newTask = taskRepository.save(task);
        return new ResponseEntity(newTask.getId(), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.isPresent() ?
                new ResponseEntity(optionalTask.get(), HttpStatus.OK) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private void updateTask(String key, Object object, Task task) {
        switch (key) {
            case "title" -> task.setTitle((String) object);
            case "description" -> task.setDescription((String) object);
            case "isDone" -> task.setDone((boolean) object);
        }
    }
}
