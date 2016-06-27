package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "menus_usuarios", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idMenuPai", "idMenuUsuario", "ativo", "ordem"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenusUsuariosTO.findAll", query = "SELECT m FROM MenusUsuariosTO m")})
public class MenusUsuariosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idMenu;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character ativo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int ordem;
    @JoinColumn(name = "idMenu", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private MenusTO menusTO;
    @OneToMany(mappedBy = "idMenuPai", fetch = FetchType.LAZY)
    private List<MenusUsuariosTO> menusUsuariosTOList;
    @JoinColumn(name = "idMenuPai", referencedColumnName = "idMenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenusUsuariosTO idMenuPai;
    @JoinColumn(name = "idMenuUsuario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuariosTO idMenuUsuario;

    public MenusUsuariosTO() {
    }

    public MenusUsuariosTO(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public MenusUsuariosTO(Integer idMenu, Character ativo, int ordem) {
        this.idMenu = idMenu;
        this.ativo = ativo;
        this.ordem = ordem;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public MenusTO getMenusTO() {
        return menusTO;
    }

    public void setMenusTO(MenusTO menusTO) {
        this.menusTO = menusTO;
    }

    @XmlTransient
    public List<MenusUsuariosTO> getMenusUsuariosTOList() {
        return menusUsuariosTOList;
    }

    public void setMenusUsuariosTOList(List<MenusUsuariosTO> menusUsuariosTOList) {
        this.menusUsuariosTOList = menusUsuariosTOList;
    }

    public MenusUsuariosTO getIdMenuPai() {
        return idMenuPai;
    }

    public void setIdMenuPai(MenusUsuariosTO idMenuPai) {
        this.idMenuPai = idMenuPai;
    }

    public UsuariosTO getIdMenuUsuario() {
        return idMenuUsuario;
    }

    public void setIdMenuUsuario(UsuariosTO idMenuUsuario) {
        this.idMenuUsuario = idMenuUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenusUsuariosTO)) {
            return false;
        }
        MenusUsuariosTO other = (MenusUsuariosTO) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.MenusUsuariosTO[ idMenu=" + idMenu + " ]";
    }

}
