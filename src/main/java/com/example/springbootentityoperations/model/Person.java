package com.example.springbootentityoperations.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "PERSON_LIST")
public class Person extends RepresentationModel<Person> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String firstName;

    int age;

    String gender;

}
