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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "KURS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k")
    , @NamedQuery(name = "Kurs.findById", query = "SELECT k FROM Kurs k WHERE k.id = :id")
    , @NamedQuery(name = "Kurs.findByKursAdi", query = "SELECT k FROM Kurs k WHERE k.kursAdi = :kursAdi")
    , @NamedQuery(name = "Kurs.findByMaxOgrenci", query = "SELECT k FROM Kurs k WHERE k.maxOgrenci = :maxOgrenci")
    , @NamedQuery(name = "Kurs.findByMinOgrenci", query = "SELECT k FROM Kurs k WHERE k.minOgrenci = :minOgrenci")
    , @NamedQuery(name = "Kurs.findByHaftalikSaat", query = "SELECT k FROM Kurs k WHERE k.haftalikSaat = :haftalikSaat")
    , @NamedQuery(name = "Kurs.findByMinYas", query = "SELECT k FROM Kurs k WHERE k.minYas = :minYas")
    , @NamedQuery(name = "Kurs.findByMaxYas", query = "SELECT k FROM Kurs k WHERE k.maxYas = :maxYas")})
public class Kurs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "KURS_ADI")
    private String kursAdi;
    @Column(name = "MAX_OGRENCI")
    private BigInteger maxOgrenci;
    @Column(name = "MIN_OGRENCI")
    private BigInteger minOgrenci;
    @Column(name = "HAFTALIK_SAAT")
    private BigInteger haftalikSaat;
    @Column(name = "MIN_YAS")
    private BigInteger minYas;
    @Column(name = "MAX_YAS")
    private BigInteger maxYas;
    @ManyToMany(mappedBy = "kursCollection")
    private Collection<Ogretmen> ogretmenCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<AcilanKurs> acilanKursCollection;

    public Kurs() {
    }

    public Kurs(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getKursAdi() {
        return kursAdi;
    }

    public void setKursAdi(String kursAdi) {
        this.kursAdi = kursAdi;
    }

    public BigInteger getMaxOgrenci() {
        return maxOgrenci;
    }

    public void setMaxOgrenci(BigInteger maxOgrenci) {
        this.maxOgrenci = maxOgrenci;
    }

    public BigInteger getMinOgrenci() {
        return minOgrenci;
    }

    public void setMinOgrenci(BigInteger minOgrenci) {
        this.minOgrenci = minOgrenci;
    }

    public BigInteger getHaftalikSaat() {
        return haftalikSaat;
    }

    public void setHaftalikSaat(BigInteger haftalikSaat) {
        this.haftalikSaat = haftalikSaat;
    }

    public BigInteger getMinYas() {
        return minYas;
    }

    public void setMinYas(BigInteger minYas) {
        this.minYas = minYas;
    }

    public BigInteger getMaxYas() {
        return maxYas;
    }

    public void setMaxYas(BigInteger maxYas) {
        this.maxYas = maxYas;
    }

    @XmlTransient
    public Collection<Ogretmen> getOgretmenCollection() {
        return ogretmenCollection;
    }

    public void setOgretmenCollection(Collection<Ogretmen> ogretmenCollection) {
        this.ogretmenCollection = ogretmenCollection;
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
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.Kurs[ id=" + id + " ]";
    }
    
}
