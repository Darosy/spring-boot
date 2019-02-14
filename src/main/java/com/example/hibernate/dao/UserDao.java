package com.example.hibernate.dao;

import com.example.hibernate.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<UserModel> getUser() {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<UserModel> query = session.createQuery("from UserModel");
        List<UserModel> result = query.getResultList();
        return result;
    }

    public void addUser(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    public UserModel findById(Long id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        UserModel user = (UserModel) session.get(UserModel.class,id);
        return user;
    }

    public UserModel update(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;
    }

    public void delete(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
