package com.sciiit.domain;

import com.sciiit.domain.interfaces.IRepo;
import com.sciiit.domain.repository.Student;
import com.sciiit.domain.repository.StudentRepo;
import com.sciiit.domain.validators.ValidationException;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        LOGGER.log(Level.INFO, "Starting app...");


        Student student1 = new Student();
        student1.setFirstName("Mircea");
        student1.setLastName("cel Batran");
        student1.setDateOfBirth("12/11/13355");
        student1.setGender('m');
        student1.setId("1911112303467");

        Student student2 = new Student("Ioana", "Andrenescu", "12/11/1293", 'f', "2326713123362");
        Student student3 = new Student("", "Popescu", "02/05/1989", 'M', "187155317263");
        Student student4 = new Student("Teodora", "Popescu", "02/05/1987", 'F', "1923823712446");
        Student student5 = new Student("Gabriel", "Mirunica", "12/07/1991", 'M', "187126317263");
        Student student6 = new Student("Vasilica", "cel Viteaz", "14/04/1991", 'M', "13762131233");

        IRepo<Student> studentRepo = new StudentRepo();

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6);

        students.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                try {
                    studentRepo.addStudent(student);
                } catch (ValidationException e) {

                    LOGGER.log(Level.WARNING, "Student has not been added! Student: " + student.getFirstName() + "  " + student.getLastName() + " " + e.getMessage() + "\n");

                }
            }
        });

        try {
            studentRepo.deleteStudent("");
        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

        try {
            System.out.println(studentRepo.retrieveStudentsByAge("27"));
        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

        try {
            System.out.println(studentRepo.listStudentsByBirthDate());
        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}
