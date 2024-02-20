package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskSearchDto;
import com.example.demo.exception.InvalidTaskData;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.QTask;
import com.example.demo.model.Task;
import com.example.demo.respoitory.TaskRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getTaskDetails(int id){
        try {
            return  taskRepository.findById(id);
        }catch (Exception exception){
            throw  new ServiceException(exception.getMessage(),exception);
        }
    }

    public Task createTask(TaskDTO taskDTO){
        try {
            Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getTaskStatus(), LocalDate.now(), taskDTO.getEndDate(), taskDTO.getComments());
            List<Task> response = taskRepository.findByTitle(task.getTitle());
            if(response.isEmpty()) {
                return taskRepository.save(task);
            }else{
                throw new InvalidTaskData("task with same title exists");
            }
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

    public Page<Task> filterTasks(Pageable pageable, TaskSearchDto taskSearchDto) {
        Predicate search = buildPredicate(taskSearchDto);
        if(search==null){
            return taskRepository.findAll(pageable);
        }
        return taskRepository.findAll(buildPredicate(taskSearchDto),pageable);
    }

    public Predicate buildPredicate(TaskSearchDto taskSearchDto) {
            QTask qTask = QTask.task;
        BooleanBuilder builder = new BooleanBuilder();


            if (taskSearchDto.getCreatedDate() != null) {
                LocalDate createdDate = taskSearchDto.getCreatedDate().getValue();
                switch (taskSearchDto.getCreatedDate().getOperationType()) {
                    case EQUAL -> builder.and(qTask.createdOn.eq(createdDate));
                    case LESS_THAN -> builder.and(qTask.createdOn.before(createdDate));
                    case GREATER_THAN -> builder.and(qTask.createdOn.after(createdDate));
                    default -> {
                    }
                }
            }

            if (taskSearchDto.getEndDate() != null) {
                LocalDate endDate = taskSearchDto.getEndDate().getValue();
                switch (taskSearchDto.getEndDate().getOperationType()) {
                    case EQUAL -> builder.and(qTask.endDate.eq(endDate));
                    case LESS_THAN -> builder.and(qTask.endDate.before(endDate));
                    case GREATER_THAN -> builder.and(qTask.endDate.after(endDate));
                    default -> {
                    }
                }
            }

            if (taskSearchDto.getTitle() != null) {
                builder.and(qTask.title.likeIgnoreCase("%" + taskSearchDto.getTitle() + "%"));
            }

            if (taskSearchDto.getTaskStatusType() != null) {
                builder.and(qTask.taskStatus.eq(taskSearchDto.getTaskStatusType()));
            }

            if(!builder.hasValue()){
               return null;
            }
        return builder.getValue();
        }
    }

