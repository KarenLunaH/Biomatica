/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.DatosFiscales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KarenLunaH
 */
@Stateless
public class DatosFiscalesFacade extends AbstractFacade<DatosFiscales> implements DatosFiscalesFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosFiscalesFacade() {
        super(DatosFiscales.class);
    }
    
}
