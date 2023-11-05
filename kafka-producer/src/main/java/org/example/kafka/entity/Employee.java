package org.example.kafka.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String employeeId;
    private String name;
    private LocalDate birthDate;
}
