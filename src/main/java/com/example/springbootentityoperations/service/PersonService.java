package com.example.springbootentityoperations.service;

import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person createPerson(PersonDTO personDTO);

    Person updatePerson(int id,PersonDTO personDTO);

    List<Person> getPersons();

    void deletePerson(int id);

    Person getPerson(int id);



}
