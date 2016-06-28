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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "idiomas", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdiomasTO.findAll", query = "SELECT i FROM IdiomasTO i")})
public class IdiomasTO implements Serializable {

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
    private String idioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String local;
    @OneToMany(mappedBy = "idIdioma", fetch = FetchType.LAZY)
    private List<MensagensTO> mensagensTOList;

    public IdiomasTO() {
    }

    public IdiomasTO(Integer id) {
        this.id = id;
    }

    public IdiomasTO(Integer id, String idioma, String local) {
        this.id = id;
        this.idioma = idioma;
        this.local = local;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @XmlTransient
    public List<MensagensTO> getMensagensTOList() {
        return mensagensTOList;
    }

    public void setMensagensTOList(List<MensagensTO> mensagensTOList) {
        this.mensagensTOList = mensagensTOList;
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
        if (!(object instanceof IdiomasTO)) {
            return false;
        }
        IdiomasTO other = (IdiomasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.IdiomasTO[ id=" + id + " ]";
    }

}
