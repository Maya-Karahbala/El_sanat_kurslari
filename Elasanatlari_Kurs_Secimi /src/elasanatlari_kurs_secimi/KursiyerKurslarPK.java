/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author maya
 */
@Embeddable
public class KursiyerKurslarPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ACILAN_KURS_ID")
    private BigInteger acilanKursId;
    @Basic(optional = false)
    @Column(name = "KURSIYER_ID")
    private BigInteger kursiyerId;

    public KursiyerKurslarPK() {
    }

    public KursiyerKurslarPK(BigInteger acilanKursId, BigInteger kursiyerId) {
        this.acilanKursId = acilanKursId;
        this.kursiyerId = kursiyerId;
    }

    public BigInteger getAcilanKursId() {
        return acilanKursId;
    }

    public void setAcilanKursId(BigInteger acilanKursId) {
        this.acilanKursId = acilanKursId;
    }

    public BigInteger getKursiyerId() {
        return kursiyerId;
    }

    public void setKursiyerId(BigInteger kursiyerId) {
        this.kursiyerId = kursiyerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acilanKursId != null ? acilanKursId.hashCode() : 0);
        hash += (kursiyerId != null ? kursiyerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KursiyerKurslarPK)) {
            return false;
        }
        KursiyerKurslarPK other = (KursiyerKurslarPK) object;
        if ((this.acilanKursId == null && other.acilanKursId != null) || (this.acilanKursId != null && !this.acilanKursId.equals(other.acilanKursId))) {
            return false;
        }
        if ((this.kursiyerId == null && other.kursiyerId != null) || (this.kursiyerId != null && !this.kursiyerId.equals(other.kursiyerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.KursiyerKurslarPK[ acilanKursId=" + acilanKursId + ", kursiyerId=" + kursiyerId + " ]";
    }
    
}
