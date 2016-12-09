/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "Paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
    , @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente")
    , @NamedQuery(name = "Paciente.findByFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo")
    , @NamedQuery(name = "Paciente.findByEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Paciente.findByNumeroExterior", query = "SELECT p FROM Paciente p WHERE p.numeroExterior = :numeroExterior")
    , @NamedQuery(name = "Paciente.findByNumeroInterior", query = "SELECT p FROM Paciente p WHERE p.numeroInterior = :numeroInterior")
    , @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Paciente.findByCelular", query = "SELECT p FROM Paciente p WHERE p.celular = :celular")
    , @NamedQuery(name = "Paciente.findByEmail", query = "SELECT p FROM Paciente p WHERE p.email = :email")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPaciente")
    private Integer idPaciente;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "curp")
    private String curp;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "rfc")
    private String rfc;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "tipoDeSangre")
    private String tipoDeSangre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estadoCivil")
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "religion")
    private String religion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ocupacion")
    private String ocupacion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "numeroExterior")
    private String numeroExterior;
    @Size(max = 9)
    @Column(name = "numeroInterior")
    private String numeroInterior;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contactoEmergencia")
    private String contactoEmergencia;
    @JoinColumn(name = "idDireccion", referencedColumnName = "codigoPostal")
    @ManyToOne(optional = false)
    private Direccion idDireccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Consulta> consultaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Medicamento> medicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Antescedentes> antescedentesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Imagenes> imagenesList;

    public Paciente() {
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(Integer idPaciente, String curp, String rfc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String tipoDeSangre, String sexo, String estadoCivil, String origen, String religion, String ocupacion, String calle, String numeroExterior, String contactoEmergencia) {
        this.idPaciente = idPaciente;
        this.curp = curp;
        this.rfc = rfc;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDeSangre = tipoDeSangre;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.origen = origen;
        this.religion = religion;
        this.ocupacion = ocupacion;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.contactoEmergencia = contactoEmergencia;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    @XmlTransient
    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    @XmlTransient
    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    @XmlTransient
    public List<Antescedentes> getAntescedentesList() {
        return antescedentesList;
    }

    public void setAntescedentesList(List<Antescedentes> antescedentesList) {
        this.antescedentesList = antescedentesList;
    }

    @XmlTransient
    public List<Imagenes> getImagenesList() {
        return imagenesList;
    }

    public void setImagenesList(List<Imagenes> imagenesList) {
        this.imagenesList = imagenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Paciente[ idPaciente=" + idPaciente + " ]";
    }
    
}
