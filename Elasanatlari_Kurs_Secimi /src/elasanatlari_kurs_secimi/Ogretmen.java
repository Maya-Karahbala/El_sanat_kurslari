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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "OGRETMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ogretmen.findAll", query = "SELECT o FROM Ogretmen o")
    , @NamedQuery(name = "Ogretmen.findById", query = "SELECT o FROM Ogretmen o WHERE o.id = :id")
    , @NamedQuery(name = "Ogretmen.findByAdi", query = "SELECT o FROM Ogretmen o WHERE o.adi = :adi")
    , @NamedQuery(name = "Ogretmen.findBySoyadi", query = "SELECT o FROM Ogretmen o WHERE o.soyadi = :soyadi")
    , @NamedQuery(name = "Ogretmen.findByEvAdresi", query = "SELECT o FROM Ogretmen o WHERE o.evAdresi = :evAdresi")
    , @NamedQuery(name = "Ogretmen.findByCepTelefonu", query = "SELECT o FROM Ogretmen o WHERE o.cepTelefonu = :cepTelefonu")
    , @NamedQuery(name = "Ogretmen.findByEvTelefonu", query = "SELECT o FROM Ogretmen o WHERE o.evTelefonu = :evTelefonu")
    , @NamedQuery(name = "Ogretmen.findByEmail", query = "SELECT o FROM Ogretmen o WHERE o.email = :email")
    , @NamedQuery(name = "Ogretmen.findByDurum", query = "SELECT o FROM Ogretmen o WHERE o.durum = :durum")})
public class Ogretmen implements Serializable {

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
    @Column(name = "DURUM")
    private String durum;
    @JoinTable(name = "OGRETMEN_KURSLAR", joinColumns = {
        @JoinColumn(name = "OGRETMEN_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "KURS_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Kurs> kursCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ogretmenId")
    private Collection<OgretmenDetay> ogretmenDetayCollection;

    public Ogretmen() {
    }

    public Ogretmen(BigDecimal id) {
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

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    @XmlTransient
    public Collection<Kurs> getKursCollection() {
        return kursCollection;
    }

    public void setKursCollection(Collection<Kurs> kursCollection) {
        this.kursCollection = kursCollection;
    }

    @XmlTransient
    public Collection<OgretmenDetay> getOgretmenDetayCollection() {
        return ogretmenDetayCollection;
    }

    public void setOgretmenDetayCollection(Collection<OgretmenDetay> ogretmenDetayCollection) {
        this.ogretmenDetayCollection = ogretmenDetayCollection;
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
        if (!(object instanceof Ogretmen)) {
            return false;
        }
        Ogretmen other = (Ogretmen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.Ogretmen[ id=" + id + " ]";
    }
    
}
