package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    Specialty mapToEntity(SpecialtyDTO dto);

    SpecialtyDTO mapToDto(Specialty entity);

    List<SpecialtyDTO> mapToDtoList(List<Specialty> list);
}
