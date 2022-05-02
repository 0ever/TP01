package mif.vu.lt.TP01.controllers;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.TP01.entities.University;
import mif.vu.lt.TP01.services.UniversityServiceImpl;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
@Getter
@Setter
public class UniversityController implements Serializable {

    @Inject
    private UniversityServiceImpl universityService;

    private University university = new University();
    private University universityToEdit = new University();

    @Transactional
    public String createUniversity() {
        if (!universityService.getAllUniversities().contains(university.getName())) {
            universityService.saveUniversity(university);
            university = new University();
        }
        return "index?faces-redirect=true";
    }
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @Transactional
    public String updateUniversity() {
        universityService.updateUniversity(universityToEdit);
        return "index?faces-redirect=true";
    }

}
