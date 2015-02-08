package com.bigbang.iowservices.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bigbang.iowaservices.boundary.SkillFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Skill;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rejina
 */
@ManagedBean
@RequestScoped
public class UserController {

    @EJB
    UsersFacadeLocal service;
    @EJB
    EmailService emailService;
    @EJB
    SkillFacadeLocal skillFacadeLocal;
    private Users user;
    private Map<String, Object> skillsValue;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        service = new UsersFacade();
        user = new Users();
        skillsValue = new HashMap<>();
    }

    public UsersFacadeLocal getService() {
        return service;
    }

    public void setService(UsersFacadeLocal service) {
        this.service = service;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Map<String, Object> getSkillsValue() {
        return skillsValue;
    }

    public void setSkillsValue(Map<String, Object> skillsValue) {
        this.skillsValue = skillsValue;
    }

    public String registerPage() {

        System.out.println("here+++ " + skillFacadeLocal.findAll());
        for (Skill skill : skillFacadeLocal.findAll()) {
            skillsValue.put(skill.getName(), skill.getCode());
        }
        return "register";
    }

    public String register() {
        user.setEnabled(Boolean.TRUE);
        user.setRole("ROLE_ADMIN");
        service.create(user);

        String messageLink = "http://localhost:8080/IowaServices-web/validateUser?userId=" + user.getId();
        emailService.sendEmailAfterRegister(user.getUsername(), messageLink);

        return "home";
    }

    public String validateUser() {
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String value=request.getParameter("userId");
               
        return "main";
    }

}
