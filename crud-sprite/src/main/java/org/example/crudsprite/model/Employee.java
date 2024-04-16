package org.example.crudsprite.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {
    int employeeId;
    private String name;
    private int age;
    private String Department;
}
