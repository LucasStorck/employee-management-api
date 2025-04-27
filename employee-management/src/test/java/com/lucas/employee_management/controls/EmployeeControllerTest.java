package com.lucas.employee_management.controls;

import com.lucas.employee_management.dtos.EmployeeRequestDto;
import com.lucas.employee_management.dtos.EmployeeResponseDto;
import com.lucas.employee_management.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  private EmployeeRequestDto employeeRequestDto;
  private EmployeeResponseDto employeeResponseDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    employeeRequestDto = new EmployeeRequestDto("Mary Doe", "Developer", "Technology");
    employeeResponseDto = new EmployeeResponseDto(1L, "John Doe", "Developer", "Technology");
  }

  @Test
  void shouldReturnListOfEmployees_whenGetAllEmployeesIsCalled() throws Exception {
    when(employeeService.getAllEmployees()).thenReturn(List.of(employeeResponseDto));

    mockMvc.perform(get("/api/employees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John Doe"))
            .andExpect(jsonPath("$[0].role").value("Developer"))
            .andExpect(jsonPath("$[0].department").value("Technology"));

    verify(employeeService, times(1)).getAllEmployees();
  }

  @Test
  void shouldReturnEmployeeDetails_whenFindEmployeeByIdIsCalled() throws Exception {
    when(employeeService.findEmployeeById(1L)).thenReturn(employeeResponseDto);

    mockMvc.perform(get("/api/employees/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.role").value("Developer"))
            .andExpect(jsonPath("$.department").value("Technology"));

    verify(employeeService, times(1)).findEmployeeById(1L);
  }

  @Test
  void shouldCreateEmployee_whenValidEmployeeIsProvided() throws Exception {
    when(employeeService.createEmployee(any(EmployeeRequestDto.class)))
            .thenReturn(new EmployeeResponseDto(1L, "John Doe", "Developer", "Technology"));
    mockMvc.perform(post("/api/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"John Doe\",\"role\":\"Developer\",\"department\":\"Technology\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.role").value("Developer"))
            .andExpect(jsonPath("$.department").value("Technology"));

    verify(employeeService, times(1)).createEmployee(any(EmployeeRequestDto.class));
  }

  @Test
  void shouldUpdateEmployee_whenValidEmployeeIsPresent() throws Exception {
    when(employeeService.updateEmployee(eq(1L), any(EmployeeRequestDto.class)))
            .thenReturn(new EmployeeResponseDto(1L, "Mary Doe", "Developer", "Technology"));

    mockMvc.perform(put("/api/employees/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"John Doe\",\"role\":\"Developer\",\"department\":\"Technology\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Mary Doe"))
            .andExpect(jsonPath("$.role").value("Developer"))
            .andExpect(jsonPath("$.department").value("Technology"));

    verify(employeeService, times(1)).updateEmployee(eq(1L), any(EmployeeRequestDto.class));
  }

  @Test
  void shouldDeleteEmployee_whenValidEmployeeIsPresent() throws Exception {
    when(employeeService.deleteEmployeeById(1L)).thenReturn(true);
    mockMvc.perform(delete("/api/employees/1"))
            .andExpect(status().isNoContent());

    verify(employeeService, times(1)).deleteEmployeeById(1L);
  }
}