package com.example.hibernate.repository;

import com.example.hibernate.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepositoty extends JpaRepository<UserProfileModel, Long> {
}
