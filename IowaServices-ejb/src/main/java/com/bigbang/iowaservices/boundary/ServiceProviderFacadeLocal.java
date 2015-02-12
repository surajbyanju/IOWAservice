/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.ServiceProvider;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dell
 */
@Local
public interface ServiceProviderFacadeLocal {

    void create(ServiceProvider serviceProvider);

    void edit(ServiceProvider serviceProvider);

    void remove(ServiceProvider serviceProvider);

    ServiceProvider find(Object id);

    List<ServiceProvider> findAll();

    List<ServiceProvider> findRange(int[] range);

    int count();
    
    List<ServiceProvider> searchServiceProvider(String searchString);
    
}
