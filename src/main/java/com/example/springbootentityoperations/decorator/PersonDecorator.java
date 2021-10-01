package com.example.springbootentityoperations.decorator;

import com.example.springbootentityoperations.controller.PersonController;
import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.mapper.PersonMapper;
import com.example.springbootentityoperations.model.Person;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;

import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDecorator implements PersonMapper {

    @Setter(onMethod = @__({@Autowired}))
    PersonMapper personMapper;

    @Override
    public Person personDTOToPerson(PersonDTO personDTO) {
        Person person=personMapper.personDTOToPerson(personDTO);
        Date date=new Date(System.currentTimeMillis());

        if(date.getYear()>=personDTO.getBirthday().getYear())
        person.setAge(date.getYear()-personDTO.getBirthday().getYear());

        return person;
    }


    @Override
    public Person personWithPersonLink(Person person) {
        var person1=personMapper.personWithPersonLink(person);
        Link[] links = new Link[]{
                linkTo(methodOn(PersonController.class).createPerson(null))
                        .withRel("person")
                        .withType("POST")
                        .withDeprecation("Add Person"),
                linkTo(methodOn(PersonController.class).getPersons())
                        .withRel("person")
                        .withType("GET")
                        .withDeprecation("List Students"),
                linkTo(methodOn(PersonController.class).updatePerson(null,null))
                        .withRel("Persn")
                        .withType("PUT")
                        .withDeprecation("UpdatePerson")


        };
        person1.add(links);
        return person1;
    }


}


