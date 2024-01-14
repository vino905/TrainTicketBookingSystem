package com.train.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.ticket.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
