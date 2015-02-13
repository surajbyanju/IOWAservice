/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.converters;

import com.bigbang.iowaservices.boundary.ServiceProviderFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author dell
 */
@ManagedBean
public class ProviderConverter implements Converter{

    @EJB
    ServiceProviderFacadeLocal facadeLocal;
    /**
     * Creates a new instance of ProviderConverter
     */
    public ProviderConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        int id = Integer.parseInt(value);
        return facadeLocal.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Long id = ((ServiceProvider) value).getId();
        return (id != null) ? id.toString() : null;
    }
    
}
