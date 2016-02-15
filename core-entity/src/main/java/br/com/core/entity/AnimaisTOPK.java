package br.com.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author marce
 */
@Embeddable
public class AnimaisTOPK implements Serializable {

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idRaca;

    public AnimaisTOPK() {
    }

    public AnimaisTOPK(Integer id, Integer idRaca) {
        this.id = id;
        this.idRaca = idRaca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Integer idRaca) {
        this.idRaca = idRaca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idRaca;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnimaisTOPK)) {
            return false;
        }
        AnimaisTOPK other = (AnimaisTOPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idRaca != other.idRaca) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.AnimaisTOPK[ id=" + id + ", idRaca=" + idRaca + " ]";
    }

}
