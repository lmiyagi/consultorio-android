package br.com.leonardomiyagi.apppaciente.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class User {

    public static final String PREFERENCES_USER_EMAIL = "PREFERENCES_USER_EMAIL";
    public static final String PREFERENCES_USER_CPF = "PREFERENCES_USER_CPF";
    public static final String PREFERENCES_USER_CNS = "PREFERENCES_USER_CNS";
    public static final String PREFERENCES_USER_TOKEN = "PREFERENCES_USER_TOKEN";

    @SerializedName("email")
    private String email;
    @SerializedName("cpf")
    private String cpf;
    @SerializedName("cns")
    private String cns;
    @SerializedName("phone")
    private String phone;
    @SerializedName("authentication_token")
    private String token;
    @SerializedName("default_password")
    private boolean defaultPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(boolean defaultPassword) {
        this.defaultPassword = defaultPassword;
    }
}
