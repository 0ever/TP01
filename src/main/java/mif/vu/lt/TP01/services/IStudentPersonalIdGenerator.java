package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;

public interface IStudentPersonalIdGenerator {
    String generateId(Student student);
}