package com.example.hibernate.service;

import com.example.hibernate.dao.UserDao;
import com.example.hibernate.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<UserModel> getUser() {
        return userDao.getUser();
    }

    public UserModel findById(Long id) {
        return userDao.findById(id);
    }

    public void createUser(UserModel user) {
        user.setId(System.currentTimeMillis());
        userDao.addUser(user);
    }

    public void deleteUserById(UserModel userModel) {
        userDao.delete(userModel);
    }

    public UserModel update(UserModel userModel) {
        return userDao.update(userModel);
    }
}
