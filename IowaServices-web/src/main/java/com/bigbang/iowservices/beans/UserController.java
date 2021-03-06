package com.bigbang.iowservices.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bigbang.iowaservices.boundary.SkillFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.Skill;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    private Users user;
    private Users spUser;
    private List<Skill> skillsValue;
    private List<String> selectedSkills;
    private final static String messageLink = "http://localhost:8080/IowaServices-web/validateUser.jsf?userId=";
    private final static String subject ="Verify Iowa service account ";
    private final static String finalMessage = "\n\n Thanks for joining us ! \n\n\n\n please click the activation link ";
    
    private List<Users> activeUsers;
    private List<Users> inactiveUsers;

    /**
     * Creates a new instance of UserController
     */
    @PostConstruct
    public void initSkills() {
        skillsValue = skillFacadeLocal.findAll();
        
        activeUsers = service.findActiveUsers();
    }

    public UserController() {
        service = new UsersFacade();
        user = new Users();
        spUser = new ServiceProvider();
        skillsValue = new ArrayList<>();
        
        activeUsers   = new ArrayList<>();
    }

    public String registerPage() {
        for (Skill skill : skillFacadeLocal.findAll()) {
            skillsValue.add(skill);
        }
        return "register";
    }

    public String register() {
        user.setEnabled(Boolean.FALSE);
        user.setRole("ROLE_USER");
        service.create(user);

        String mLink = messageLink + user.getId();
        String messageBody = "Dear "+user.getUserInformation().getFullName()+finalMessage+mLink;
        emailService.serviceRequestEmail(user.getUsername(), subject, messageBody);
//        emailService.sendEmailAfterRegister(user.getUsername(), mLink);
        FacesContext.getCurrentInstance().addMessage("test", new javax.faces.application.FacesMessage("Thank you for registering. We have sent you a mail to validate you account."));
        return "/home";
    }

    public String registerSP() {
        List<Skill> skillList = new ArrayList<>();
        ServiceProvider spUser2 = (ServiceProvider) spUser;
        spUser2.setEnabled(Boolean.FALSE);
        spUser2.setRole("ROLE_SP");
        for (String selectedSkill : selectedSkills) {
            skillList.add(skillFacadeLocal.findByCode(selectedSkill));
        }
        spUser2.setSkills(skillList);
        service.create(spUser2);
        String mLink = messageLink + spUser2.getId();
        String messageBody = "Dear "+spUser2.getUserInformation().getFullName()+finalMessage+mLink;
        emailService.serviceRequestEmail(spUser2.getUsername(), subject, messageBody);
//        emailService.sendEmailAfterRegister(spUser2.getUsername(), mLink);
        FacesContext.getCurrentInstance().addMessage("test", new javax.faces.application.FacesMessage("Thank you for registering. We have sent you a mail to validate you account."));
        return "/home";
    }

    public String validateUser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String value = request.getParameter("userId");
        Long userId = Long.parseLong(value);
        Users user = service.find(userId);
        user.setEnabled(Boolean.TRUE);
        service.edit(user);
        return "login";
    }

    public String userInfo() {
        user = service.getUserInfo(loginController.getUser().getUsername());
        return "/profile";
    }

    public String editUserInfo() {
        user = service.getUserInfo(loginController.getUser().getUsername());
        return "editProfile";
    }

    public String updateUserInfo() {
        Long userId = loginController.getUser().getId();
        System.out.println(userId + "++++++");
        Users user = service.find(userId);
        user.setEnabled(Boolean.TRUE);
        service.edit(user);
        return "home";

    }

    public String changePassword() {
        return "";
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

    public List<Skill> getSkillsValue() {
        return skillsValue;
    }

    public void setSkillsValue(List<Skill> skillsValue) {
        this.skillsValue = skillsValue;
    }

    public void setSelectedSkills(List<String> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    public List<String> getSelectedSkills() {
        return selectedSkills;
    }

    public Users getSpUser() {
        return spUser;
    }

    public void setSpUser(Users spUser) {
        this.spUser = spUser;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public List<Users> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<Users> activeUsers) {
        this.activeUsers = activeUsers;
    }    
}
