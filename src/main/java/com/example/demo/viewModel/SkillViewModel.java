package com.example.demo.viewModel;

import com.example.demo.model.Employee;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SkillViewModel {
    private  Long id;

    @NotNull
    private String skill;

    private List<EmployeeViewModel> employees;

    public SkillViewModel(){
        this.employees = new ArrayList<>();
    }
    public  SkillViewModel(String skill){
        this();
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public List<EmployeeViewModel> getEmployees() {
        return employees;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployees(List<EmployeeViewModel> employees) {
        this.employees = employees;
    }
}

