package br.com.leonardomiyagi.apppaciente.api.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import br.com.leonardomiyagi.apppaciente.util.PreferenceUtils;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class User {

    public static final String PREFERENCES_USER_NAME = "PREFERENCES_USER_NAME";
    public static final String PREFERENCES_USER_EMAIL = "PREFERENCES_USER_EMAIL";
    public static final String PREFERENCES_USER_CPF = "PREFERENCES_USER_CPF";
    public static final String PREFERENCES_USER_CNS = "PREFERENCES_USER_CNS";
    public static final String PREFERENCES_USER_TOKEN = "PREFERENCES_USER_TOKEN";
    public static final String PREFERENCES_USER_DEFAULT_PASSWORD = "PREFERENCES_USER_DEFAULT_PASSWORD";
    public static final String LOGGED_IN = "LOGGED_IN";

    @SerializedName("name")
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public static void setCurrentUser(Context context, User user) {
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_NAME, user.getName());
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_EMAIL, user.getEmail());
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_CPF, user.getCpf());
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_CNS, user.getCns());
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_DEFAULT_PASSWORD, user.isDefaultPassword());
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_TOKEN, user.getToken());
    }

    public static void logoutCurrentUser(Context context) {
        PreferenceUtils.setPreference(context, User.LOGGED_IN, false);
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_EMAIL, "");
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_CPF, "");
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_CNS, "");
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_DEFAULT_PASSWORD, false);
        PreferenceUtils.setPreference(context, User.PREFERENCES_USER_TOKEN, "");
    }
}
