package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    SpecialtyRepository repository;
    SpecialtyMapper mapper;

    public SpecialtyServiceImpl(SpecialtyRepository repository, SpecialtyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SpecialtyDTO create(SpecialtyDTO dto) {
        Specialty newSpecialty = repository.save(mapper.mapToEntity(dto));
        return mapper.mapToDto(newSpecialty);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO dto) {
        Specialty updated = repository.save(mapper.mapToEntity(dto));
        return mapper.mapToDto(updated);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        SpecialtyDTO dto = findById(id);
        repository.delete(mapper.mapToEntity(dto));
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = repository.findById(id);

        if (!specialty.isPresent())
            throw new SpecialtyNotFoundException("Specialty not found!");

        return mapper.mapToDto(specialty.get());
    }

    @Override
    public List<SpecialtyDTO> findByDescription(String description) {
        List<Specialty> list = repository.findByDescription(description);

        return list.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialty> findAll() {
        return repository.findAll();
    }
}
