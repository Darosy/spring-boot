package com.example.hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/user-test")
public class UserResource {

    @GetMapping
    public List<User> getUsers() {
        return Arrays.asList(
                new User("Sam", 2000L),
                new User("Imam", 3000L)
        );
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") final String userName) {
        return new User(userName, 1000L);
    }

    private class User {
        private String name;
        private Long salary;

        public User(String name, Long salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }
    }
}
