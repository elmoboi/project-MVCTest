package ru.olejik.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should be not empty")@Size(max = 30, min = 2, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should be not empty")
    @Size(max = 30, min = 2, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Min(value = 0, message = "Age should be more than 0")
    @Min(value = 0, message = "Age should be more than 0")
    @Column(name = "age")
    private int age;
    @Email(message = "Please, enter valid email")@NotEmpty(message = "Email should be not empty")
    @Email(message = "Please, enter valid email")
    @NotEmpty(message = "Email should be not empty")
    @Column(name = "email")
    private String email;

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

}
