package com.example.demo.dto;

import com.example.demo.util.TaskStatus;

public class TaskSearchDto {
    private String title;
    private DateSearchCriteria endDate;
    private DateSearchCriteria createdDate;

    private TaskStatus taskStatusType;

    public TaskSearchDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateSearchCriteria getEndDate() {
        return endDate;
    }

    public void setEndDate(DateSearchCriteria endDate) {
        this.endDate = endDate;
    }

    public DateSearchCriteria getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateSearchCriteria createdDate) {
        this.createdDate = createdDate;
    }

    public TaskStatus getTaskStatusType() {
        return taskStatusType;
    }

    public void setTaskStatusType(TaskStatus taskStatusType) {
        this.taskStatusType = taskStatusType;
    }
}
