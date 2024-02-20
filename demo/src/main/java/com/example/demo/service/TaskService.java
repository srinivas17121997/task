package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.InvalidTaskData;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.respoitory.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

/*    public  void abc(){
        PageRequest q = PageRequest.of(1, 10, Sort.Direction.ASC);
        taskRepository.findAll("abc",q);
    }*/


    public Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskDetails(int id){
        try {
            return  taskRepository.findById(id);
        }catch (Exception exception){
            throw  new ServiceException(exception.getMessage(),exception);
        }
    }

    public Task createTask(TaskDTO taskDTO){
        try {
                Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.isCompleted(), new Date(), taskDTO.getEndDate(), taskDTO.getComments());
                return taskRepository.save(task);
        }catch (InvalidTaskData invalidTaskData){
            throw  invalidTaskData;
        }
        catch (Exception exception){
            throw new ServiceException("Failed to Save the Task",exception);
        }
    }

    public Task updateTask(TaskDTO taskDTO, int taskId){
        try {
            Optional<Task> oldTask = taskRepository.findById(taskId);
            if(oldTask.isPresent()) {
                Task task=updateTaskDetails(taskDTO,oldTask.get());
                return taskRepository.save(task);
            }else {
                throw  new TaskNotFoundException("Not Found with id"+taskId);
            }
        }catch (InvalidTaskData invalidTaskData){
            throw  invalidTaskData;
        }
        catch (Exception exception){
            throw new ServiceException("Failed to Save the Task",exception);
        }
    }

    private Task updateTaskDetails(TaskDTO taskDTO, Task task){

        if(taskDTO.getEndDate()!=null){
            task.setEndDate(taskDTO.getEndDate());
        }
        if(taskDTO.getTitle()!=null){
            task.setTitle(taskDTO.getTitle());
        }
        if(taskDTO.getComments()!=null){
            task.setComments(taskDTO.getComments());
        }
        return task;
    }
    private void validaTaskData(TaskDTO taskDTO){
        if (taskDTO.getTitle()==null){
            throw  new InvalidTaskData("Pls send the title data for the task");
        }
        if(taskDTO.getEndDate()==null){
            throw  new InvalidTaskData("pls set the end of the task");
        }

    }

    public void deleteTask(Integer id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            try {
                taskRepository.deleteById(id);
            } catch (Exception e) {
                throw new ServiceException("Error deleting task with id " + id, e);
            }
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }

    public Page<Task> filterTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
