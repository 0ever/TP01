package mybatis.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.UniversityMapper;
import mybatis.model.University;

@Model
@Getter
@Setter
public class MyBatisUniversityController implements Serializable {

    @Inject
    private UniversityMapper universityMapper;

    private Long universityToDeleteId;
    private University universityToAdd = new University();
    private List<University> allUniversities;


    @PostConstruct
    void init() {
        this.allUniversities = universityMapper.selectAll();
    }

    @Transactional
    public String deleteUniversity() {
        universityMapper.deleteByPrimaryKey(universityToDeleteId);
        return "/mybatis/mybatis?faces-redirect=true";
    }

    @Transactional
    public String addUniversity() {
        universityMapper.insert(universityToAdd);
        return "/mybatis/mybatis?faces-redirect=true";
    }
}