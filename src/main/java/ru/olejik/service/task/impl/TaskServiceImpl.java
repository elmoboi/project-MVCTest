package ru.olejik.service.task.impl;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.olejik.entity.Task;
import ru.olejik.enums.TaskStatus;
import ru.olejik.exeption.ResourceNotFoundException;
import ru.olejik.repositories.TaskRepository;
import ru.olejik.service.task.TaskService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = Logger.getAnonymousLogger();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks(Integer page) {
        Page<Task> taskPage = taskRepository.findAll(PageRequest.of(page,5));
        List<Task> taskList = taskPage.getContent();
        return taskList;
    }

    @Override
    @Transactional
    public Task getTask(Integer id) throws ResourceNotFoundException {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
        taskRepository.flush();
    }

    @Override
    public Integer getAllCount() {
        int count = taskRepository.findAll().size();
        return count;
    }

    @Override
    @Transactional
    public void updateTaskById(String description, TaskStatus taskStatus, Integer taskId) throws ResourceNotFoundException {
        taskRepository.updateTaskById(description, taskStatus, taskId);
    }
}
