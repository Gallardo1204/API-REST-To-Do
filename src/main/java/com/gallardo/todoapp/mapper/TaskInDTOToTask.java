package com.gallardo.todoapp.mapper;

import com.gallardo.todoapp.persistence.entity.Task;
import com.gallardo.todoapp.persistence.entity.TaskStatus;
import com.gallardo.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {

    @Override
    public Task map(TaskInDTO in) {

        Task task = new Task();

        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setDateFinishEstimated(in.getDateFinishEstimated());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);

        return task;
    }
}
