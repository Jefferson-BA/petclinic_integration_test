package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;

import java.util.List;

public interface SpecialtyService {

    SpecialtyDTO create(SpecialtyDTO dto);

    SpecialtyDTO update(Integer id, SpecialtyDTO dto) throws SpecialtyNotFoundException;

    void delete(Integer id) throws SpecialtyNotFoundException;

    SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException;

    List<SpecialtyDTO> findByName(String name);

    List<Specialty> findAll();
}
