package com.czz.test.markdown;

import com.czz.test.markdown.controller.MarkDownTest;
import com.czz.test.markdown.entity.ParamModel;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class MarkdownApplicationTests {
    MockMvc mockMvc;



    @Test
    void contextLoads() {
        Class<MarkDownTest> markDownTestClass = MarkDownTest.class;
        Method[] methods = markDownTestClass.getMethods();

        for (Method method: methods) {
            //获取该方法所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter: parameters) {
                ParamModel paramModel=new ParamModel();
                //获取参数上的注解
                Annotation[] annotations = parameter.getAnnotations();
                //判断参数是否为对象
                if (annotations[0].annotationType()== RequestBody.class){
                    paramModel.setName(parameter.getName());
                    obj(parameter);
                }else if (annotations[0].annotationType()== PathVariable.class){
                    paramModel.setName(parameter.getName());
                }
            }
        }
    }


    private void obj(Parameter parameter){
        Class<?> type = parameter.getType();

        Field[] fields = type.getDeclaredFields();
        for (Field field: fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation ann : annotations) {
                ApiOperation apiOperation = ann.annotationType().getAnnotation(ApiOperation.class);
                ParamModel paramModel = new ParamModel();
                paramModel.setName(apiOperation.value());

            }
        }
    }


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
