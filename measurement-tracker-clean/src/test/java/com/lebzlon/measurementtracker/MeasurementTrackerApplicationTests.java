package com.lebzlon.measurementtracker;

import com.fasterxml.jackson.databind.ObjectMapper; import com.lebzlon.measurementtracker.dto.Dtos.*; import com.lebzlon.measurementtracker.entity.MeterType; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class MeasurementTrackerApplicationTests {
 @Autowired MockMvc mvc; @Autowired ObjectMapper om;
 @Test void validationErrorsHandled() throws Exception { mvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.fieldErrors").exists()); }
 @Test void registerLoginFlow() throws Exception { var reg=new RegisterRequest("user1","password"); mvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reg))).andExpect(status().isOk()).andExpect(jsonPath("$.accessToken").exists()); }
}
