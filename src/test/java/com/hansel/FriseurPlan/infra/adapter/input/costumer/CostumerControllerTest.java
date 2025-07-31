package com.hansel.FriseurPlan.infra.adapter.input.costumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansel.FriseurPlan.BaseIntegrationTest;
import com.hansel.FriseurPlan.IntegrationTest;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerDto;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
class CostumerControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CostumerEntityRepository costumerEntityRepository;

    private Email email;
    private PhoneNumber phoneNumber;
    private CostumerEntity costumerEntity;
    private CostumerDto costumerDto;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.costumerEntity = new CostumerEntity(null, "name", phoneNumber, EmailVo.fromEmailDomain(email), LocalDateTime.MAX, null, null);
        this.costumerDto = new CostumerDto(costumerEntity.getName(), phoneNumber.getNumber());
    }

    @AfterEach
    void tearDown() {
        this.costumerEntityRepository.deleteAll();
    }

    @Test
    void shouldCreateCostumer() throws Exception {
        mockMvc.perform(post("/v1/costumers").
                        with(SecurityMockMvcRequestPostProcessors.jwt()
                                .jwt(jwt -> {
                                    jwt.claim("email", "hanelvinicius@gmail.com");
                                    jwt.claim("email_verified", true);
                                }))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(costumerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(header().string("Location", containsString("http://localhost/v1/costumers")));
    }


    @Test
    void shouldGetCostumerByEmail() throws Exception {
        this.costumerEntityRepository.save(costumerEntity);
        mockMvc.perform(get("/v1/costumers").
                        with(SecurityMockMvcRequestPostProcessors.jwt()
                                .jwt(jwt -> {
                                    jwt.claim("email", "hanelvinicius@gmail.com");
                                    jwt.claim("email_verified", true);
                                }))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }


    @Test
    void shouldGetCostumerById() throws Exception {
        CostumerEntity save = this.costumerEntityRepository.save(costumerEntity);
        mockMvc.perform(get("/v1/costumers/"+save.getId()).
                        with(SecurityMockMvcRequestPostProcessors.jwt()
                                .jwt(jwt -> {
                                    jwt.claim("email", "hanelvinicius@gmail.com");
                                    jwt.claim("email_verified", true);
                                }))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

}