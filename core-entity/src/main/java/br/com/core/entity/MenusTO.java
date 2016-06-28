package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "menus", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"icone"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenusTO.findAll", query = "SELECT m FROM MenusTO m")})
public class MenusTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String nome;
    @Size(max = 45)
    @Column(length = 45)
    private String icone;
    @Size(max = 45)
    @Column(length = 45)
    private String acao;
    @Size(max = 45)
    @Column(length = 45)
    private String link;
    private Character tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu", fetch = FetchType.LAZY)
    private List<MenusUsuariosTO> menusUsuariosTOList;

    public MenusTO() {
    }

    public MenusTO(Integer id) {
        this.id = id;
    }

    public MenusTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<MenusUsuariosTO> getMenusUsuariosTOList() {
        return menusUsuariosTOList;
    }

    public void setMenusUsuariosTOList(List<MenusUsuariosTO> menusUsuariosTOList) {
        this.menusUsuariosTOList = menusUsuariosTOList;
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
        if (!(object instanceof MenusTO)) {
            return false;
        }
        MenusTO other = (MenusTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.MenusTO[ id=" + id + " ]";
    }

}
