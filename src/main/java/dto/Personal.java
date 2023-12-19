package dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "personal")
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByCodiPers", query = "SELECT p FROM Personal p WHERE p.codiPers = :codiPers"),
    @NamedQuery(name = "Personal.findByAppaPers", query = "SELECT p FROM Personal p WHERE p.appaPers = :appaPers"),
    @NamedQuery(name = "Personal.findByApmaPers", query = "SELECT p FROM Personal p WHERE p.apmaPers = :apmaPers"),
    @NamedQuery(name = "Personal.findByNombPers", query = "SELECT p FROM Personal p WHERE p.nombPers = :nombPers"),
    @NamedQuery(name = "Personal.findByPesoPers", query = "SELECT p FROM Personal p WHERE p.pesoPers = :pesoPers"),
    @NamedQuery(name = "Personal.findByFechNaciPers", query = "SELECT p FROM Personal p WHERE p.fechNaciPers = :fechNaciPers"),
    @NamedQuery(name = "Personal.findByLogiPers", query = "SELECT p FROM Personal p WHERE p.logiPers = :logiPers"),
    @NamedQuery(name = "Personal.iniciarSesion", query = "SELECT p FROM Personal p WHERE p.logiPers = :logiPers AND p.passPers = :passPers AND p.codiTipo = :codiTipo"),
    @NamedQuery(name = "Personal.findByPassPers", query = "SELECT p FROM Personal p WHERE p.passPers = :passPers"),
    @NamedQuery(name = "Personal.findByCodiTipo", query = "SELECT p FROM Personal p WHERE p.codiTipo = :codiTipo")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codiPers")
    private Integer codiPers;
    @Basic(optional = false)
    @Column(name = "appaPers")
    private String appaPers;
    @Basic(optional = false)
    @Column(name = "apmaPers")
    private String apmaPers;
    @Basic(optional = false)
    @Column(name = "nombPers")
    private String nombPers;
    @Basic(optional = false)
    @Column(name = "pesoPers")
    private double pesoPers;
    @Basic(optional = false)
    @Column(name = "fechNaciPers")
    @Temporal(TemporalType.DATE)
    private Date fechNaciPers;
    @Basic(optional = false)
    @Column(name = "logiPers")
    private String logiPers;
    @Basic(optional = false)
    @Column(name = "passPers")
    private String passPers;
    @Basic(optional = false)
    @Column(name = "codiTipo")
    private int codiTipo;

    public Personal() {
    }

    public Personal(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Personal(Integer codiPers, String appaPers, String apmaPers, String nombPers, double pesoPers, Date fechNaciPers, String logiPers, String passPers, int codiTipo) {
        this.codiPers = codiPers;
        this.appaPers = appaPers;
        this.apmaPers = apmaPers;
        this.nombPers = nombPers;
        this.pesoPers = pesoPers;
        this.fechNaciPers = fechNaciPers;
        this.logiPers = logiPers;
        this.passPers = passPers;
        this.codiTipo = codiTipo;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public String getAppaPers() {
        return appaPers;
    }

    public void setAppaPers(String appaPers) {
        this.appaPers = appaPers;
    }

    public String getApmaPers() {
        return apmaPers;
    }

    public void setApmaPers(String apmaPers) {
        this.apmaPers = apmaPers;
    }

    public String getNombPers() {
        return nombPers;
    }

    public void setNombPers(String nombPers) {
        this.nombPers = nombPers;
    }

    public double getPesoPers() {
        return pesoPers;
    }

    public void setPesoPers(double pesoPers) {
        this.pesoPers = pesoPers;
    }

    public Date getFechNaciPers() {
        return fechNaciPers;
    }

    public void setFechNaciPers(Date fechNaciPers) {
        this.fechNaciPers = fechNaciPers;
    }

    public String getLogiPers() {
        return logiPers;
    }

    public void setLogiPers(String logiPers) {
        this.logiPers = logiPers;
    }

    public String getPassPers() {
        return passPers;
    }

    public void setPassPers(String passPers) {
        this.passPers = passPers;
    }

    public int getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(int codiTipo) {
        this.codiTipo = codiTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPers != null ? codiPers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.codiPers == null && other.codiPers != null) || (this.codiPers != null && !this.codiPers.equals(other.codiPers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Personal[ codiPers=" + codiPers + " ]";
    }
    
}
