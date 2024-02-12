package org.example.crudsprite.services;

import jakarta.annotation.PostConstruct;
import org.example.crudsprite.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;

@Service

public class EmployeeService {

    List<Employee> employees = new ArrayList<Employee>();


    @PostConstruct
    public void loadTheUsers() {
        for (int i = 0; i < 10; i++) {
            // Generating sample data for each employee
            String name = "Employee " + (i + 1);
            int age = 25 + i; // Sample age
            String department = "Department " + ((i % 3) + 1); // Three departments
            // Creating a new User object and adding it to the list
            employees.add(new Employee(name, age, department));
        }
    }

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

    public void updateEmployee(Employee employee) {

    }

    public List<Employee> getAllEmployees() {
        return employees;
    }


}
