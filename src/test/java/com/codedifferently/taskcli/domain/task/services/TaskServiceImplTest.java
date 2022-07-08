package com.codedifferently.taskcli.domain.task.services;

import com.codedifferently.taskcli.domain.task.exception.TaskCreationException;
import com.codedifferently.taskcli.domain.task.exception.TaskNotFoundException;
import com.codedifferently.taskcli.domain.task.models.Task;
import com.codedifferently.taskcli.domain.task.repos.TaskRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
@ExtendWith(SpringExtension.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepo taskRepo;

    @Test
    @DisplayName("Create task -- success")
    public void createTaskTest01() throws TaskCreationException {
        // Given
        Task firstTask = new Task("First Task");
        firstTask.setId(1l);
        String expected = "1 First Task false";
        // When
        BDDMockito.doReturn(firstTask).when(taskRepo).save(ArgumentMatchers.any());
        BDDMockito.doReturn(Optional.empty()).when(taskRepo).findByTitle("First Task");
        Task actualTask = new Task("First Task");
        actualTask = taskService.create(actualTask);
        // Then
        String actual = actualTask.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create Task -- fail")
    public void createTaskTest02(){
        Task firstTask = new Task("First Task");
        firstTask.setId(1l);
        BDDMockito.doReturn(Optional.of(firstTask)).when(taskRepo).findByTitle(ArgumentMatchers.any());
        Assertions.assertThrows(TaskCreationException.class, ()->{
           taskService.create(firstTask);
        });
    }

    @Test
    @DisplayName("Update Task -- success")
    public void updateTaskTest01() throws TaskNotFoundException {
        Task beforeSave = new Task("First Task");
        beforeSave.setId(1l);
        Task afterSave = new Task("First Task");
        afterSave.setId(1l);
        afterSave.setComplete(true);
        BDDMockito.doReturn(afterSave).when(taskRepo).save(beforeSave);
        BDDMockito.doReturn(Optional.of(beforeSave)).when(taskRepo).findById(1l);
        Task actual = taskService.updateToComplete(1l);
        Assertions.assertTrue(actual.getComplete());
    }

    @Test
    @DisplayName("Update Task -- success")
    public void updateTaskTest02() throws TaskNotFoundException {
        Task beforeSave = new Task("First Task");
        beforeSave.setId(1l);
        Task afterSave = new Task("First Task Updated");
        afterSave.setId(1l);
        BDDMockito.doReturn(afterSave).when(taskRepo).save(beforeSave);
        BDDMockito.doReturn(Optional.of(beforeSave)).when(taskRepo).findById(1l);
        Task actual = taskService.updateTitle(1l, "First Task Updated");
        Assertions.assertEquals("First Task Updated", actual.getTitle());
    }
}
