package ru.olejik.service.user;

import ru.olejik.entity.User;
import ru.olejik.exeption.ResourceNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserService {
    public void createUser(User user);
    public List<User> getUsers();
    public User getUser(Integer id) throws ResourceNotFoundException;
    public void deleteUser(Integer id);
    public void updateById(String name, Integer age, String email, Integer userId) throws ResourceNotFoundException;
}
