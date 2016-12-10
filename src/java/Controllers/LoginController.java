/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Ejbs.DoctorFacadeLocal;
import Models.Doctor;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KarenLunaH
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable{

    /*
    *Etiqueta SessionScoped, indica que este controlador podrá
    *ser visto/usado en todas las vistas
    */
    private Doctor doctor = null;
    private String email, psw;
    
    
    
    @EJB
    private DoctorFacadeLocal doctorEjb;
    
    
    public void login() throws Exception {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        try {
            Doctor aux = doctorEjb.login(email, psw);
            if (aux != null) {
                this.doctor = aux;
                this.email = this.psw = "";
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No tienes acceso al sistema."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario o contraseña inválidos"));
        }
    }

    public void logout() throws IOException {
        this.doctor = null;
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath);
    }
    
    public boolean regresaLogeado(){
        return (this.doctor!=null)? true:false;
    }
    
    public void loggedInRedirect() throws IOException {
        if (!this.regresaLogeado()) {
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/views/Login.xhtml");
        }
    }
    
    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the psw
     */
    public String getPsw() {
        return psw;
    }

    /**
     * @param psw the psw to set
     */
    public void setPsw(String psw) {
        this.psw = psw;
    }

}
