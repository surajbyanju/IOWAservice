/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.ServiceRequest;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dell
 */
@Local
public interface ServiceRequestFacadeLocal {

    void create(ServiceRequest serviceRequest);

    void edit(ServiceRequest serviceRequest);

    void remove(ServiceRequest serviceRequest);

    ServiceRequest find(Object id);

    List<ServiceRequest> findAll();

    List<ServiceRequest> findRange(int[] range);

    int count();
    
}
