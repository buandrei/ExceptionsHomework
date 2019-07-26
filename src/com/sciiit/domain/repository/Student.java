package com.sciiit.domain.repository;

public class Student {

    //the ID is/can be the CNP of the student
    private String id;
    private String firstName;
    private String lastName;
    //The birth date should be in format MM/dd/yyyy
    private String dateOfBirth;
    private char gender;


    public Student() {
    }

    public Student(String firstName, String lastName, String dateOfBirth, char gender, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = Character.toUpperCase(gender);
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = Character.toUpperCase(gender);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                '}';
    }
}
