package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
    @UniqueConstraint(columnNames = {"idMenu", "idMenuPai", "idUsuario", "ativo"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenusUsuariosTO.findAll", query = "SELECT m FROM MenusUsuariosTO m")})
public class MenusUsuariosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character ativo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int ordem;
    @JoinColumn(name = "idMenu", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MenusTO idMenu;
    @OrderBy("ordem ASC")
    @OneToMany(mappedBy = "idMenuPai", fetch = FetchType.LAZY)
    private List<MenusUsuariosTO> menusUsuariosTOList;
    @JoinColumn(name = "idMenuPai", referencedColumnName = "idMenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenusUsuariosTO idMenuPai;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuariosTO idUsuario;

    public MenusUsuariosTO() {
    }

    public MenusUsuariosTO(Integer id) {
        this.id = id;
    }

    public MenusUsuariosTO(Integer id, Character ativo, int ordem) {
        this.id = id;
        this.ativo = ativo;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public MenusTO getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(MenusTO idMenu) {
        this.idMenu = idMenu;
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

    public UsuariosTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenusUsuariosTO)) {
            return false;
        }
        MenusUsuariosTO other = (MenusUsuariosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.MenusUsuariosTO[ id=" + id + " ]";
    }

}
