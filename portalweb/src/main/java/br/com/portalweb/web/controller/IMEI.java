package br.com.portalweb.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author marce
 */
@XmlRootElement
@XmlType(propOrder = {"login", "password", "imei", "error"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class IMEI implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private String imei;
    private String error;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
