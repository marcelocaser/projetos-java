package br.com.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "WEB_MENUS_USUARIOS", catalog = "", schema = "PORTALWEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebMenusUsuariosTO.findAll", query = "SELECT w FROM WebMenusUsuariosTO w")})
public class WebMenusUsuariosTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebMenusUsuariosTOPK webMenusUsuariosTOPK;
    private Integer idmenupai;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int ordem;

    public WebMenusUsuariosTO() {
    }

    public WebMenusUsuariosTO(WebMenusUsuariosTOPK webMenusUsuariosTOPK) {
        this.webMenusUsuariosTOPK = webMenusUsuariosTOPK;
    }

    public WebMenusUsuariosTO(WebMenusUsuariosTOPK webMenusUsuariosTOPK, int ordem) {
        this.webMenusUsuariosTOPK = webMenusUsuariosTOPK;
        this.ordem = ordem;
    }

    public WebMenusUsuariosTO(int idmenu, int idusuario) {
        this.webMenusUsuariosTOPK = new WebMenusUsuariosTOPK(idmenu, idusuario);
    }

    public WebMenusUsuariosTOPK getWebMenusUsuariosTOPK() {
        return webMenusUsuariosTOPK;
    }

    public void setWebMenusUsuariosTOPK(WebMenusUsuariosTOPK webMenusUsuariosTOPK) {
        this.webMenusUsuariosTOPK = webMenusUsuariosTOPK;
    }

    public Integer getIdmenupai() {
        return idmenupai;
    }

    public void setIdmenupai(Integer idmenupai) {
        this.idmenupai = idmenupai;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (webMenusUsuariosTOPK != null ? webMenusUsuariosTOPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebMenusUsuariosTO)) {
            return false;
        }
        WebMenusUsuariosTO other = (WebMenusUsuariosTO) object;
        if ((this.webMenusUsuariosTOPK == null && other.webMenusUsuariosTOPK != null) || (this.webMenusUsuariosTOPK != null && !this.webMenusUsuariosTOPK.equals(other.webMenusUsuariosTOPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.WebMenusUsuariosTO[ webMenusUsuariosTOPK=" + webMenusUsuariosTOPK + " ]";
    }

}
