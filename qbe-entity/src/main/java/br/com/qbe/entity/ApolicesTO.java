package br.com.qbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "apolices", catalog = "qbe", schema = "")
@XmlRootElement
@JsonIgnoreProperties(value = {"id", "apolicesStatusTOList", "nCorretor"})
@JsonPropertyOrder(value = {"apolice", "nome", "cnpjcpf", "aniversario", "codDispositivo", "descricao", "numero", "complemento",
"cidade", "bairro", "uf", "codFipe", "valor", "marcaVeiculo", "modeloVeiculo", "placa", "cor", "utilizacaoVeiculo",
"anoModelo", "renavan", "chassi", "nCorretor", "geracao", "carga", "capitalizacao", "lote", "estCivil", "km",
"tipo", "combustivel", "assistenciasPremiosTO", "coberturasPremiosTO"})
@NamedQueries({
    @NamedQuery(name = "ApolicesTO.findAll", query = "SELECT a FROM ApolicesTO a")})
public class ApolicesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer apolice;
    @Size(max = 60)
    @Column(length = 60)
    private String nome;
    @Size(max = 18)
    @Column(length = 18)
    private String cnpjcpf;
    @Size(max = 10)
    @Column(length = 10)
    private String aniversario;
    @Size(max = 11)
    @Column(length = 11)
    @JsonProperty(value = "cod_dispositivo")
    private String codDispositivo;
    @Size(max = 100)
    @Column(length = 100)
    private String descricao;
    @Size(max = 10)
    @Column(length = 10)
    private String cep;
    @Size(max = 45)
    @Column(length = 45)
    private String endereco;
    @Size(max = 45)
    @Column(length = 45)
    private String numero;
    @Size(max = 45)
    @Column(length = 45)
    private String complemento;
    @Size(max = 45)
    @Column(length = 45)
    private String cidade;
    @Size(max = 45)
    @Column(length = 45)
    private String bairro;
    @Size(max = 45)
    @Column(length = 45)
    private String uf;
    @Size(max = 45)
    @Column(length = 45)
    @JsonProperty(value = "cod_fipe")
    private String codFipe;
    @Size(max = 45)
    @Column(length = 45)
    private String valor;
    @Size(max = 45)
    @Column(length = 45)
    @JsonProperty(value = "marca_veiculo")
    private String marcaVeiculo;
    @Size(max = 45)
    @Column(length = 45)
    @JsonProperty(value = "modelo_veiculo")
    private String modeloVeiculo;
    @Size(max = 8)
    @Column(length = 8)
    private String placa;
    @Size(max = 45)
    @Column(length = 45)
    private String cor;
    @Size(max = 45)
    @Column(length = 45)
    @JsonProperty(value = "utilizacao_veiculo")
    private String utilizacaoVeiculo;
    @Size(max = 4)
    @Column(length = 4)
    @JsonProperty(value = "ano_modelo")
    private String anoModelo;
    @Size(max = 45)
    @Column(length = 45)
    private String renavan;
    @Size(max = 45)
    @Column(length = 45)
    private String chassi;
    @Size(max = 45)
    @Column(length = 45)
    @JsonProperty(value = "n_corretor")
    private String nCorretor;
    @Size(max = 10)
    @Column(length = 10)
    private String geracao;
    @Size(max = 10)
    @Column(length = 10)
    private String carga;
    @Size(max = 45)
    @Column(length = 45)
    private String capitalizacao;
    @Size(max = 45)
    @Column(length = 45)
    private String lote;
    @JsonProperty(value = "est_civil")
    private Character estCivil;
    @Column(name = "0km")
    @JsonProperty(value = "0km")
    private Character km;
    @Size(max = 45)
    @Column(length = 45)
    private String tipo;
    private Character combustivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apolice", fetch = FetchType.LAZY)
    private List<ApolicesStatusTO> apolicesStatusTOList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apolicesTO", fetch = FetchType.LAZY)
    @JsonProperty(value = "assistencias")
    private AssistenciasPremiosTO assistenciasPremiosTO;
    @JsonProperty(value = "coberturas")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apolicesTO", fetch = FetchType.LAZY)
    private CoberturasPremiosTO coberturasPremiosTO;

    public ApolicesTO() {
    }

    public ApolicesTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApolice() {
        return apolice;
    }

    public void setApolice(Integer apolice) {
        this.apolice = apolice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getCodDispositivo() {
        return codDispositivo;
    }

    public void setCodDispositivo(String codDispositivo) {
        this.codDispositivo = codDispositivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodFipe() {
        return codFipe;
    }

    public void setCodFipe(String codFipe) {
        this.codFipe = codFipe;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getUtilizacaoVeiculo() {
        return utilizacaoVeiculo;
    }

    public void setUtilizacaoVeiculo(String utilizacaoVeiculo) {
        this.utilizacaoVeiculo = utilizacaoVeiculo;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getNCorretor() {
        return nCorretor;
    }

    public void setNCorretor(String nCorretor) {
        this.nCorretor = nCorretor;
    }

    public String getGeracao() {
        return geracao;
    }

    public void setGeracao(String geracao) {
        this.geracao = geracao;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public String getCapitalizacao() {
        return capitalizacao;
    }

    public void setCapitalizacao(String capitalizacao) {
        this.capitalizacao = capitalizacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Character getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(Character estCivil) {
        this.estCivil = estCivil;
    }

    public Character getKm() {
        return km;
    }

    public void setKm(Character km) {
        this.km = km;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Character combustivel) {
        this.combustivel = combustivel;
    }

    @XmlTransient
    public List<ApolicesStatusTO> getApolicesStatusTOList() {
        return apolicesStatusTOList;
    }

    public void setApolicesStatusTOList(List<ApolicesStatusTO> apolicesStatusTOList) {
        this.apolicesStatusTOList = apolicesStatusTOList;
    }

    public AssistenciasPremiosTO getAssistenciasPremiosTO() {
        return assistenciasPremiosTO;
    }

    public void setAssistenciasPremiosTO(AssistenciasPremiosTO assistenciasPremiosTO) {
        this.assistenciasPremiosTO = assistenciasPremiosTO;
    }

    public CoberturasPremiosTO getCoberturasPremiosTO() {
        return coberturasPremiosTO;
    }

    public void setCoberturasPremiosTO(CoberturasPremiosTO coberturasPremiosTO) {
        this.coberturasPremiosTO = coberturasPremiosTO;
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
        if (!(object instanceof ApolicesTO)) {
            return false;
        }
        ApolicesTO other = (ApolicesTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.ApolicesTO[ id=" + id + " ]";
    }
    
}
