package com.example.demo;

import com.example.demo.db.EmployeeRepository;
import com.example.demo.db.SkillRepository;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import com.example.demo.viewModel.EmployeeViewModel;
import com.example.demo.viewModel.SkillViewModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private EmployeeRepository employeeRepository;
    private SkillRepository skillRepository;

    public  Mapper(EmployeeRepository employeeRepository, SkillRepository skillRepository){
        this.skillRepository = skillRepository;
        this.employeeRepository = employeeRepository;

    }

    public  SkillViewModel convertToSkillViewModel(Skill skill){
        SkillViewModel skillViewModel = new SkillViewModel(skill.getSkill());
        skillViewModel.setId(skill.getId());

        List<EmployeeViewModel> employeeViewModels = skill.getEmployees().stream()
                .map(employee -> {EmployeeViewModel  employeeViewModel =  new EmployeeViewModel();

                   employeeViewModel.setId(employee.getId());
                    employeeViewModel.setName(employee.getName());
                    employeeViewModel.setDob(employee.getDob());
                    employeeViewModel.setEmail(employee.getEmail());

                    return  employeeViewModel;
                }).collect(Collectors.toList());

        skillViewModel.setEmployees(employeeViewModels);
        return  skillViewModel;

    }

    public EmployeeViewModel convertToEmployeeViewModel(Employee employee){

        EmployeeViewModel  employeeViewModel =  new EmployeeViewModel();
        employeeViewModel.setId(employee.getId());
        employeeViewModel.setName(employee.getName());
        employeeViewModel.setDob(employee.getDob());
        employeeViewModel.setEmail(employee.getEmail());

        List <SkillViewModel> skills = employee.getSkills().stream()
                .map(skill -> convertToSkillViewModel(skill))
                .collect(Collectors.toList());

        employeeViewModel.setSkills(skills);

        return  employeeViewModel;

    }

    public  Skill convertToSkill (SkillViewModel skillViewModel) {

        Skill skill;
        if(skillViewModel.getId() !=null)
        {
            skill = skillRepository.findById(skillViewModel.getId()).get();
        }
        else {
            skill = new Skill();
        }
        skill.setSkill(skillViewModel.getSkill());
        return  skill;
    }

    public Employee convertToEmployee(EmployeeViewModel employeeViewModel){
        Employee employee ;
        if(employeeViewModel.getId() !=null){
            //employee.setId(employeeViewModel.getId());
            employee = employeeRepository.findById(employeeViewModel.getId()).get();
        }
        else{
            employee = new Employee();
        }

        employee.setName(employeeViewModel.getName());
        employee.setDob(employeeViewModel.getDob());
        employee.setEmail(employeeViewModel.getEmail());

        Set<Skill> skills = employeeViewModel.getSkills().stream()
                .map(viewModel1 -> {
                    Skill skill = skillRepository.findBySkill(viewModel1.getSkill());
                    skill.getEmployees().add(employee);
                    return  skill;

                }).collect(Collectors.toSet());

        employee.setSkills(skills);

        return employee;
    }
}
