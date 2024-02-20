package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskSearchDto;
import com.example.demo.exception.InvalidTaskData;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class TaskController {

    @Autowired
    TaskService taskService;


    @PostMapping("/tasks/search")
    public Page<Task> filterTasks(@RequestBody TaskSearchDto taskSearchDto,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "createdOn") String sortBy,
                                  @RequestParam(defaultValue = "desc") String sortOrder) {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return taskService.filterTasks( pageable,taskSearchDto);
    }

    @PostMapping("/tasks/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO){
        Task task;
        try {
              task=  taskService.createTask(taskDTO);
              log.info("new task created");
            return ResponseEntity.ok(task);
        }catch (InvalidTaskData invalidTaskData){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidTaskData.getLocalizedMessage());
        }
        catch (ServiceException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO,@PathVariable int id){

        Task task;
        try {
            task=  taskService.updateTask(taskDTO,id);
            return ResponseEntity.ok(task);
        }catch (InvalidTaskData invalidTaskData){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidTaskData.getLocalizedMessage());
        }
        catch (ServiceException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (ServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
