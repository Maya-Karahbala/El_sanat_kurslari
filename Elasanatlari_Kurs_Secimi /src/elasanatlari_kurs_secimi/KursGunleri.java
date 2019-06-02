/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "KURS_GUNLERI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KursGunleri.findAll", query = "SELECT k FROM KursGunleri k")
    , @NamedQuery(name = "KursGunleri.findById", query = "SELECT k FROM KursGunleri k WHERE k.id = :id")
    , @NamedQuery(name = "KursGunleri.findByGun", query = "SELECT k FROM KursGunleri k WHERE k.gun = :gun")
    , @NamedQuery(name = "KursGunleri.findByBasSaati", query = "SELECT k FROM KursGunleri k WHERE k.basSaati = :basSaati")
    , @NamedQuery(name = "KursGunleri.findByBitisSaati", query = "SELECT k FROM KursGunleri k WHERE k.bitisSaati = :bitisSaati")})
public class KursGunleri implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "GUN")
    private String gun;
    @Column(name = "BAS_SAATI")
    private String basSaati;
    @Column(name = "BITIS_SAATI")
    private String bitisSaati;
    @JoinColumn(name = "ACILAN_KURS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private AcilanKurs acilanKursId;

    public KursGunleri() {
    }

    public KursGunleri(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getBasSaati() {
        return basSaati;
    }

    public void setBasSaati(String basSaati) {
        this.basSaati = basSaati;
    }

    public String getBitisSaati() {
        return bitisSaati;
    }

    public void setBitisSaati(String bitisSaati) {
        this.bitisSaati = bitisSaati;
    }

    public AcilanKurs getAcilanKursId() {
        return acilanKursId;
    }

    public void setAcilanKursId(AcilanKurs acilanKursId) {
        this.acilanKursId = acilanKursId;
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
        if (!(object instanceof KursGunleri)) {
            return false;
        }
        KursGunleri other = (KursGunleri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.KursGunleri[ id=" + id + " ]";
    }
    
}
