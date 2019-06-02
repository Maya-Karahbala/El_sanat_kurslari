/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "KURSIYER_KURSLAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KursiyerKurslar.findAll", query = "SELECT k FROM KursiyerKurslar k")
    , @NamedQuery(name = "KursiyerKurslar.findByAcilanKursId", query = "SELECT k FROM KursiyerKurslar k WHERE k.kursiyerKurslarPK.acilanKursId = :acilanKursId")
    , @NamedQuery(name = "KursiyerKurslar.findByKursiyerId", query = "SELECT k FROM KursiyerKurslar k WHERE k.kursiyerKurslarPK.kursiyerId = :kursiyerId")
    , @NamedQuery(name = "KursiyerKurslar.findByOzelFiyat", query = "SELECT k FROM KursiyerKurslar k WHERE k.ozelFiyat = :ozelFiyat")
    , @NamedQuery(name = "KursiyerKurslar.findByOdemeTipi", query = "SELECT k FROM KursiyerKurslar k WHERE k.odemeTipi = :odemeTipi")
    , @NamedQuery(name = "KursiyerKurslar.findByKayitTarih", query = "SELECT k FROM KursiyerKurslar k WHERE k.kayitTarih = :kayitTarih")})
public class KursiyerKurslar implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KursiyerKurslarPK kursiyerKurslarPK;
    @Column(name = "OZEL_FIYAT")
    private BigInteger ozelFiyat;
    @Column(name = "ODEME_TIPI")
    private String odemeTipi;
    @Column(name = "KAYIT_TARIH")
    private String kayitTarih;
    @JoinColumn(name = "ACILAN_KURS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AcilanKurs acilanKurs;
    @JoinColumn(name = "KURSIYER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kursiyer kursiyer;

    public KursiyerKurslar() {
    }

    public KursiyerKurslar(KursiyerKurslarPK kursiyerKurslarPK) {
        this.kursiyerKurslarPK = kursiyerKurslarPK;
    }

    public KursiyerKurslar(BigInteger acilanKursId, BigInteger kursiyerId) {
        this.kursiyerKurslarPK = new KursiyerKurslarPK(acilanKursId, kursiyerId);
    }

    public KursiyerKurslarPK getKursiyerKurslarPK() {
        return kursiyerKurslarPK;
    }

    public void setKursiyerKurslarPK(KursiyerKurslarPK kursiyerKurslarPK) {
        this.kursiyerKurslarPK = kursiyerKurslarPK;
    }

    public BigInteger getOzelFiyat() {
        return ozelFiyat;
    }

    public void setOzelFiyat(BigInteger ozelFiyat) {
        this.ozelFiyat = ozelFiyat;
    }

    public String getOdemeTipi() {
        return odemeTipi;
    }

    public void setOdemeTipi(String odemeTipi) {
        this.odemeTipi = odemeTipi;
    }

    public String getKayitTarih() {
        return kayitTarih;
    }

    public void setKayitTarih(String kayitTarih) {
        this.kayitTarih = kayitTarih;
    }

    public AcilanKurs getAcilanKurs() {
        return acilanKurs;
    }

    public void setAcilanKurs(AcilanKurs acilanKurs) {
        this.acilanKurs = acilanKurs;
    }

    public Kursiyer getKursiyer() {
        return kursiyer;
    }

    public void setKursiyer(Kursiyer kursiyer) {
        this.kursiyer = kursiyer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kursiyerKurslarPK != null ? kursiyerKurslarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KursiyerKurslar)) {
            return false;
        }
        KursiyerKurslar other = (KursiyerKurslar) object;
        if ((this.kursiyerKurslarPK == null && other.kursiyerKurslarPK != null) || (this.kursiyerKurslarPK != null && !this.kursiyerKurslarPK.equals(other.kursiyerKurslarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.KursiyerKurslar[ kursiyerKurslarPK=" + kursiyerKurslarPK + " ]";
    }

    public KursiyerKurslar(KursiyerKurslarPK kursiyerKurslarPK, BigInteger ozelFiyat, String odemeTipi, String kayitTarih, AcilanKurs acilanKurs, Kursiyer kursiyer) {
        this.kursiyerKurslarPK = kursiyerKurslarPK;
        this.ozelFiyat = ozelFiyat;
        this.odemeTipi = odemeTipi;
        this.kayitTarih = kayitTarih;
        this.acilanKurs = acilanKurs;
        this.kursiyer = kursiyer;
    }
    
    
}
