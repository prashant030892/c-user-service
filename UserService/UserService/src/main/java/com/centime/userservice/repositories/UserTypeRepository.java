package com.centime.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centime.userservice.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
