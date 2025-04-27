package com.lucas.employee_management.services;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
  /**
   * Retrieves all employees.
   *
   * @return a list of employee response DTOs
   */
  List<EmployeeResponseDto> getAllEmployees();

  /**
   * Retrieves an employee by ID.
   *
   * @param id the ID of the employee
   * @return the employee response DTO
   * @throws EntityNotFoundException if no employee is found with the given ID
   */
  EmployeeResponseDto findEmployeeById(Long id);

  /**
   * Creates a new employee.
   *
   * @param employeeRequestDto the DTO containing employee data
   * @return the created employee response DTO
   */
  EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

  /**
   * Updates an existing employee by ID.
   *
   * @param id                 the ID of the employee to update
   * @param employeeRequestDto the DTO with updated employee data
   * @return the updated employee response DTO
   * @throws EntityNotFoundException if no employee is found with the given ID
   */
  EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

  /**
   * Deletes an employee by ID.
   *
   * @param id the ID of the employee to delete
   * @return true if the employee was deleted, false if not found
   */
  boolean deleteEmployeeById(Long id);
}
