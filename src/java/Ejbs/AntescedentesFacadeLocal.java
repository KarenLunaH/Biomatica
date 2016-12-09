/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Antescedentes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KarenLunaH
 */
@Local
public interface AntescedentesFacadeLocal {

    void create(Antescedentes antescedentes);

    void edit(Antescedentes antescedentes);

    void remove(Antescedentes antescedentes);

    Antescedentes find(Object id);

    List<Antescedentes> findAll();

    List<Antescedentes> findRange(int[] range);

    int count();
    
}
