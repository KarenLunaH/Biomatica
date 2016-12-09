/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Ejbs.DireccionFacadeLocal;
import Ejbs.PacienteFacadeLocal;
import Models.Direccion;
import Models.Paciente;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author KarenLunaH
 */

@Named
@ViewScoped
public class PacienteController implements Serializable{
    
    @EJB
    private PacienteFacadeLocal pacienteEjb;
    private Paciente paciente;
    private List<Paciente> pacientes;
    
    @EJB
    private DireccionFacadeLocal direccionEjb;
    
    @PostConstruct
    public void init(){
        this.paciente = new Paciente();
        this.pacientes = new ArrayList<Paciente>();
    }
    
    public void listar(){
        this.pacientes = this.pacienteEjb.findAll();
    }
    
    public void crear(){
        this.pacienteEjb.create(paciente);
        this.paciente = null;
        this.paciente = new Paciente();
    }
    
    public void limpiarformulario(){
        this.paciente = null;
        this.paciente = new Paciente();
    }
    
    public void findForEdit(Object id){
        this.paciente = this.pacienteEjb.find(id);
    }
    
    public void editar(){
        this.pacienteEjb.edit(paciente);
        this.limpiarformulario();
    }
    
    public void findDireccion(){
        int cp = this.paciente.getIdDireccion().getCodigoPostal();
        Direccion d = this.direccionEjb.find(cp);
        if(d != null){
            this.paciente.getIdDireccion().setColonia(d.getColonia());
            this.paciente.getIdDireccion().setMunicipio(d.getMunicipio());
            this.paciente.getIdDireccion().setEstado(d.getEstado());
        }
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    
    public void updateRFC(){
        String cu = "";
        for(int i = 0;i<this.paciente.getCurp().length() && i<10;i++){
            cu+=this.paciente.getCurp().toUpperCase().charAt(i);
        }
        this.paciente.setRfc(cu);
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    
    public void viewPage(Object id) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("idPaciente", id);
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/views/paciente/View.xhtml");
    }
    
    public void find(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Object id = sessionMap.get("idPaciente");
        this.paciente = this.pacienteEjb.find(id);
    }
}
