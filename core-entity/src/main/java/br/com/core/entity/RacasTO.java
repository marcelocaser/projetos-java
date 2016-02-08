package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "racas", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome", "idEspecie"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RacasTO.findAll", query = "SELECT r FROM RacasTO r")})
public class RacasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String nome;
    @Size(max = 100)
    @Column(length = 100)
    private String detalhes;
    @Lob
    private byte[] foto;
    @JoinColumn(name = "idEspecie", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EspeciesTO idEspecie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "racasTO")
    private List<AnimaisTO> animaisTOList;

    public RacasTO() {
    }

    public RacasTO(Integer id) {
        this.id = id;
    }

    public RacasTO(Integer id, String nome) {
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public EspeciesTO getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(EspeciesTO idEspecie) {
        this.idEspecie = idEspecie;
    }

    @XmlTransient
    public List<AnimaisTO> getAnimaisTOList() {
        return animaisTOList;
    }

    public void setAnimaisTOList(List<AnimaisTO> animaisTOList) {
        this.animaisTOList = animaisTOList;
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
        if (!(object instanceof RacasTO)) {
            return false;
        }
        RacasTO other = (RacasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.RacasTO[ id=" + id + " ]";
    }

}
