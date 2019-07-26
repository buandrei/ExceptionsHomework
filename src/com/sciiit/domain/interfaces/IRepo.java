package com.sciiit.domain.interfaces;

import com.sciiit.domain.validators.ValidationException;

import java.util.List;

public interface IRepo<T> {

    void addStudent(T obj) throws ValidationException;

    void deleteStudent(String id) throws ValidationException;

    List<T> retrieveStudentsByAge(String age) throws ValidationException;

    List<T> listStudentsByBirthDate() throws ValidationException;
}
