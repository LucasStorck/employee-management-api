package com.lucas.employee_management.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id", nullable = false)
  private Long id;

  @Column(name = "employee_name", nullable = false)
  private String name;

  @Column(name = "employee_role", nullable = false)
  private String role;

  @Column(name = "employee_department", nullable = false)
  private String department;

  public Employee() {
  }

  public Employee(String name, String role, String department) {
    this.name = name;
    this.role = role;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
