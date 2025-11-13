package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    /**
     * LISTAR especialidades
     */
    @Test
    public void testFindAllSpecialties() throws Exception {
        mockMvc.perform(get("/specialties")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    /**
     * CREAR especialidad
     */
    @Test
    public void testCreateSpecialty() throws Exception {

        SpecialtyDTO dto = new SpecialtyDTO();
        dto.setName("Cardiology");
        dto.setOffice("203A");
        dto.setH_open(8);
        dto.setH_close(17);

        mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Cardiology")))
                .andExpect(jsonPath("$.office", is("203A")))
                .andExpect(jsonPath("$.h_open", is(8)))
                .andExpect(jsonPath("$.h_close", is(17)));
    }
}
