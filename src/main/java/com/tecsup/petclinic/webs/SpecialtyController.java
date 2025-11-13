package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SpecialtyController {

    private final SpecialtyService service;
    private final SpecialtyMapper mapper;

    public SpecialtyController(SpecialtyService service, SpecialtyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyDTO>> findAll() {
        List<Specialty> list = service.findAll();
        return ResponseEntity.ok(mapper.mapToDtoList(list));
    }

    @PostMapping("/specialties")
    public ResponseEntity<SpecialtyDTO> create(@RequestBody SpecialtyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/specialties/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/specialties/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SpecialtyDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/specialties/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Deleted ID: " + id);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
