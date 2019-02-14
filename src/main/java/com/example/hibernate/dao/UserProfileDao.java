package com.example.hibernate.dao;

import com.example.hibernate.model.UserProfileModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserProfileDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<UserProfileModel> getUser() {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<UserProfileModel> query = session.createQuery("from UserProfileModel");
        List<UserProfileModel> result = query.getResultList();
        return result;
    }

    public void addUser(UserProfileModel userProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.save(userProfile);
    }

    public UserProfileModel findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserProfileModel userProfile = (UserProfileModel) session.get(UserProfileModel.class,id);
        return userProfile;
    }

    public UserProfileModel update(UserProfileModel userProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userProfile);
        return userProfile;
    }

    public void delete(UserProfileModel userProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(userProfile);
    }
}
