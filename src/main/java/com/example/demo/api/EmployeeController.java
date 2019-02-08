package com.example.demo.api;

import com.example.demo.Mapper;
import com.example.demo.db.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.viewModel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value="/api/employees")
@CrossOrigin
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private Mapper mapper;


    public EmployeeController(EmployeeRepository employeeRepository, Mapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;

    }

    @RequestMapping(value = "/all", method = GET)
    public List<EmployeeViewModel> getAll() {
        return employeeRepository.findAll()
                .stream().map(employee -> this.mapper.convertToEmployeeViewModel(employee))
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeViewModel save(@RequestBody EmployeeViewModel employeeViewModel, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Employee");
        }
        Employee employee = this.mapper.convertToEmployee(employeeViewModel);
        this.employeeRepository.save(employee);

        return this.mapper.convertToEmployeeViewModel(employee);
    }


    @GetMapping("/{id}")
    public EmployeeViewModel getById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException();
        }

        return this.mapper.convertToEmployeeViewModel(employee);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.employeeRepository.deleteById(id);
    }
}