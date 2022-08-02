package com.RestManager.Project.Manager;

import com.RestManager.Project.Manager.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/projects-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/projects-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {
    @Autowired
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void projectListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(xpath("//*[@id='project-list']/div").nodeCount(4));
    }

    @Test
    public void filterProjectTest() throws Exception {
        this.mockMvc.perform(get("/main").param("filter", "my-tag"))
                .andDo(print())
                .andExpect(xpath("//*[@id='project-list']/div").nodeCount(2))
                .andExpect(xpath("//*[@id='project-list']/div[@data-id='1']").exists())
                .andExpect(xpath("//*[@id='project-list']/div[@data-id='3']").exists());
    }

    @Test
    public void addProjectToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/main")
                .file("file", "123".getBytes())
                .param("text", "fifth")
                .param("tag", "new one");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("//*[@id='project-list']/div").nodeCount(5))
                .andExpect(xpath("//*[@id='project-list']/div[@data-id='10']").exists())
                .andExpect(xpath("//*[@id='project-list']/div[@data-id='10']/div/span").string("fifth"))
                .andExpect(xpath("//*[@id='project-list']/div[@data-id='10']/div/i").string("#new one"));
    }
}
