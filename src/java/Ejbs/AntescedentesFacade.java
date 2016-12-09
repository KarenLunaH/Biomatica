/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Antescedentes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KarenLunaH
 */
@Stateless
public class AntescedentesFacade extends AbstractFacade<Antescedentes> implements AntescedentesFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntescedentesFacade() {
        super(Antescedentes.class);
    }
    
}
