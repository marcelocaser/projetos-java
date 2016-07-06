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
@Table(name = "WEB_MENUS", catalog = "", schema = "PORTALWEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebMenusTO.findAll", query = "SELECT w FROM WebMenusTO w")})
public class WebMenusTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nome;
    @Size(max = 50)
    @Column(length = 50)
    private String acao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String icone;
    @Size(max = 20)
    @Column(length = 20)
    private String link;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String ativo;
    @OneToMany(mappedBy = "idpai", fetch = FetchType.LAZY)
    private List<WebMenusTO> webMenusTOList;
    @JoinColumn(name = "IDPAI", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private WebMenusTO idpai;

    public WebMenusTO() {
    }

    public WebMenusTO(Integer id) {
        this.id = id;
    }

    public WebMenusTO(Integer id, String nome, String icone, String ativo) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
        this.ativo = ativo;
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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<WebMenusTO> getWebMenusTOList() {
        return webMenusTOList;
    }

    public void setWebMenusTOList(List<WebMenusTO> webMenusTOList) {
        this.webMenusTOList = webMenusTOList;
    }

    public WebMenusTO getIdpai() {
        return idpai;
    }

    public void setIdpai(WebMenusTO idpai) {
        this.idpai = idpai;
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
        if (!(object instanceof WebMenusTO)) {
            return false;
        }
        WebMenusTO other = (WebMenusTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.WebMenusTO[ id=" + id + " ]";
    }

}
