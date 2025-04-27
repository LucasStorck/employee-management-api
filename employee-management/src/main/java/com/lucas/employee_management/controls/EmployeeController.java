package com.lucas.employee_management.controls;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Employees", description = "Operations related to Employee management")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Operation(summary = "List all employees", description = "Returns a list of all registered employees.")
  @GetMapping
  public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
    List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  @Operation(summary = "Get an employee by id", description = "Returns employee details for the given id.")
  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDto> findEmployeeById(@PathVariable Long id) {
    EmployeeResponseDto employeeDto = employeeService.findEmployeeById(id);
    return ResponseEntity.ok(employeeDto);
  }

  @Operation(summary = "Create a new employee", description = "Adds a new employee to the system.")
  @PostMapping
  public ResponseEntity<EmployeeResponseDto> createEmployee(
          @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
    EmployeeResponseDto createdEmployee = employeeService.createEmployee(employeeRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
  }

  @Operation(summary = "Update an employee", description = "Updates the employee information for the given id.")
  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponseDto> updateEmployee(
          @PathVariable Long id,
          @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
    EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, employeeRequestDto);
    return ResponseEntity.ok(updatedEmployee);
  }

  @Operation(summary = "Delete an employee", description = "Deletes an employee for the given id.")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    boolean deleted = employeeService.deleteEmployeeById(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
