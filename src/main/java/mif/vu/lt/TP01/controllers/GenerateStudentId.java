package mif.vu.lt.TP01.controllers;


import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.interceptors.LoggedInvocation;
import mif.vu.lt.TP01.services.StudentPersonalIdGenerator;
import mif.vu.lt.TP01.services.StudentServiceImpl;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateStudentId implements Serializable {
    @Inject
    private StudentPersonalIdGenerator studentPersonalIdGenerator;

    @Inject
    private StudentServiceImpl studentService;

    private CompletableFuture<String> loginNameGenerationTask = null;

    @Getter @Setter
    private long currentStudentId;

    @LoggedInvocation
    public String generate() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Long studentId = Long.parseLong(requestParameters.get("studentId"));

        Student student = studentService.getStudentById(studentId);

        this.currentStudentId = studentId;
        loginNameGenerationTask = CompletableFuture.supplyAsync(() -> studentPersonalIdGenerator.generateId(student))
                .exceptionally(Throwable::getMessage);

        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isLoginNameGeneratorRunning() {
        return loginNameGenerationTask != null && !loginNameGenerationTask.isDone();
    }

    public String getLoginGenerationStatus() throws ExecutionException, InterruptedException {
        if (loginNameGenerationTask == null) {
            return null;
        }

        if (isLoginNameGeneratorRunning()) {
            return "Student's personal id generation is running...";
        }

        return "Suggested student's personal id name: " + loginNameGenerationTask.get();
    }
}
