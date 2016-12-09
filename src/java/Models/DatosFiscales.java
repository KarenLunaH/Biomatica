/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "DatosFiscales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosFiscales.findAll", query = "SELECT d FROM DatosFiscales d")
    , @NamedQuery(name = "DatosFiscales.findByIdDatosFiscales", query = "SELECT d FROM DatosFiscales d WHERE d.idDatosFiscales = :idDatosFiscales")
    , @NamedQuery(name = "DatosFiscales.findByNumeroExterior", query = "SELECT d FROM DatosFiscales d WHERE d.numeroExterior = :numeroExterior")
    , @NamedQuery(name = "DatosFiscales.findByNumeroInterior", query = "SELECT d FROM DatosFiscales d WHERE d.numeroInterior = :numeroInterior")})
public class DatosFiscales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDatosFiscales")
    private Integer idDatosFiscales;
    @Lob
    @Size(max = 65535)
    @Column(name = "rfc")
    private String rfc;
    @Lob
    @Size(max = 65535)
    @Column(name = "tipoDePersona")
    private String tipoDePersona;
    @Lob
    @Size(max = 65535)
    @Column(name = "regimenFiscal")
    private String regimenFiscal;
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
    @JoinColumn(name = "idDoctor", referencedColumnName = "idDoctor")
    @ManyToOne(optional = false)
    private Doctor idDoctor;
    @JoinColumn(name = "idDireccion", referencedColumnName = "codigoPostal")
    @ManyToOne(optional = false)
    private Direccion idDireccion;

    public DatosFiscales() {
    }

    public DatosFiscales(Integer idDatosFiscales) {
        this.idDatosFiscales = idDatosFiscales;
    }

    public DatosFiscales(Integer idDatosFiscales, String calle, String numeroExterior) {
        this.idDatosFiscales = idDatosFiscales;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
    }

    public Integer getIdDatosFiscales() {
        return idDatosFiscales;
    }

    public void setIdDatosFiscales(Integer idDatosFiscales) {
        this.idDatosFiscales = idDatosFiscales;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTipoDePersona() {
        return tipoDePersona;
    }

    public void setTipoDePersona(String tipoDePersona) {
        this.tipoDePersona = tipoDePersona;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
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

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosFiscales != null ? idDatosFiscales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosFiscales)) {
            return false;
        }
        DatosFiscales other = (DatosFiscales) object;
        if ((this.idDatosFiscales == null && other.idDatosFiscales != null) || (this.idDatosFiscales != null && !this.idDatosFiscales.equals(other.idDatosFiscales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DatosFiscales[ idDatosFiscales=" + idDatosFiscales + " ]";
    }
    
}
