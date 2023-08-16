package com.springbootunleashed.library.integrationtest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.springbootunleashed.library.LibraryApplication;
import com.springbootunleashed.library.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
public class ApiControllerIntegrationTest {
  private WireMockServer wireMockServer;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    wireMockServer = new WireMockServer(8081); // Use a port different from your main application
    wireMockServer.start();
    WireMock.configureFor("localhost", 8081); // Configure WireMock to use the correct host and port
  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  @Test
  public void testGetBooks() throws Exception {
    // Stub WireMock to simulate an external API response
    stubFor(get(urlEqualTo("/api/books"))
        .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody("{\n" +
                "  \"content\": [\n" +
                "    {\"title\": \"The Great Gatsby\", \"category\": \"Fiction\", \"isbn\": \"978-0-7432-7356-5\"}\n" +
                "  ],\n" +
                "  \"totalElements\": 1,\n" +
                "  \"totalPages\": 1,\n" +
                "  \"number\": 0,\n" +
                "  \"size\": 10\n" +
                "}")));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("The Great Gatsby"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].category").value("Fiction"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].isbn").value("978-0-7432-7356-5"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(0))
        .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(10));
  }

  @Test
  public void testCreateBook() throws Exception {
    // Stub WireMock to simulate an external API response
    stubFor(post(urlEqualTo("/api/books"))
        .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody("{\"id\": 1, \"title\": \"New Title\", \"category\": \"Fiction\", \"isbn\": \"1234567890\"}")));

    Book newBook = new Book("New Title", "Fiction", "1234567890");

    mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newBook)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Title"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Fiction"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("1234567890"));
  }

}


