package br.com.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "usuarios", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"chave"}),
    @UniqueConstraint(columnNames = {"cpf"}),
    @UniqueConstraint(columnNames = {"cnpj"}),
    @UniqueConstraint(columnNames = {"login"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosTO.findAll", query = "SELECT u FROM UsuariosTO u")})
public class UsuariosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nomeRazaoFantasia;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character tipoCadastro;
    @Size(max = 14)
    @Column(length = 14)
    private String cpf;
    @Size(max = 18)
    @Column(length = 18)
    private String cnpj;
    @Size(max = 16)
    @Column(length = 16)
    private String telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String email;
    @Size(max = 15)
    @Column(length = 15)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String chave;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PessoasTO idPessoa;
    @OneToMany(mappedBy = "idMenuUsuario", fetch = FetchType.LAZY)
    private List<MenusUsuariosTO> menusUsuariosTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarios", fetch = FetchType.LAZY)
    private List<ConsultasTO> consultasTOList;

    public UsuariosTO() {
    }

    public UsuariosTO(Integer id) {
        this.id = id;
    }

    public UsuariosTO(Integer id, String nomeRazaoFantasia, Character tipoCadastro, String email, String senha, String chave, Character status) {
        this.id = id;
        this.nomeRazaoFantasia = nomeRazaoFantasia;
        this.tipoCadastro = tipoCadastro;
        this.email = email;
        this.senha = senha;
        this.chave = chave;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRazaoFantasia() {
        return nomeRazaoFantasia;
    }

    public void setNomeRazaoFantasia(String nomeRazaoFantasia) {
        this.nomeRazaoFantasia = nomeRazaoFantasia;
    }

    public Character getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(Character tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }

    public PessoasTO getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(PessoasTO idPessoa) {
        this.idPessoa = idPessoa;
    }

    @XmlTransient
    public List<MenusUsuariosTO> getMenusUsuariosTOList() {
        return menusUsuariosTOList;
    }

    public void setMenusUsuariosTOList(List<MenusUsuariosTO> menusUsuariosTOList) {
        this.menusUsuariosTOList = menusUsuariosTOList;
    }

    @XmlTransient
    public List<ConsultasTO> getConsultasTOList() {
        return consultasTOList;
    }

    public void setConsultasTOList(List<ConsultasTO> consultasTOList) {
        this.consultasTOList = consultasTOList;
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
        if (!(object instanceof UsuariosTO)) {
            return false;
        }
        UsuariosTO other = (UsuariosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.UsuariosTO[ id=" + id + " ]";
    }

}
