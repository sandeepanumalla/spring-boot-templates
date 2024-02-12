package org.example.crudsprite.controller;


import org.example.crudsprite.model.Employee;
import org.example.crudsprite.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/{eid}")
    public void findEmployee(@PathVariable int eid) {

    }


    @DeleteMapping("/{eid}")
    public void addEmployee(@PathVariable int eid) {

    }


}
