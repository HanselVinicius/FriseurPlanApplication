package com.hansel.FriseurPlan.infra.adapter.input.hairdresser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansel.FriseurPlan.BaseIntegrationTest;
import com.hansel.FriseurPlan.IntegrationTest;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.adapter.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
class HairdresserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HairdresserEntityRepository  hairdresserRepository;

    private Email email;
    private PhoneNumber phoneNumber;
    private HairdresserEntity hairdresserEntity;
    private Address address;
    private HairdresserDto hairdresserDto;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        AddressVo addressVo = AddressVo.fromAddressDomain(address);
        this.hairdresserEntity = new HairdresserEntity(null, "John Doe", EmailVo.fromEmailDomain(email), phoneNumber, addressVo, LocalDateTime.MAX, null, null);
        this.hairdresserDto = new HairdresserDto(this.hairdresserEntity.getName(),this.phoneNumber,addressVo.toAddressDomain());
    }

    @AfterEach
    void tearDown() {
        this.hairdresserRepository.deleteAll();
    }

    @Test
    void shouldCreateHairdresser() throws Exception {
        mockMvc.perform(post("/v1/hairdressers").
                        with(SecurityMockMvcRequestPostProcessors.jwt()
                                .jwt(jwt -> {
                                    jwt.claim("email", "hanelvinicius@gmail.com");
                                    jwt.claim("email_verified", true);
                                }))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(hairdresserDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(header().string("Location", containsString("http://localhost/v1/hairdressers")));
    }

    @Test
    void shouldGetHairdresserByEmail() throws Exception {
        this.hairdresserRepository.save(hairdresserEntity);
        mockMvc.perform(get("/v1/hairdressers/email").
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
        HairdresserEntity save = this.hairdresserRepository.save(hairdresserEntity);
        mockMvc.perform(get("/v1/hairdressers/"+save.getId()).
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
    public void shouldGetAppointments() throws Exception {
        this.hairdresserRepository.save(hairdresserEntity);
        MvcResult mvcResult = this.mockMvc.perform(
                        get("/v1/hairdressers")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("id"));
    }

}