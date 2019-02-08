package com.example.demo.model;

import com.example.demo.model.Employee;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String skill;

    @ManyToMany(mappedBy = "skills")
    private Set<Employee> employees = new HashSet<>();

    public Skill() {
    }

    public Skill(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public Long getId() {
        return id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
