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
@Table(name = "KUPON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupon.findAll", query = "SELECT k FROM Kupon k")
    , @NamedQuery(name = "Kupon.findByKod", query = "SELECT k FROM Kupon k WHERE k.kod = :kod")
    , @NamedQuery(name = "Kupon.findByYuzdelik", query = "SELECT k FROM Kupon k WHERE k.yuzdelik = :yuzdelik")})
public class Kupon implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private BigDecimal kod;
    @Column(name = "YUZDELIK")
    private BigInteger yuzdelik;
    @JoinColumn(name = "KURSIYER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kursiyer kursiyerId;

    public Kupon() {
    }

    public Kupon(BigDecimal kod) {
        this.kod = kod;
    }

    public BigDecimal getKod() {
        return kod;
    }

    public void setKod(BigDecimal kod) {
        this.kod = kod;
    }

    public BigInteger getYuzdelik() {
        return yuzdelik;
    }

    public void setYuzdelik(BigInteger yuzdelik) {
        this.yuzdelik = yuzdelik;
    }

    public Kursiyer getKursiyerId() {
        return kursiyerId;
    }

    public void setKursiyerId(Kursiyer kursiyerId) {
        this.kursiyerId = kursiyerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupon)) {
            return false;
        }
        Kupon other = (Kupon) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elasanatlari_kurs_secimi.Kupon[ kod=" + kod + " ]";
    }
    
}
