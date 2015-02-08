/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.Listeners;

import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import javax.ejb.EJB;
import javax.persistence.PostPersist;

/**
 *
 * @author dell
 */
public class UserListener {
   
    @PostPersist
    void triggerEmail(Users o){
         String messageLink = "http://localhost:8080/IowaServices-web/validateUser.jsf?userId=" + o.getId();
        System.out.println("here++++ "+messageLink);
    }
}
