package ru.olejik.service.user.impl;

import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.olejik.entity.User;
import ru.olejik.exeption.ResourceNotFoundException;
import ru.olejik.repositories.UserRepository;
import ru.olejik.service.user.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUser(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
        userRepository.flush();
    }

    @Override
    @Transactional
    public void updateById(String name, Integer age, String email, Integer userId) throws ResourceNotFoundException {
        userRepository.updateById(name,age,email,userId);
    }
}
