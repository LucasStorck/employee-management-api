package com.lucas.employee_management.services;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.entities.Employee;
import com.lucas.employee_management.exceptions.EntityNotFoundException;
import com.lucas.employee_management.mapper.EmployeeMapper;
import com.lucas.employee_management.respositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  @Override
  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(employeeMapper::toResponseDto)
            .collect(Collectors.toList());
  }

  @Override
  public EmployeeResponseDto findEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " was not found or does not exist"));
    return employeeMapper.toResponseDto(employee);
  }

  @Override
  public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
    Employee employee = employeeMapper.toEntity(employeeRequestDto);
    Employee createdEmployee = employeeRepository.save(employee);
    return employeeMapper.toResponseDto(createdEmployee);
  }

  @Override
  public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " was not found or does not exist"));
    employeeMapper.updateEntityFromDto(employeeRequestDto, employee);

    Employee updatedEmployee = employeeRepository.save(employee);

    return employeeMapper.toResponseDto(updatedEmployee);
  }

  @Override
  public boolean deleteEmployeeById(Long id) {
    return employeeRepository.findById(id).map(employee -> {
      employeeRepository.delete(employee);
      return true;
    }).orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " was not found or does not exist"));
  }
}
