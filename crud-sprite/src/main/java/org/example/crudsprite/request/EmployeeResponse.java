package org.example.crudsprite.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String name;
    private int age;
    private String Department;
}
