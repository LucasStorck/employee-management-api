package com.lucas.employee_management.mapper;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  Employee toEntity(EmployeeRequestDto dto);

  EmployeeResponseDto toResponseDto(Employee entity);

  void updateEntityFromDto(EmployeeRequestDto dto, @MappingTarget Employee entity);
}
