/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "KURSIYER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kursiyer.findAll", query = "SELECT k FROM Kursiyer k")
    , @NamedQuery(name = "Kursiyer.findById", query = "SELECT k FROM Kursiyer k WHERE k.id = :id")
    , @NamedQuery(name = "Kursiyer.findByAdi", query = "SELECT k FROM Kursiyer k WHERE k.adi = :adi")
    , @NamedQuery(name = "Kursiyer.findBySoyadi", query = "SELECT k FROM Kursiyer k WHERE k.soyadi = :soyadi")
    , @NamedQuery(name = "Kursiyer.findByEvAdresi", query = "SELECT k FROM Kursiyer k WHERE k.evAdresi = :evAdresi")
    , @NamedQuery(name = "Kursiyer.findByCepTelefonu", query = "SELECT k FROM Kursiyer k WHERE k.cepTelefonu = :cepTelefonu")
    , @NamedQuery(name = "Kursiyer.findByEvTelefonu", query = "SELECT k FROM Kursiyer k WHERE k.evTelefonu = :evTelefonu")
    , @NamedQuery(name = "Kursiyer.findByEmail", query = "SELECT k FROM Kursiyer k WHERE k.email = :email")
    , @NamedQuery(name = "Kursiyer.findByYas", query = "SELECT k FROM Kursiyer k WHERE k.yas = :yas")})
public class Kursiyer implements Serializable {

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
    @Column(name = "YAS")
    private BigInteger yas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kursiyerId")
    private Collection<Kupon> kuponCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kursiyer")
    private Collection<KursiyerKurslar> kursiyerKurslarCollection;

    public Kursiyer() {
    }

    public Kursiyer(BigDecimal id) {
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

    public BigInteger getYas() {
        return yas;
    }

    public void setYas(BigInteger yas) {
        this.yas = yas;
    }

    @XmlTransient
    public Collection<Kupon> getKuponCollection() {
        return kuponCollection;
    }

    public void setKuponCollection(Collection<Kupon> kuponCollection) {
        this.kuponCollection = kuponCollection;
    }

    @XmlTransient
    public Collection<KursiyerKurslar> getKursiyerKurslarCollection() {
        return kursiyerKurslarCollection;
    }

    public void setKursiyerKurslarCollection(Collection<KursiyerKurslar> kursiyerKurslarCollection) {
        this.kursiyerKurslarCollection = kursiyerKurslarCollection;
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
        if (!(object instanceof Kursiyer)) {
            return false;
        }
        Kursiyer other = (Kursiyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.Kursiyer[ id=" + id + " ]";
    }
    
}
