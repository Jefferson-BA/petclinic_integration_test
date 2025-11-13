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

    private SpecialtyService service;
    private SpecialtyMapper mapper;

    public SpecialtyController(SpecialtyService service, SpecialtyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyDTO>> findAll() {

        List<Specialty> list = service.findAll();
        List<SpecialtyDTO> dtoList = mapper.mapToDtoList(list);

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SpecialtyDTO> create(@RequestBody SpecialtyDTO dto) {

        SpecialtyDTO newDto = service.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newDto);
    }

    @GetMapping("/specialties/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/specialties/{id}")
    public ResponseEntity<SpecialtyDTO> update(@RequestBody SpecialtyDTO dto, @PathVariable Integer id) {

        try {
            SpecialtyDTO existing = service.findById(id);

            existing.setDescription(dto.getDescription());

            service.update(existing);
            return ResponseEntity.ok(existing);

        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/specialties/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        try {
            service.delete(id);
            return ResponseEntity.ok("Deleted ID: " + id);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
