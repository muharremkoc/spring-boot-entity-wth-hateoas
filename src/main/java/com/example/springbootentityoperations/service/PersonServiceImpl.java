package com.example.springbootentityoperations.service;

import com.example.springbootentityoperations.decorator.PersonDecorator;
import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.mapper.PersonMapper;
import com.example.springbootentityoperations.model.Person;
import com.example.springbootentityoperations.repository.PersonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    final PersonRepository personRepository;

    final PersonMapper personMapper;

    final PersonDecorator personDecorator;


    @Override
    public Person createPerson(PersonDTO personDTO) {

        Person person=personMapper.personDTOToPerson(personDTO);


        Person savePerson=personDecorator.personWithPersonLink(person);


        return personRepository.save(savePerson);
    }

    @Override
    public Person updatePerson(int id, PersonDTO personDTO) {

        Person newPerson=personRepository.findById(id).get();

        Date date=new Date(System.currentTimeMillis());

        newPerson.setFirstName(personDTO.getFirstName());
        newPerson.setGender(personDTO.getGender());
        newPerson.setAge(date.getYear()-personDTO.getBirthday().getYear());


        return personRepository.save(newPerson);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(int id) {

        personRepository.deleteById(id);

    }

    @Override
    public Person getPerson(int id) {

        Person getPerson=personRepository.findById(id).get();

        Person decorPerson=personDecorator.personWithPersonLink(getPerson);

        return decorPerson;


    }
}
