package com.example.springbootentityoperations.decorator;

import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.mapper.PersonMapper;
import com.example.springbootentityoperations.model.Person;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
}


