/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.SkillFacade;
import com.bigbang.iowaservices.boundary.SkillFacadeLocal;
import com.bigbang.iowaservices.entities.Skill;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author sajana
 */
@Named(value = "adminController")
@ManagedBean
@RequestScoped
public class AdminController {
       @EJB
    SkillFacadeLocal addNewSkill;
        @EJB
    SkillFacadeLocal skillFacadeLocal;
        
    private Skill newSkill;
    private List<Skill> allSkills;
    
    private  List<String> classList;
    /**
     * Creates a new instance of AdminController
     */
 
    @PostConstruct
    public void initSkills() {
        allSkills = skillFacadeLocal.findAll();
        
        classList =  new ArrayList<>();
        classList.add("circle pink-bg");
        classList.add("circle blue-bg");
        classList.add("circle yellow-bg");
        classList.add("circle green-bg");
    }
    public SkillFacadeLocal getAddNewSkill() {
        return addNewSkill;
    }
    
    public void setAddNewSkill(SkillFacadeLocal addNewSkill) {
        this.addNewSkill = addNewSkill;
    }
    
    public AdminController() {
        addNewSkill = new SkillFacade();
        newSkill = new Skill();
    }
    
    public Skill getNewSkill() {
        return newSkill;
    }
    
    public void setNewSkill(Skill newSkill) {
        this.newSkill = newSkill;
    }
    
    public List<Skill> getAllSkills() {
        return allSkills;
    }
    
    public void setAllSkills(List<Skill> allSkills) {
        this.allSkills = allSkills;
    }

    public List<String> getClassList() {
        return classList;
    }

    
    public String addSkill(){
        
        addNewSkill.create(newSkill);
        return "/services";
    }
    
    public String displaySkill(){
        
        addNewSkill.findAll();
        return "/home";
    }
    
    public String displaySpOfSkill(){
        
//        addNewSkill.findBySkill(code);
       
//        addNewSkill.findSpOfSkill();
        return "/serviceDetails";
    }
    
}
