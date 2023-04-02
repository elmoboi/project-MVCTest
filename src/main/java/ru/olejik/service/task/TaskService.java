package ru.olejik.service.task;

import org.springframework.data.domain.Page;
import ru.olejik.entity.Task;
import ru.olejik.enums.TaskStatus;
import ru.olejik.exeption.ResourceNotFoundException;

import java.awt.print.Pageable;
import java.util.List;

public interface TaskService {
    public void createTask(Task task);
    public List<Task> getTasks(Integer page);
    public Task getTask(Integer id) throws ResourceNotFoundException;
    public void deleteTask(Integer id);
    public Integer getAllCount();
    public void updateTaskById(String description, TaskStatus taskStatus, Integer taskId) throws ResourceNotFoundException;
}
