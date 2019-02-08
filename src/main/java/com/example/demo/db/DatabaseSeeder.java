package com.example.demo.db;


import com.example.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private  SkillRepository skillRepository;

    @Autowired
    public DatabaseSeeder(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Override
    public  void  run(String... strings) throws Exception{
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Angular"));
        skills.add(new Skill("SpringBoot"));
        skills.add(new Skill("NodeJS"));
        skills.add(new Skill("ReactJS"));
        skillRepository.saveAll(skills);
    }
}
