/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maya
 */
@Entity
@Table(name = "OGRETMEN_DETAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OgretmenDetay.findAll", query = "SELECT o FROM OgretmenDetay o")
    , @NamedQuery(name = "OgretmenDetay.findById", query = "SELECT o FROM OgretmenDetay o WHERE o.id = :id")
    , @NamedQuery(name = "OgretmenDetay.findByMaasBasTarih", query = "SELECT o FROM OgretmenDetay o WHERE o.maasBasTarih = :maasBasTarih")
    , @NamedQuery(name = "OgretmenDetay.findByMaasBitisTarih", query = "SELECT o FROM OgretmenDetay o WHERE o.maasBitisTarih = :maasBitisTarih")
    , @NamedQuery(name = "OgretmenDetay.findByHSonuUcreti", query = "SELECT o FROM OgretmenDetay o WHERE o.hSonuUcreti = :hSonuUcreti")
    , @NamedQuery(name = "OgretmenDetay.findByHIciUcreti", query = "SELECT o FROM OgretmenDetay o WHERE o.hIciUcreti = :hIciUcreti")})
public class OgretmenDetay implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "MAAS_BAS_TARIH")
    private String maasBasTarih;
    @Column(name = "MAAS_BITIS_TARIH")
    private String maasBitisTarih;
    @Column(name = "H_SONU_UCRETI")
    private BigInteger hSonuUcreti;
    @Column(name = "H_ICI_UCRETI")
    private BigInteger hIciUcreti;
    @JoinColumn(name = "OGRETMEN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ogretmen ogretmenId;

    public OgretmenDetay() {
    }

    public OgretmenDetay(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMaasBasTarih() {
        return maasBasTarih;
    }

    public void setMaasBasTarih(String maasBasTarih) {
        this.maasBasTarih = maasBasTarih;
    }

    public String getMaasBitisTarih() {
        return maasBitisTarih;
    }

    public void setMaasBitisTarih(String maasBitisTarih) {
        this.maasBitisTarih = maasBitisTarih;
    }

    public BigInteger getHSonuUcreti() {
        return hSonuUcreti;
    }

    public void setHSonuUcreti(BigInteger hSonuUcreti) {
        this.hSonuUcreti = hSonuUcreti;
    }

    public BigInteger getHIciUcreti() {
        return hIciUcreti;
    }

    public void setHIciUcreti(BigInteger hIciUcreti) {
        this.hIciUcreti = hIciUcreti;
    }

    public Ogretmen getOgretmenId() {
        return ogretmenId;
    }

    public void setOgretmenId(Ogretmen ogretmenId) {
        this.ogretmenId = ogretmenId;
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
        if (!(object instanceof OgretmenDetay)) {
            return false;
        }
        OgretmenDetay other = (OgretmenDetay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.OgretmenDetay[ id=" + id + " ]";
    }
    
}
