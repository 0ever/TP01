package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.entities.University;

import java.util.List;

public interface UniversityService {

    List<University> getAllUniversities();
    void saveUniversity (University university);
    University getUniversityById(long id);
    void updateUniversity (University university);
    void deleteUniversityById(long id);
}
