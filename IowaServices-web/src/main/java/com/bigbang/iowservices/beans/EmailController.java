/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.services.EmailService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Rejina
 */
@Named(value = "emailController")
@RequestScoped
public class EmailController {

    private String email;
    private String message;

    
    @EJB
    EmailService emailService;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Creates a new instance of EmailController
     */
    public EmailController() {
    }
    
    public void sendEmail(){
         
	       System.out.println(message);
               System.out.println(email);
               
        String subject = "suggestion from guest user";    
        String msgBody = "Dear admin \n sender email: r " + email
                + ", \n\n You have a ms from cont! with message: \n"
                + message;
        emailService.serviceRequestEmail("iowaservice1@gmail.com", subject, msgBody);
	 
	}
    
}
