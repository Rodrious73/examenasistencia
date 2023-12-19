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
import java.io.Serializable;

@Entity
@Table(name = "tipo")
@NamedQueries({
    @NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t"),
    @NamedQuery(name = "Tipo.findByCodiTipo", query = "SELECT t FROM Tipo t WHERE t.codiTipo = :codiTipo"),
    @NamedQuery(name = "Tipo.findByNombTipo", query = "SELECT t FROM Tipo t WHERE t.nombTipo = :nombTipo")})
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiTipo")
    private Integer codiTipo;
    @Basic(optional = false)
    @Column(name = "nombTipo")
    private String nombTipo;

    public Tipo() {
    }

    public Tipo(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public Tipo(Integer codiTipo, String nombTipo) {
        this.codiTipo = codiTipo;
        this.nombTipo = nombTipo;
    }

    public Integer getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public String getNombTipo() {
        return nombTipo;
    }

    public void setNombTipo(String nombTipo) {
        this.nombTipo = nombTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipo != null ? codiTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipo)) {
            return false;
        }
        Tipo other = (Tipo) object;
        if ((this.codiTipo == null && other.codiTipo != null) || (this.codiTipo != null && !this.codiTipo.equals(other.codiTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipo[ codiTipo=" + codiTipo + " ]";
    }
    
}
