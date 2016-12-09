/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Imagenes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KarenLunaH
 */
@Stateless
public class ImagenesFacade extends AbstractFacade<Imagenes> implements ImagenesFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagenesFacade() {
        super(Imagenes.class);
    }
    
}
