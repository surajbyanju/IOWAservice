/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.entities.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author dell
 */
@Named(value = "searchProvidersController")
@Dependent
public class SearchProvidersController {

    /**
     * Creates a new instance of SearchProvidersController
     */
    public SearchProvidersController() {
    }
    
    public List<Users> findAllServiceProviders(){
        return null;
    
    } 
    
}
