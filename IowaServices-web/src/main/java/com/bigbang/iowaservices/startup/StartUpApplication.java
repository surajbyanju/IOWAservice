/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.startup;

import com.bigbang.iowaservices.boundary.SkillFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Address;
import com.bigbang.iowaservices.entities.Skill;
import com.bigbang.iowaservices.entities.UserInformation;
import com.bigbang.iowaservices.entities.Users;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author dell
 */
@Singleton
@Startup
public class StartUpApplication {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB 
    UsersFacadeLocal userFacade;
    @EJB
    SkillFacadeLocal SkillFacade;
    Users user;
    Skill skill;
    
    @PostConstruct
    public void initApp(){
    
        user = new Users();
        UserInformation userInformation = new UserInformation();
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("1000 N 4th street");
        address.setState("IOWA");
        
        userInformation.setAddress(address);
        userInformation.setFirstName("Admin");
        userInformation.setLastName("Admin");
        userInformation.setPhoneNo("1234567890");
        
        user.setUserInformation(userInformation);
        user.setPassword("admin");
        user.setRole("ROLE_ADMIN");
        user.setUsername("admin");
        user.setEnabled(Boolean.TRUE);
        userFacade.create(user);
        
        skill = new Skill();
        skill.setCode("CAR");
        skill.setDescription("Carpenting");
        skill.setName("Carpening");
        SkillFacade.create(skill);
        
        skill = new Skill();
        skill.setCode("PAINT");
        skill.setDescription("Painting");
        skill.setName("Painting");
        SkillFacade.create(skill);
        
        skill = new Skill();
        skill.setCode("CARWASH");
        skill.setDescription("Car Washing");
        skill.setName("Car Washing");
        SkillFacade.create(skill);
        
        skill = new Skill();
        skill.setCode("FUR");
        skill.setDescription("Furnishing");
        skill.setName("Furnishing");
        SkillFacade.create(skill);
    }
}
