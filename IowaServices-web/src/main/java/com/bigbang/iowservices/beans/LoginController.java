/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Users;
import java.io.IOException;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;

/**
 *
 * @author dell
 */
@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController implements PhaseListener {

    @EJB
    UsersFacadeLocal userFacade;
    private Users user;
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     *
     * Redirects the login request directly to spring security check. Leave this
     * method as it is to properly support spring security.
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String doLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            user = userFacade.getUserInfo(auth.getName());
        }

        System.out.println("user+++ " + user.getUserInformation().getFirstName());
        return "protected";
    }

    public void afterPhase(PhaseEvent event) {
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     * 
     * Do something before rendering phase.
     */
    public void beforePhase(PhaseEvent event) {
        Exception e = (Exception) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException) {
            logger.debug("Found exception in session map: " + e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Username or password not valid.", "Username or password not valid"));
        }
    }

    public LoginController() {
        user = new Users();
        userFacade = new UsersFacade();
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     * 
     * In which phase you want to interfere?
     */
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void checkUser() throws IOException {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("user+++ "+user.getRole());
        if (null != user.getRole()) 
            switch (user.getRole()) {
            case "ROLE_ADMIN":
                ec.redirect(ec.getRequestContextPath() + "/admin/adminDashboard.jsf");
                break;
            case "ROLE_SP":
                ec.redirect(ec.getRequestContextPath() + "/serviceProvider/spDashboard.jsf");
                break;
                case "ROLE_USER":
                ec.redirect(ec.getRequestContextPath() + "/users/userDashboard.jsf");
                break;
        }
        
    }

    public String test() {
        return "login";
    }

    public Users getUser() {
        return user;
    }

}
