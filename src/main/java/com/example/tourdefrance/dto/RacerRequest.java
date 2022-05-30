package com.example.tourdefrance.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RacerRequest {
    String firstName;
    String lastName;
    String country;
    int age;
    int points;
}
