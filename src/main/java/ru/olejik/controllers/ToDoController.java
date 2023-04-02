package ru.olejik.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.olejik.entity.Task;
import ru.olejik.entity.User;
import ru.olejik.enums.TaskStatus;
import ru.olejik.exeption.ResourceNotFoundException;
import ru.olejik.service.task.TaskService;
import ru.olejik.service.user.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@RequestMapping("/todo")
@Controller
public class ToDoController {
    public static final int PAGE = 0;
    @Autowired
    private TaskService taskService;

    Logger logger = Logger.getAnonymousLogger();

    @GetMapping()
    public String index(Model model, @RequestParam(value ="page", required = false) int page) {
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount()/5);
        model.addAttribute("task", taskService.getTasks(page));
        if(totalPages > PAGE) {
            List<Integer> pageList = IntStream.rangeClosed(PAGE,totalPages-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageList",pageList);
        }
        return "todo/index";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) throws ResourceNotFoundException {
        model.addAttribute("task", taskService.getTask(id));
        model.addAttribute("description", taskService.getTask(id).getDescription());
        model.addAttribute("taskStatus", taskService.getTask(id).getTaskStatus());
        return "todo/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid Task task, @PathVariable("id") int id, Model model) throws ResourceNotFoundException {
        taskService.updateTaskById(task.getDescription(),task.getTaskStatus(),id);
        model.addAttribute("task", taskService.getTasks(PAGE));
        return index(model, PAGE);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        taskService.deleteTask(id);
        return index(model,PAGE);
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws ResourceNotFoundException {
        model.addAttribute("task", taskService.getTask(id));
        return "todo/show";
    }
    @PostMapping()
    public String create(@ModelAttribute("task") @Valid Task task, Model model) {
        taskService.createTask(task);
        model.addAttribute("task", taskService.getTasks(PAGE));
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount()/5);
        int rightPage = totalPages-1;
        return index(model,rightPage);
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("task", new Task());
        return "todo/new";
    }
}
