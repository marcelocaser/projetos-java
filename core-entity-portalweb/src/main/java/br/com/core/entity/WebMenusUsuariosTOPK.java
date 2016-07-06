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
public class WebMenusUsuariosTOPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idmenu;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idusuario;

    public WebMenusUsuariosTOPK() {
    }

    public WebMenusUsuariosTOPK(int idmenu, int idusuario) {
        this.idmenu = idmenu;
        this.idusuario = idusuario;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmenu;
        hash += (int) idusuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebMenusUsuariosTOPK)) {
            return false;
        }
        WebMenusUsuariosTOPK other = (WebMenusUsuariosTOPK) object;
        if (this.idmenu != other.idmenu) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.WebMenusUsuariosTOPK[ idmenu=" + idmenu + ", idusuario=" + idusuario + " ]";
    }

}
