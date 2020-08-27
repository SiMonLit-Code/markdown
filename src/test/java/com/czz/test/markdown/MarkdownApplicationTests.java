package com.czz.test.markdown;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class MarkdownApplicationTests {
    @Resource
    MockMvc mockMvc;



    @Test
    void contextLoads() {

    }
    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
//        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
        String outputDir = "target/swagger";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/v2/api-docs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String swaggerJson = response.getContentAsString();
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)) {
            writer.write(swaggerJson);
        }
    }
}
