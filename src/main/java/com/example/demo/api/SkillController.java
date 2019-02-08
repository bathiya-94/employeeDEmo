package com.example.demo.api;

import com.example.demo.Mapper;
import com.example.demo.db.SkillRepository;
import com.example.demo.model.Skill;
import com.example.demo.viewModel.SkillViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import  java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/api/skills")
@CrossOrigin
public class SkillController {
    private SkillRepository skillRepository;
    private Mapper mapper;

    @Autowired
    public  SkillController(SkillRepository skillRepository , Mapper mapper){
        this.skillRepository = skillRepository;
        this.mapper = mapper;
    }

    @GetMapping(value ="/all")
    public List<SkillViewModel>  getAll(){
        return  skillRepository.findAll().stream()
                .map(skill -> this.mapper.convertToSkillViewModel(skill))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")

    public SkillViewModel getById(@PathVariable Long id){
        Skill skill = this.skillRepository.findById(id).orElse(null);

        if(skill == null){
            throw  new EntityNotFoundException();
        }
        return  this.mapper.convertToSkillViewModel(skill);
    }

    @PostMapping
    public  SkillViewModel save(@RequestBody SkillViewModel skillViewModel, BindingResult bindingResult) throws ValidationException{

        if (bindingResult.hasErrors()){
            throw  new javax.validation.ValidationException("Skill");
        }
        Skill skill = this.mapper.convertToSkill(skillViewModel);
        this.skillRepository.save(skill);
        return this.mapper.convertToSkillViewModel(skill);
    }

}
