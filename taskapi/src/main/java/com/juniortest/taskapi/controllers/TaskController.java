package com.juniortest.taskapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.juniortest.taskapi.models.TaskModel;
import com.juniortest.taskapi.repositories.TaskRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class TaskController {
  
  @Autowired
  private TaskRepository taskRepo;

  @GetMapping("/tasks")
  public ResponseEntity<List<TaskModel>> getAllTaks() {
    try {
      List<TaskModel> taskList = new ArrayList<>();
      taskRepo.findAll().forEach(taskList::add);

      if (taskList.isEmpty()) {
        return new ResponseEntity<>(taskList, HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(taskList, HttpStatus.OK);

    } catch(Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/task/{id}")
  public ResponseEntity<TaskModel> getTaskById(@PathVariable Integer id) {
    try {
      Optional<TaskModel> taskData = taskRepo.findById(id);

      if (taskData.isPresent()) {
        return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch(Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tasks/{status}")
  public ResponseEntity<List<TaskModel>> getTaskByStatus(@PathVariable Boolean status) {
    try {
      List<TaskModel> taskList = new ArrayList<>();
      taskRepo.findByStatus(status).forEach(taskList::add);

      if (taskList.isEmpty()) {
        return new ResponseEntity<>(taskList, HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(taskList, HttpStatus.OK);

    } catch(Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/task")
  public ResponseEntity<TaskModel> addTask(@RequestBody TaskModel task) {
    try {
      TaskModel reqTask = taskRepo.save(task);

      return new ResponseEntity<>(reqTask, HttpStatus.OK);

    } catch(Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/task/{id}")
  public ResponseEntity<TaskModel> updateTask(@PathVariable Integer id, @RequestBody TaskModel newTask) {
    try {
      Optional<TaskModel> oldTaskData = taskRepo.findById(id);
      
      if (oldTaskData.isPresent()) {
        TaskModel updatedTaskData = oldTaskData.get();

        updatedTaskData.setDescription(newTask.getDescription());
        updatedTaskData.setStatus(newTask.getStatus());

        TaskModel task = taskRepo.save(updatedTaskData);
        return new ResponseEntity<>(task, HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/task/{id}")
  public ResponseEntity<HttpStatus> deleteTask(@PathVariable Integer id) {
    try {
      Optional<TaskModel> taskData = taskRepo.findById(id);
      
      if (taskData.isPresent()) {
        taskRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
