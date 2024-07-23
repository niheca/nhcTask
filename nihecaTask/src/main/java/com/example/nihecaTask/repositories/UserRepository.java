package com.example.nihecaTask.repositories;

import com.example.nihecaTask.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
