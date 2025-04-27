package com.lucas.employee_management.mapper;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.entities.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T22:14:55-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( dto.name() );
        employee.setRole( dto.role() );
        employee.setDepartment( dto.department() );

        return employee;
    }

    @Override
    public EmployeeResponseDto toResponseDto(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String role = null;
        String department = null;

        id = entity.getId();
        name = entity.getName();
        role = entity.getRole();
        department = entity.getDepartment();

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto( id, name, role, department );

        return employeeResponseDto;
    }

    @Override
    public void updateEntityFromDto(EmployeeRequestDto dto, Employee entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.name() );
        entity.setRole( dto.role() );
        entity.setDepartment( dto.department() );
    }
}
