/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.validator;

import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Users;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Rejina
 */
@FacesValidator("com.bigbang.iowaservices.validator.usernameExistValidator")
public class UsernameExistValidator implements Validator{

    @EJB
    UsersFacadeLocal service;
    private Users user;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String username = (String)value;
        
        user = service.getUserInfo(username);
        if(user!= null){
            FacesMessage msg = 
				new FacesMessage("Username already exist.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
        }
    }
 
}
