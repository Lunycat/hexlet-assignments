package exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
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
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;



    @AfterEach
    public void afterAll() {
        taskRepository.deleteAll();
    }

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


    @Test
    public void testShow() throws Exception {
        String title = faker.lorem().word();
        String description = faker.lorem().sentence(10);

        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> description)
                .create();
        taskRepository.save(task);

        MvcResult result = mockMvc
                .perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Map<String, String> json = om.readValue(body, new TypeReference<>() {});

        assertThat(json.get("title")).isEqualTo(title);
        assertThat(json.get("description")).isEqualTo(description);
    }

    @Test
    public void postTest() throws Exception {
        String title = faker.lorem().word();
        String description = faker.lorem().sentence(10);

        Task task = Instancio.of(Task.class)
                .supply(Select.field(Task::getId), () -> 999999)
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> description)
                .create();

        mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated());
    }

    @Test
    public void putTest() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(10))
                .create();

        taskRepository.save(task);

        String title = faker.lorem().word();
        String description = faker.lorem().sentence(10);

        Task data = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> description)
                .create();

        MvcResult result = mockMvc.perform(put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data)))
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Map<String, String> json = om.readValue(body, new TypeReference<>() {});

        assertThat(json.get("title")).isEqualTo(title);
        assertThat(json.get("description")).isEqualTo(description);
    }

    @Test
    public void destroyTest() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(10))
                .create();

        taskRepository.save(task);

        mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/tasks/" + task.getId()));

        mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isNotFound());
    }
}
