package com.lucas.employee_management.services;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.entities.Employee;
import com.lucas.employee_management.mapper.EmployeeMapper;
import com.lucas.employee_management.respositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private EmployeeMapper employeeMapper;

  private Employee employee;
  private EmployeeRequestDto employeeRequestDto;
  private EmployeeResponseDto employeeResponseDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);
    employeeRequestDto = new EmployeeRequestDto("John Doe", "Developer", "Technology");
    employeeResponseDto = new EmployeeResponseDto(1L, "John Doe", "Developer", "Technology");

    employee = new Employee("John Doe", "Developer", "Technology");
  }


  @Test
  void shouldReturnAllEmployees() {
    when(employeeRepository.findAll()).thenReturn(List.of(employee));
    when(employeeMapper.toResponseDto(any(Employee.class))).thenReturn(employeeResponseDto);

    var employees = employeeService.getAllEmployees();

    assertEquals(1, employees.size());
    assertEquals("John Doe", employees.get(0).name());
  }

  @Test
  void shouldReturnEmployeeById() {
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    when(employeeMapper.toResponseDto(employee)).thenReturn(employeeResponseDto);

    var result = employeeService.findEmployeeById(1L);

    assertEquals(employeeResponseDto, result);
  }

  @Test
  void shouldCreateEmployee() {
    when(employeeMapper.toEntity(employeeRequestDto)).thenReturn(employee);
    when(employeeRepository.save(employee)).thenReturn(employee);
    when(employeeMapper.toResponseDto(employee)).thenReturn(employeeResponseDto);

    var result = employeeService.createEmployee(employeeRequestDto);

    assertEquals(employeeResponseDto, result);
  }

  @Test
  void shouldUpdateEmployeeWhenEmployeeExists() {
    EmployeeRequestDto requestDto = new EmployeeRequestDto("John Smith", "Developer", "Technology");
    EmployeeResponseDto responseDto = new EmployeeResponseDto(1L, "John Smith", "Developer", "Technology");
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    doAnswer(invocation -> {
      EmployeeRequestDto dto = invocation.getArgument(0);
      Employee entity = invocation.getArgument(1);
      entity.setName(dto.name());
      entity.setRole(dto.role());
      entity.setDepartment(dto.department());
      return null;
    }).when(employeeMapper).updateEntityFromDto(any(EmployeeRequestDto.class), any(Employee.class));

    when(employeeRepository.save(employee)).thenReturn(employee);
    when(employeeMapper.toResponseDto(employee)).thenReturn(responseDto);

    var result = employeeService.updateEmployee(1L, requestDto);

    assertNotNull(result);
    assertEquals("John Smith", result.name());
    assertEquals("Developer", result.role());
    assertEquals("Technology", result.department());
  }

  @Test
  void deleteEmployee() {
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

    boolean result = employeeService.deleteEmployeeById(1L);

    assertTrue(result);
    verify(employeeRepository, times(1)).delete(employee);
  }
}