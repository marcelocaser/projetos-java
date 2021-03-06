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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "pessoas", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoasTO.findAll", query = "SELECT p FROM PessoasTO p")})
public class PessoasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 60)
    @Column(nullable = false, length = 60)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character sexo;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Size(min = 4, max = 5)
    @Column(length = 5)
    private String aniversario;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneResidencial;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneComercial;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneCelularPessoal;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneCelularOutro;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneWhatsAppPessoal;
    @Size(min = 10, max = 16)
    @Column(length = 16)
    private String telefoneWhatsAppOutro;
    @Size(max = 15)
    @Column(length = 15)
    private String registroGeral;
    @Size(min = 11, max = 14)
    @Column(length = 14)
    private String cpf;
    //@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(length = 60)
    private String email;
    @Size(max = 60)
    @Column(length = 60)
    private String facebook;
    @Size(max = 60)
    @Column(length = 60)
    private String instagram;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @ManyToMany(mappedBy = "pessoasTOList", fetch = FetchType.LAZY)
    private List<EnderecosComplementosTO> enderecosComplementosTOList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoasTO", fetch = FetchType.LAZY)
    private DependentesTO dependentesTO;
    @OneToMany(mappedBy = "idPessoa", fetch = FetchType.LAZY)
    private List<UsuariosTO> usuariosTOList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoasTO", fetch = FetchType.LAZY)
    private ClientesTO clientesTO;

    public PessoasTO() {
    }

    public PessoasTO(Integer id) {
        this.id = id;
    }

    public PessoasTO(Integer id, String nome, Character sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneCelularPessoal() {
        return telefoneCelularPessoal;
    }

    public void setTelefoneCelularPessoal(String telefoneCelularPessoal) {
        this.telefoneCelularPessoal = telefoneCelularPessoal;
    }

    public String getTelefoneCelularOutro() {
        return telefoneCelularOutro;
    }

    public void setTelefoneCelularOutro(String telefoneCelularOutro) {
        this.telefoneCelularOutro = telefoneCelularOutro;
    }

    public String getTelefoneWhatsAppPessoal() {
        return telefoneWhatsAppPessoal;
    }

    public void setTelefoneWhatsAppPessoal(String telefoneWhatsAppPessoal) {
        this.telefoneWhatsAppPessoal = telefoneWhatsAppPessoal;
    }

    public String getTelefoneWhatsAppOutro() {
        return telefoneWhatsAppOutro;
    }

    public void setTelefoneWhatsAppOutro(String telefoneWhatsAppOutro) {
        this.telefoneWhatsAppOutro = telefoneWhatsAppOutro;
    }

    public String getRegistroGeral() {
        return registroGeral;
    }

    public void setRegistroGeral(String registroGeral) {
        this.registroGeral = registroGeral;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
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

    @XmlTransient
    public List<EnderecosComplementosTO> getEnderecosComplementosTOList() {
        return enderecosComplementosTOList;
    }

    public void setEnderecosComplementosTOList(List<EnderecosComplementosTO> enderecosComplementosTOList) {
        this.enderecosComplementosTOList = enderecosComplementosTOList;
    }

    public DependentesTO getDependentesTO() {
        return dependentesTO;
    }

    public void setDependentesTO(DependentesTO dependentesTO) {
        this.dependentesTO = dependentesTO;
    }

    @XmlTransient
    public List<UsuariosTO> getUsuariosTOList() {
        return usuariosTOList;
    }

    public void setUsuariosTOList(List<UsuariosTO> usuariosTOList) {
        this.usuariosTOList = usuariosTOList;
    }

    public ClientesTO getClientesTO() {
        return clientesTO;
    }

    public void setClientesTO(ClientesTO clientesTO) {
        this.clientesTO = clientesTO;
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
        if (!(object instanceof PessoasTO)) {
            return false;
        }
        PessoasTO other = (PessoasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.PessoasTO[ id=" + id + " ]";
    }

}
