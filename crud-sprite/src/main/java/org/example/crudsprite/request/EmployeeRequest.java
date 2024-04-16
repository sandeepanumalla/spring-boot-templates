package org.example.crudsprite.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeRequest {
    private String name;
    private int age;
    private String Department;
}
