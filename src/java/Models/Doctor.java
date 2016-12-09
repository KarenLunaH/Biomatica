/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "Doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")
    , @NamedQuery(name = "Doctor.findByIdDoctor", query = "SELECT d FROM Doctor d WHERE d.idDoctor = :idDoctor")
    , @NamedQuery(name = "Doctor.findByPrefijo", query = "SELECT d FROM Doctor d WHERE d.prefijo = :prefijo")
    , @NamedQuery(name = "Doctor.findByNombres", query = "SELECT d FROM Doctor d WHERE d.nombres = :nombres")
    , @NamedQuery(name = "Doctor.findByApellidoPaterno", query = "SELECT d FROM Doctor d WHERE d.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Doctor.findByApellidoMaterno", query = "SELECT d FROM Doctor d WHERE d.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Doctor.findByEspecialidad", query = "SELECT d FROM Doctor d WHERE d.especialidad = :especialidad")
    , @NamedQuery(name = "Doctor.findByCedulaProfesional", query = "SELECT d FROM Doctor d WHERE d.cedulaProfesional = :cedulaProfesional")
    , @NamedQuery(name = "Doctor.findByEmail", query = "SELECT d FROM Doctor d WHERE d.email = :email")
    , @NamedQuery(name = "Doctor.findByTelefono", query = "SELECT d FROM Doctor d WHERE d.telefono = :telefono")
    , @NamedQuery(name = "Doctor.findByCelular", query = "SELECT d FROM Doctor d WHERE d.celular = :celular")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDoctor")
    private Integer idDoctor;
    @Size(max = 5)
    @Column(name = "prefijo")
    private String prefijo;
    @Size(max = 75)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 100)
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Size(max = 100)
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Size(max = 100)
    @Column(name = "especialidad")
    private String especialidad;
    @Size(max = 45)
    @Column(name = "cedulaProfesional")
    private String cedulaProfesional;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Lob
    @Size(max = 65535)
    @Column(name = "password")
    private String password;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 17)
    @Column(name = "celular")
    private String celular;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<DatosFiscales> datosFiscalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<Consulta> consultaList;

    public Doctor() {
    }

    public Doctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @XmlTransient
    public List<DatosFiscales> getDatosFiscalesList() {
        return datosFiscalesList;
    }

    public void setDatosFiscalesList(List<DatosFiscales> datosFiscalesList) {
        this.datosFiscalesList = datosFiscalesList;
    }

    @XmlTransient
    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoctor != null ? idDoctor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.idDoctor == null && other.idDoctor != null) || (this.idDoctor != null && !this.idDoctor.equals(other.idDoctor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Doctor[ idDoctor=" + idDoctor + " ]";
    }
    
}
