package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "WEB_MENU", catalog = "", schema = "VISITACAO")
@XmlRootElement
public class WebMenuTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String icone;
    @Size(max = 50)
    @Column(length = 50)
    private String acao;
    @Size(max = 20)
    @Column(length = 20)
    private String link;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String ativo;
    @OneToMany(mappedBy = "idmenupai", fetch = FetchType.LAZY)
    private List<WebMenuTO> webMenuTOList;
    @JoinColumn(name = "IDMENUPAI", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private WebMenuTO idmenupai;

    public WebMenuTO() {
    }

    public WebMenuTO(BigDecimal id) {
        this.id = id;
    }

    public WebMenuTO(BigDecimal id, String nome, String icone, String ativo) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
        this.ativo = ativo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<WebMenuTO> getWebMenuTOList() {
        return webMenuTOList;
    }

    public void setWebMenuTOList(List<WebMenuTO> webMenuTOList) {
        this.webMenuTOList = webMenuTOList;
    }

    public WebMenuTO getIdmenupai() {
        return idmenupai;
    }

    public void setIdmenupai(WebMenuTO idmenupai) {
        this.idmenupai = idmenupai;
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
        if (!(object instanceof WebMenuTO)) {
            return false;
        }
        WebMenuTO other = (WebMenuTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.WebMenuTO[ id=" + id + " ]";
    }

}
