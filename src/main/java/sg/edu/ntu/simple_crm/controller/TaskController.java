
package sg.edu.ntu.simple_crm.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sg.edu.ntu.simple_crm.model.Task;
import sg.edu.ntu.simple_crm.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // GET all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // GET a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(
            @PathVariable Long id) {

        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create a task
    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody Task task) {

        task.setId(null);
        Task savedTask = taskRepository.save(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    // PUT update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task updatedTask) {

        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(
                            updatedTask.getDescription());
                    task.setCompleted(
                            updatedTask.isCompleted());

                    return ResponseEntity.ok(
                            taskRepository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id) {

        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
