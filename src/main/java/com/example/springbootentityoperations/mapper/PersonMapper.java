package com.example.springbootentityoperations.mapper;


import com.example.springbootentityoperations.decorator.PersonDecorator;
import com.example.springbootentityoperations.dto.PersonDTO;
import com.example.springbootentityoperations.model.Person;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@DecoratedWith(PersonDecorator.class)
@Mapper
public interface PersonMapper {


    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "gender",source = "gender")
    Person personToPersonDTO(PersonDTO personDTO);
}


