/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.Skill;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dell
 */
@Local
public interface SkillFacadeLocal {

    void create(Skill skill);

    void edit(Skill skill);

    void remove(Skill skill);

    Skill find(Object id);

    List<Skill> findAll();

    List<Skill> findRange(int[] range);
    
    
    Skill findByCode(String code);

    int count();
    
}
