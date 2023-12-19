package dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "asistencia")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByCodiAsis", query = "SELECT a FROM Asistencia a WHERE a.codiAsis = :codiAsis"),
    @NamedQuery(name = "Asistencia.findByCodiPers", query = "SELECT a FROM Asistencia a WHERE a.codiPers = :codiPers"),
    @NamedQuery(name = "Asistencia.findByFechAsis", query = "SELECT a FROM Asistencia a WHERE a.fechAsis = :fechAsis"),
    @NamedQuery(name = "Asistencia.findByHoraAsisPers", query = "SELECT a FROM Asistencia a WHERE a.horaAsisPers = :horaAsisPers")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiAsis")
    private Integer codiAsis;
    @Basic(optional = false)
    @Column(name = "codiPers")
    private int codiPers;
    @Basic(optional = false)
    @Column(name = "fechAsis")
    @Temporal(TemporalType.DATE)
    private Date fechAsis;
    @Basic(optional = false)
    @Column(name = "horaAsisPers")
    @Temporal(TemporalType.TIME)
    private Date horaAsisPers;

    public Asistencia() {
    }

    public Asistencia(Integer codiAsis) {
        this.codiAsis = codiAsis;
    }

    public Asistencia(Integer codiAsis, int codiPers, Date fechAsis, Date horaAsisPers) {
        this.codiAsis = codiAsis;
        this.codiPers = codiPers;
        this.fechAsis = fechAsis;
        this.horaAsisPers = horaAsisPers;
    }

    public Integer getCodiAsis() {
        return codiAsis;
    }

    public void setCodiAsis(Integer codiAsis) {
        this.codiAsis = codiAsis;
    }

    public int getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(int codiPers) {
        this.codiPers = codiPers;
    }

    public Date getFechAsis() {
        return fechAsis;
    }

    public void setFechAsis(Date fechAsis) {
        this.fechAsis = fechAsis;
    }

    public Date getHoraAsisPers() {
        return horaAsisPers;
    }

    public void setHoraAsisPers(Date horaAsisPers) {
        this.horaAsisPers = horaAsisPers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiAsis != null ? codiAsis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.codiAsis == null && other.codiAsis != null) || (this.codiAsis != null && !this.codiAsis.equals(other.codiAsis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Asistencia[ codiAsis=" + codiAsis + " ]";
    }
    
}
