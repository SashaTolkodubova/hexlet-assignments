package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    private Task task;
    @BeforeEach
        public void createTask() {
        task = generateTask();
    }

    @Test
    public void testShow() throws Exception {
        taskRepository.save(task);
        var request = get("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON);
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var responsedTask = om.readValue(result
                        .getResponse()
                        .getContentAsString()
                , Task.class);
        assertThat(task.getTitle()).isEqualTo(responsedTask.getTitle());
        assertThat(task.getId()).isEqualTo(responsedTask.getId());
        assertThat(task.getDescription()).isEqualTo(responsedTask.getDescription());

    }

    @Test
    public void testCreate() throws Exception {
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var createdTask = taskRepository.findByTitle(task.getTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + task.getId() + " not found"));
        assertThat(task.getDescription()).isEqualTo(createdTask.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        taskRepository.save(task);
        task = taskRepository.findByTitle(task.getTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Task with title " + task.getTitle() + " not found"));
        task.setTitle(faker.lorem().word());

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        Task updatedTask = taskRepository.findById(task.getId()).orElseThrow(() -> new ResourceNotFoundException("Task with title " + task.getTitle() + " not found"));;
        assertThat(task.getTitle()).isEqualTo(updatedTask.getTitle());
    }

    @Test
    public void testDelete() throws Exception {
        taskRepository.save(task);
        task = taskRepository.findByTitle(task.getTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Task with title " + task.getTitle() + " not found"));

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        Optional<Task> deletedTask = taskRepository.findById(task.getId());
        assertThat(deletedTask).isEqualTo(Optional.empty());
    }
    // END
}
