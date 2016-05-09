package br.com.e.feira.web.controller.bean;

import br.com.core.bean.RetornoCEP;
import br.com.core.controller.business.CidadesBO;
import br.com.core.controller.business.EnderecosBO;
import br.com.core.entity.CidadesTO;
import br.com.core.entity.EnderecosTO;
import br.com.core.util.RestUtil;
import br.com.e.feira.business.controller.business.FeirasBO;
import br.com.core.entity.FeirasTO;
import br.com.e.feira.business.controller.business.FeirasPreCadastrosBO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author marce
 */
@Named
@Scope("session")
public class FeiraBean {

    private static final String URL_WS_CEP = "http://e-feira.jelasticlw.com.br:8080/cep/ws/consultar.json?cep=[cep]&chave=038C5B82D6EAFAB0B5FD1977B5B3A12A";

    @Inject
    FeirasBO feiraNegocio;
    @Inject
    EnderecosBO enderecoNegocio;
    @Inject
    FeirasPreCadastrosBO feirasPreCadastrosNegocio;
    @Inject
    CidadesBO cidadesNegocio;
    private MapModel geoModel;
    private RetornoCEP retornoCEP;
    private EnderecosTO enderecosTO;
    private String iconeeFeira;
    private String centerGeoMap = "-16.680591, -49.256261";
    private String zoom = "10";
    private String cep;
    private String nomeFeira;
    private String logradouro;
    private String bairro;
    private String cidade;
    private Boolean habilitaCadastro;

    @PostConstruct
    public void init() {
        iconeeFeira = "/e-feira/resources/images/iconeFeira32px.png";
        geoModel = new DefaultMapModel();
        habilitaCadastro = false;
        buscarFeiras();
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        buscarFeiras();
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                //geoModel.addOverlay(new Overlay());
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
                //System.out.println("Coordenada: " + centerGeoMap + " End." + result.getAddress());
                zoom = "15";
            }
        }
    }

    public void onReverseGeocode(ReverseGeocodeEvent event) {
        List<String> addresses = event.getAddresses();
        LatLng coord = event.getLatlng();
        buscarFeiras();
        if (addresses != null && !addresses.isEmpty()) {
            centerGeoMap = coord.getLat() + "," + coord.getLng();
            geoModel.addOverlay(new Marker(coord, addresses.get(0)));
            zoom = "15";
        }
    }

    public void consultarCEP(ValueChangeEvent event) {
        if (event.getNewValue() != null && !event.getNewValue().toString().isEmpty()) {
            cep = (String) event.getNewValue();
            retornoCEP = new RetornoCEP();
            retornoCEP = (RetornoCEP) RestUtil.buscaRetornoRestSpring(retornoCEP,
                    URL_WS_CEP.replace("[cep]", cep));
            if (retornoCEP != null && !"erro".equals(retornoCEP.getStatus())) {
                logradouro = retornoCEP.getLogradouro();
                bairro = retornoCEP.getBairro();
                cidade = retornoCEP.getCidade();
                enderecosTO = new EnderecosTO(retornoCEP.getIdEndereco());
                enderecosTO = enderecoNegocio.consultar(enderecosTO);
            }
        } else {
            limpar();
        }
    }

    public void habilitaCadastroFeira(ValueChangeEvent event) {
        if (event.getNewValue() != null && !event.getNewValue().toString().isEmpty()) {
            habilitaCadastro = true;
        }
    }

    public void limpar() {
        cep = "";
        nomeFeira = "";
        logradouro = "";
        bairro = "";
        cidade = "";
        enderecosTO = null;
        habilitaCadastro = false;
    }

    private void buscarFeiras() {
        zoom = "10";
        geoModel = new DefaultMapModel();
        FeirasTO feirasTO = new FeirasTO();
        //Busca feiras da cidade de Goiânia;
        CidadesTO cidades = new CidadesTO(2174);
        cidades = cidadesNegocio.consultar(cidades);
        if (cidades != null) {
            feirasTO.setIdCidade(cidades);
            List<FeirasTO> feiras = feiraNegocio.listar(feirasTO);
            for (FeirasTO feira : feiras) {
                if (feira.getLatitude() != null && feira.getLongitude() != null) {
                    LatLng coord = new LatLng(feira.getLatitude(), feira.getLongitude());
                    centerGeoMap = coord.getLat() + "," + coord.getLng();
                    geoModel.addOverlay(new Marker(coord, feira.getNome(), null, iconeeFeira));
                }
            }
        }
    }

    public String cadastrarFeira() {
        limpar();
        return "cadastrar-e";
    }

    public String efeira() {
        limpar();
        return "e";
    }

    public String detalhes() {
        limpar();
        return "detalhes-e";
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    public RetornoCEP getRetornoCEP() {
        return retornoCEP;
    }

    public void setRetornoCEP(RetornoCEP retornoCEP) {
        this.retornoCEP = retornoCEP;
    }

    public String getIconeeFeira() {
        return iconeeFeira;
    }

    public void setIconeeFeira(String iconeeFeira) {
        this.iconeeFeira = iconeeFeira;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeFeira() {
        return nomeFeira;
    }

    public void setNomeFeira(String nomeFeira) {
        this.nomeFeira = nomeFeira;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Boolean getHabilitaCadastro() {
        return habilitaCadastro;
    }

    public void setHabilitaCadastro(Boolean habilitaCadastro) {
        this.habilitaCadastro = habilitaCadastro;
    }

}
