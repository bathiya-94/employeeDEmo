package com.example.demo.viewModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

public class EmployeeViewModel {
    private  Long id;

    @NotNull
    private String name;

    @Email
    private String email;

    private  Date dob;

    private  List<SkillViewModel> skills;

    public EmployeeViewModel(){
        this.skills = new ArrayList<>();
    }

    public EmployeeViewModel(String name, String email, Date dob){
        this();
        this.name = name;
        this.dob = dob;
        this.email =email;

    }
    public String getName(){
        return  name;
    }

    public Date getDob(){
        return  dob;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<SkillViewModel> getSkills() {
        return skills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSkills(List<SkillViewModel> skills) {
        this.skills = skills;
    }
}
