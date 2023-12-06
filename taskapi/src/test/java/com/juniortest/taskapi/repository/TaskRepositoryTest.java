package com.juniortest.taskapi.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.juniortest.taskapi.models.TaskModel;
import com.juniortest.taskapi.repositories.TaskRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepo;

  @Test
  public void TaskRespository_SaveTask_ReturnSavedTask() {

    TaskModel task = TaskModel.builder().description("Clean").status(false).build();

    TaskModel savedTask = taskRepo.save(task);

    Assertions.assertThat(savedTask).isNotNull();
    Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
  }

  @Test
  public void TaskRespository_getAll_ReturnMoreThanOneTask() {

    TaskModel task = TaskModel.builder().description("Homework").status(true).build();
    TaskModel task2 = TaskModel.builder().description("Clean").status(false).build();

    TaskModel savedTask = taskRepo.save(task);
    TaskModel savedTask2 = taskRepo.save(task2);

    List<TaskModel> taskList = taskRepo.findAll();

    Assertions.assertThat(taskList).isNotNull();
    Assertions.assertThat(taskList.size()).isEqualTo(2);
  }

  @Test
  public void TaskRespository_getById_ReturnTaskById() {

    TaskModel task = TaskModel.builder().description("Homework").status(true).build();
    TaskModel task2 = TaskModel.builder().description("Clean").status(false).build();

    TaskModel savedTask = taskRepo.save(task);
    TaskModel savedTask2 = taskRepo.save(task2);

    TaskModel taskById = taskRepo.findById(savedTask2.getId()).get();

    Assertions.assertThat(taskById).isNotNull();
    Assertions.assertThat(taskById.getDescription()).isEqualTo("Clean");
  }

  @Test
  public void TaskRespository_getByStatus_ReturnSameStatusTasks() {

    TaskModel task = TaskModel.builder().description("Do Homework").status(true).build();
    TaskModel task2 = TaskModel.builder().description("Clean").status(false).build();
    TaskModel task3 = TaskModel.builder().description("Feed the dog").status(true).build();
    TaskModel task4 = TaskModel.builder().description("Watch tv").status(false).build();
    TaskModel task5 = TaskModel.builder().description("Cook").status(true).build();

    TaskModel savedTask = taskRepo.save(task);
    TaskModel savedTask2 = taskRepo.save(task2);
    TaskModel savedTask3 = taskRepo.save(task3);
    TaskModel savedTask4 = taskRepo.save(task4);
    TaskModel savedTask5 = taskRepo.save(task5);

    List<TaskModel> taskList = taskRepo.findByStatus(true);
    List<TaskModel> taskList2 = taskRepo.findByStatus(false);

    Assertions.assertThat(taskList).isNotNull();
    Assertions.assertThat(taskList2).isNotNull();
    Assertions.assertThat(taskList.size()).isEqualTo(3);
    Assertions.assertThat(taskList2.size()).isEqualTo(2);
  }

  @Test
  public void TaskRespository_UpdateTask_ReturnUpdatedTask() {

    TaskModel task = TaskModel.builder().description("Clean").status(false).build();

    TaskModel savedTask = taskRepo.save(task);

    TaskModel taskById = taskRepo.findById(savedTask.getId()).get();
    taskById.setDescription("Go shopping");
    taskById.setStatus(true);

    TaskModel updatedTask = taskRepo.save(taskById);

    Assertions.assertThat(updatedTask).isNotNull();
    Assertions.assertThat(updatedTask.getDescription()).isEqualTo("Go shopping");
    Assertions.assertThat(updatedTask.getStatus()).isEqualTo(true);
  }

  @Test
  public void TaskRespository_DeleteTask_MakeSureTaskDeleted() {

    TaskModel task = TaskModel.builder().description("Do Homework").status(true).build();
    TaskModel task2 = TaskModel.builder().description("Clean").status(false).build();
    TaskModel task3 = TaskModel.builder().description("Feed the dog").status(true).build();

    TaskModel savedTask = taskRepo.save(task);
    TaskModel savedTask2 = taskRepo.save(task2);
    TaskModel savedTask3 = taskRepo.save(task3);

    List<TaskModel> oldTaskList = taskRepo.findAll();
    taskRepo.deleteById(savedTask2.getId());
    List<TaskModel> taskList = taskRepo.findAll();
    Optional<TaskModel> taskById = taskRepo.findById(savedTask2.getId());

    Assertions.assertThat(taskById).isEmpty();
    Assertions.assertThat(taskList).isNotNull();
    Assertions.assertThat(oldTaskList.size()).isEqualTo(3);
    Assertions.assertThat(taskList.size()).isEqualTo(2);
  }
}
