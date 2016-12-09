/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.DatosFiscales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KarenLunaH
 */
@Local
public interface DatosFiscalesFacadeLocal {

    void create(DatosFiscales datosFiscales);

    void edit(DatosFiscales datosFiscales);

    void remove(DatosFiscales datosFiscales);

    DatosFiscales find(Object id);

    List<DatosFiscales> findAll();

    List<DatosFiscales> findRange(int[] range);

    int count();
    
}
