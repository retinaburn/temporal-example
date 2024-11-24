package com.moynes.temporal.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor //needed for json deserialization
@Data
public class User {
    String firstName;
    String lastName;
    String createdOn;
}
