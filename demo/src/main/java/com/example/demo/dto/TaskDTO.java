package com.example.demo.dto;

import com.example.demo.util.TaskStatus;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Date;

public class TaskDTO {

    @NonNull
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDate endDate;
    private String comments;

    public TaskDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
