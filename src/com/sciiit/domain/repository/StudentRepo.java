package com.sciiit.domain.repository;

import com.sciiit.domain.interfaces.IRepo;
import com.sciiit.domain.validators.ValidationException;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentRepo implements IRepo<Student> {

    Map<String, Student> repo = new HashMap<>();


    @Override
    public void addStudent(Student student) throws ValidationException {
        validateStudent(student);
        repo.put(student.getId(), student);
    }

    @Override
    public void deleteStudent(String id) throws ValidationException {


        if (isBLank(id)) {
            throw new ValidationException("ID cannot be null!");
        }
        if (!repo.containsKey(id)) {
            throw new ValidationException("Student with key " + id + " does not exist!");
        }
        repo.remove(id);
    }

    private boolean isBLank(String id) {
        return id == null || id.isBlank();
    }

    @Override
    public List<Student> retrieveStudentsByAge(String age) throws ValidationException {

        if (isBLank(age)) {
            throw new ValidationException("The age cannot be null!");
        }
        try {
            int currentAge = Integer.parseInt(age);
            if (currentAge < 0) {
                throw new ValidationException("The age cannot be negative!");
            }

            //get the age
            return repo.values().stream().filter(new Predicate<Student>() {
                @Override
                public boolean test(Student student) {
                    return (currentAge == getAge(student));
                }
            }).sorted(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return getAge(o1) - getAge(o2);
                }
            }).collect(Collectors.toList());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ValidationException("Age is not a number!");
        }


    }

    public List<Student> listStudentsByBirthDate() throws ValidationException {
        //Nu mi-a fost clar ce exeptie sa arunc aici. Presupun doar sa arunc exceptie daca repoul este gol
        if (repo.isEmpty()) {
            throw new ValidationException("Notting to list!");
        }
        return repo.values().stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
            }
        }).collect(Collectors.toList());
    }

    private int getAge(Student student) {

        LocalDate birthDate = LocalDate.parse(student.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(birthDate, LocalDate.now()).getYears();

    }

    private void validateStudent(Student student) throws ValidationException {

        String dateOfBirth = student.getDateOfBirth();
        //checking for null
        if (isBLank(dateOfBirth)) {
            throw new ValidationException("The Date of birth is empty!");
        }

        //creating SimpleDateFormatter for our dateofbirth parameter
        //it will be in MM/dd/yyyy format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate myDate;
        try {
            myDate = LocalDate.parse(dateOfBirth, dateFormat);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new ValidationException("Invalid date format!");
        }
        LocalDate verifyDate1 = LocalDate.of(1900, Month.JANUARY, 1);
        LocalDate dueDate = LocalDate.now().minusYears(18);


        if (myDate.isBefore(verifyDate1)) {
            throw new ValidationException("The Date of birth not valid!It's under 1900! Are you sure you are not a ghost?");
        }
        if (myDate.isAfter(dueDate)) {
            throw new ValidationException("Date of birth under 18!");
        }
    }


}
