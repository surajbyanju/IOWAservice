/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Rejina
 */
@ManagedBean
@RequestScoped
public class ComponentValidator {
    /**
     * Creates a new instance of ComponentValidator
     */
    public ComponentValidator() {
    }
 
    public void validateTextInput(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String componentValue = (String)value;
        String id = component.getId(); //component name
        String msg = "invalid" + " " + id;
        //System.out.print("component name +++++++"   +  id);
        if(componentValue.isEmpty()){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, id+"filed cannot be Empty", id+"filed cannot be Empty"));
        }
        else if(componentValue.matches("/^[A-z]+$/")){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
    
    public void validatePasswordField(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String componentValue = (String)value;
        String id = component.getId(); //component name
        String msg = "invalid" + " " + id;
        //System.out.print("component name +++++++"   +  id);
        if(componentValue.isEmpty()){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, id+"filed cannot be Empty", id+"filed cannot be Empty"));
        }
    }
    
    public void validateContactNumber(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String componentValue = (String)value;
        String id = component.getId(); //component name
        String msg = "invalid" + " " + id;
        
        
        System.out.print("component name +++++++"   +  id);
        if(componentValue.isEmpty()){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, id+"filed cannot be Empty", id+"filed cannot be Empty"));

        }else if(componentValue.length() > 10 ){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            
        }
    }
    
    
    public void validAddress(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        
        String componentValue = (String)value;
        String id = component.getId(); //component name
        String msg = "invalid" + " " + id;
        
        
        System.out.print("component name +++++++"   +  id);
        if(componentValue.isEmpty()){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, id+"filed cannot be Empty", id+"filed cannot be Empty"));

        }
    }
    
    
    public void validatePassword(ComponentSystemEvent event) {
 
	  FacesContext fc = FacesContext.getCurrentInstance();
 
	  UIComponent components = event.getComponent();
 
	  // get password
	  UIInput uiInputPassword = (UIInput) components.findComponent("password");
	  String password = uiInputPassword.getLocalValue() == null ? ""
		: uiInputPassword.getLocalValue().toString();
	  String passwordId = uiInputPassword.getClientId();
 
	  // get confirm password
	  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
	  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
		: uiInputConfirmPassword.getLocalValue().toString();
 
	  // Let required="true" do its job.
	  if (password.isEmpty() || confirmPassword.isEmpty()) {
		return;
	  }
 
	  if (!password.equals(confirmPassword)) {
 
		FacesMessage msg = new FacesMessage("Password must match confirm password");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(passwordId, msg);
		fc.renderResponse();
 
	  }
 
	}
    
}



