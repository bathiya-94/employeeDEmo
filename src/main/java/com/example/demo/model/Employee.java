package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;
    private String name;
    private  String email;
    private Date dob;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Skill",
            joinColumns = {
                    @JoinColumn(
                            name = "employee_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "skill_id",
                            referencedColumnName = "id"
                    )
            })
    private Set<Skill> skills ;

    public  Employee(){}
    public Employee(String name, String email, Date dob, Skill...skills){
        this.name =name;
        this.email = email;
        this.dob = dob;

       this.skills = Stream.of(skills).collect(Collectors.toSet());
       this.skills .forEach(x-> x.getEmployees().add(this));

    }

    public long getId() {
        return id;
    }

    public String getName()  {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setId(long id) {
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

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
