package com.juniortest.taskapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniortest.taskapi.models.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer>{

  List<TaskModel> findByStatus(Boolean status);

}
