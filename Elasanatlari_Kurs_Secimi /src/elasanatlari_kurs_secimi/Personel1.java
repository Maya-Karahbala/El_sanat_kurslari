/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maya
 */
@Entity
@Table(name = "PERSONEL1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personel1.findAll", query = "SELECT p FROM Personel1 p")
    , @NamedQuery(name = "Personel1.findById", query = "SELECT p FROM Personel1 p WHERE p.id = :id")
    , @NamedQuery(name = "Personel1.findByAdi", query = "SELECT p FROM Personel1 p WHERE p.adi = :adi")
    , @NamedQuery(name = "Personel1.findBySoyadi", query = "SELECT p FROM Personel1 p WHERE p.soyadi = :soyadi")
    , @NamedQuery(name = "Personel1.findByEvAdresi", query = "SELECT p FROM Personel1 p WHERE p.evAdresi = :evAdresi")
    , @NamedQuery(name = "Personel1.findByCepTelefonu", query = "SELECT p FROM Personel1 p WHERE p.cepTelefonu = :cepTelefonu")
    , @NamedQuery(name = "Personel1.findByEvTelefonu", query = "SELECT p FROM Personel1 p WHERE p.evTelefonu = :evTelefonu")
    , @NamedQuery(name = "Personel1.findByEmail", query = "SELECT p FROM Personel1 p WHERE p.email = :email")})
public class Personel1 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ADI")
    private String adi;
    @Column(name = "SOYADI")
    private String soyadi;
    @Column(name = "EV_ADRESI")
    private String evAdresi;
    @Column(name = "CEP_TELEFONU")
    private Long cepTelefonu;
    @Column(name = "EV_TELEFONU")
    private Long evTelefonu;
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy = "personelId")
    private Collection<AcilanKurs> acilanKursCollection;

    public Personel1() {
    }

    public Personel1(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEvAdresi() {
        return evAdresi;
    }

    public void setEvAdresi(String evAdresi) {
        this.evAdresi = evAdresi;
    }

    public Long getCepTelefonu() {
        return cepTelefonu;
    }

    public void setCepTelefonu(Long cepTelefonu) {
        this.cepTelefonu = cepTelefonu;
    }

    public Long getEvTelefonu() {
        return evTelefonu;
    }

    public void setEvTelefonu(Long evTelefonu) {
        this.evTelefonu = evTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<AcilanKurs> getAcilanKursCollection() {
        return acilanKursCollection;
    }

    public void setAcilanKursCollection(Collection<AcilanKurs> acilanKursCollection) {
        this.acilanKursCollection = acilanKursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personel1)) {
            return false;
        }
        Personel1 other = (Personel1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.Personel1[ id=" + id + " ]";
    }
    
}
