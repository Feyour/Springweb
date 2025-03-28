package ru.albert.spring.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.albert.spring.dao.PersonDAO;
import ru.albert.spring.models.Person;
@Component
public class PersonValidator implements Validator {
    private PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @Override
    public boolean supports(Class<?> Clazz) {
        return Person.class.equals(Clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDAO.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email address is already in use");
        }
    }
}
