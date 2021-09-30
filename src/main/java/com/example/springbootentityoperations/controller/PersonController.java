package com.example.springbootentityoperations.controller;

import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.mapper.PersonMapper;
import com.example.springbootentityoperations.model.Person;
import com.example.springbootentityoperations.service.PersonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonController {


    final PersonService personService;
    final PersonMapper personMapper;

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO){



        Person person=personService.createPerson(personDTO);
        var links = new Link[]{
              linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel()

       };

        person.add(links);
        try {


            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getPersons() {


        try {
            List<Person> persons = personService.getPersons();

            persons.stream().forEach(person -> {
                        var links = new Link[]{
                                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel()

                        };
                        person.add(links);
                    }
                    );
            return ResponseEntity.ok(persons);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") int id,@RequestBody PersonDTO personDTO){
        Person person=personService.updatePerson(id,personDTO);

        var links = new Link[]{
                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel()

        };

        person.add(links);
        try {

            return new ResponseEntity<Person>(person, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/person/{id}")
    public void  deletePerson(@PathVariable("id")int id){
        personService.deletePerson(id);
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id")int id){
        Person person=personService.getPerson(id);
        var links = new Link[]{
                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel()

        };
        return personService.getPerson(id);
    }

}
