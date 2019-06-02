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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maya
 */
@Entity
@Table(name = "ACILAN_KURS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcilanKurs.findAll", query = "SELECT a FROM AcilanKurs a")
    , @NamedQuery(name = "AcilanKurs.findById", query = "SELECT a FROM AcilanKurs a WHERE a.id = :id")
    , @NamedQuery(name = "AcilanKurs.findByOgretmenId", query = "SELECT a FROM AcilanKurs a WHERE a.ogretmenId = :ogretmenId")
    , @NamedQuery(name = "AcilanKurs.findByStandFiyat", query = "SELECT a FROM AcilanKurs a WHERE a.standFiyat = :standFiyat")
    , @NamedQuery(name = "AcilanKurs.findByDerslik", query = "SELECT a FROM AcilanKurs a WHERE a.derslik = :derslik")
    , @NamedQuery(name = "AcilanKurs.findByZaman", query = "SELECT a FROM AcilanKurs a WHERE a.zaman = :zaman")
    , @NamedQuery(name = "AcilanKurs.findByKayitSayisi", query = "SELECT a FROM AcilanKurs a WHERE a.kayitSayisi = :kayitSayisi")
    , @NamedQuery(name = "AcilanKurs.findByBasTarih", query = "SELECT a FROM AcilanKurs a WHERE a.basTarih = :basTarih")
    , @NamedQuery(name = "AcilanKurs.findByBitisTarih", query = "SELECT a FROM AcilanKurs a WHERE a.bitisTarih = :bitisTarih")})
public class AcilanKurs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "OGRETMEN_ID")
    private BigInteger ogretmenId;
    @Column(name = "STAND_FIYAT")
    private BigInteger standFiyat;
    @Column(name = "DERSLIK")
    private String derslik;
    @Column(name = "ZAMAN")
    private String zaman;
    @Column(name = "KAYIT_SAYISI")
    private BigInteger kayitSayisi;
    @Column(name = "BAS_TARIH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date basTarih;
    @Column(name = "BITIS_TARIH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bitisTarih;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acilanKursId")
    private Collection<KursGunleri> kursGunleriCollection;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Kurs kursId;
    @JoinColumn(name = "PERSONEL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Personel1 personelId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acilanKurs")
    private Collection<KursiyerKurslar> kursiyerKurslarCollection;

    public AcilanKurs() {
    }

    public AcilanKurs(BigDecimal id) {
        this.id = id;
    }

    public AcilanKurs(BigDecimal id, BigInteger ogretmenId) {
        this.id = id;
        this.ogretmenId = ogretmenId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getOgretmenId() {
        return ogretmenId;
    }

    public void setOgretmenId(BigInteger ogretmenId) {
        this.ogretmenId = ogretmenId;
    }

    public BigInteger getStandFiyat() {
        return standFiyat;
    }

    public void setStandFiyat(BigInteger standFiyat) {
        this.standFiyat = standFiyat;
    }

    public String getDerslik() {
        return derslik;
    }

    public void setDerslik(String derslik) {
        this.derslik = derslik;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public BigInteger getKayitSayisi() {
        return kayitSayisi;
    }

    public void setKayitSayisi(BigInteger kayitSayisi) {
        this.kayitSayisi = kayitSayisi;
    }

    public Date getBasTarih() {
        return basTarih;
    }

    public void setBasTarih(Date basTarih) {
        this.basTarih = basTarih;
    }

    public Date getBitisTarih() {
        return bitisTarih;
    }

    public void setBitisTarih(Date bitisTarih) {
        this.bitisTarih = bitisTarih;
    }

    @XmlTransient
    public Collection<KursGunleri> getKursGunleriCollection() {
        return kursGunleriCollection;
    }

    public void setKursGunleriCollection(Collection<KursGunleri> kursGunleriCollection) {
        this.kursGunleriCollection = kursGunleriCollection;
    }

    public Kurs getKursId() {
        return kursId;
    }

    public void setKursId(Kurs kursId) {
        this.kursId = kursId;
    }

    public Personel1 getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Personel1 personelId) {
        this.personelId = personelId;
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
        if (!(object instanceof AcilanKurs)) {
            return false;
        }
        AcilanKurs other = (AcilanKurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.AcilanKurs[ id=" + id + " ]";
    }
    
}
