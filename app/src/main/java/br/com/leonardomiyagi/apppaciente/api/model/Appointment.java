package br.com.leonardomiyagi.apppaciente.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class Appointment {

    @SerializedName("patient")
    private User patient;
    @SerializedName("date")
    private Date date;
    @SerializedName("situation")
    private String situation;

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
