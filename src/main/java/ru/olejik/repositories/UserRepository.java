package ru.olejik.repositories;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.olejik.entity.User;



@Repository("userRepository")
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("update User u set u.name = ?1, u.age = ?2, u.email = ?3 where u.id = ?4")
    public void updateById(String name, Integer age, String email, Integer userId);
}
