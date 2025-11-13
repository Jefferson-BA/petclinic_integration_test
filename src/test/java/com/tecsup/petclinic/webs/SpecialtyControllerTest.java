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
     * CREAR especialidad (según tu BD REAL)
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
    /**
     * Test 3: BUSCAR especialidad por ID
     */
    @Test
    public void testFindSpecialtyById() throws Exception {

        SpecialtyDTO dto = new SpecialtyDTO();
        dto.setName("Neurology");
        dto.setOffice("B203");
        dto.setH_open(9);
        dto.setH_close(18);

        // Crear primero
        String response = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse().getContentAsString();

        SpecialtyDTO created = mapper.readValue(response, SpecialtyDTO.class);

        // Buscar por ID
        mockMvc.perform(get("/specialties/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Neurology")))
                .andExpect(jsonPath("$.office", is("B203")))
                .andExpect(jsonPath("$.h_open", is(9)))
                .andExpect(jsonPath("$.h_close", is(18)));
    }

    /**
     * Test 4: ACTUALIZAR especialidad
     */
    @Test
    public void testUpdateSpecialty() throws Exception {

        SpecialtyDTO dto = new SpecialtyDTO();
        dto.setName("Dentistry");
        dto.setOffice("A101");
        dto.setH_open(7);
        dto.setH_close(16);

        // Crear
        String response = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();

        SpecialtyDTO specialty = mapper.readValue(response, SpecialtyDTO.class);

        // Cambiar datos
        specialty.setName("Advanced Dentistry");
        specialty.setOffice("A202");
        specialty.setH_open(8);
        specialty.setH_close(17);

        // Actualizar
        mockMvc.perform(put("/specialties/" + specialty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(specialty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Advanced Dentistry")))
                .andExpect(jsonPath("$.office", is("A202")))
                .andExpect(jsonPath("$.h_open", is(8)))
                .andExpect(jsonPath("$.h_close", is(17)));
    }
}

