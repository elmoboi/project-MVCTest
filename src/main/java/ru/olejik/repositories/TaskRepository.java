package ru.olejik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.olejik.entity.Task;
import ru.olejik.enums.TaskStatus;

import java.awt.print.Pageable;
import java.util.List;

@Repository("taskRepository")
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Integer>, PagingAndSortingRepository<Task,Integer> {
    @Modifying
    @Transactional
    @Query("update Task t set t.description = ?1, t.taskStatus = ?2 where t.id = ?3")
    public void updateTaskById(String description, TaskStatus taskStatus, Integer taskId);
}
