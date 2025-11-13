package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;

    public SpecialtyServiceImpl(SpecialtyRepository repository, SpecialtyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SpecialtyDTO create(SpecialtyDTO dto) {
        Specialty entity = mapper.mapToEntity(dto);
        return mapper.mapToDto(repository.save(entity));
    }

    @Override
    public SpecialtyDTO update(Integer id, SpecialtyDTO dto) throws SpecialtyNotFoundException {

        Optional<Specialty> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new SpecialtyNotFoundException("Specialty not found: " + id);

        Specialty entity = opt.get();
        entity.setName(dto.getName());
        entity.setOffice(dto.getOffice());
        entity.setH_open(dto.getH_open());
        entity.setH_close(dto.getH_close());

        return mapper.mapToDto(repository.save(entity));
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {

        if (!repository.existsById(id))
            throw new SpecialtyNotFoundException("Specialty not found: " + id);

        repository.deleteById(id);
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {

        return repository.findById(id)
                .map(mapper::mapToDto)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found: " + id));
    }

    @Override
    public List<SpecialtyDTO> findByName(String name) {
        return repository.findByName(name)
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public List<Specialty> findAll() {
        return repository.findAll();
    }
}
