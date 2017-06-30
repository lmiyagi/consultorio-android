package br.com.leonardomiyagi.apppaciente.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import static br.com.leonardomiyagi.apppaciente.api.model.Appointment.Situation.CANCELLED;
import static br.com.leonardomiyagi.apppaciente.api.model.Appointment.Situation.PENDING_CANCEL;
import static br.com.leonardomiyagi.apppaciente.api.model.Appointment.Situation.PENDING_SCHEDULE;
import static br.com.leonardomiyagi.apppaciente.api.model.Appointment.Situation.SCHEDULED;
import static br.com.leonardomiyagi.apppaciente.api.model.Appointment.Situation.UNKNOWN;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class Appointment {

    @SerializedName("id")
    private int id;
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

    public Situation getSituationType() {
        switch (situation) {
            case "scheduled":
                return SCHEDULED;
            case "cancelled":
                return CANCELLED;
            case "pending_schedule":
                return PENDING_SCHEDULE;
            case "pending_cancel":
                return PENDING_CANCEL;
            default:
                return UNKNOWN;
        }
    }

    public String getSituation() {
        switch (getSituationType()) {
            case SCHEDULED:
                return "Agendado";
            case CANCELLED:
                return "Cancelado";
            case PENDING_SCHEDULE:
                return "Aguardando Agendamento";
            case PENDING_CANCEL:
                return "Aguardando Cancelamento";
            case UNKNOWN:
            default:
                return "Erro ao buscar situação.";
        }
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Situation {
        SCHEDULED,
        CANCELLED,
        PENDING_SCHEDULE,
        PENDING_CANCEL,
        UNKNOWN
    }
}
