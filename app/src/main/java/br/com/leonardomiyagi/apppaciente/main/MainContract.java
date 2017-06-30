package br.com.leonardomiyagi.apppaciente.main;

import java.util.ArrayList;

import br.com.leonardomiyagi.apppaciente.api.model.Appointment;

/**
 * Created by lmiyagi on 6/29/17.
 */

public abstract class MainContract {

    public interface View {

        void setAppointments(ArrayList<Appointment> appointments);

        void showError(String error);

        void goToCreateAppointment();

        void showEmptyList(boolean show);

        void showLoading(boolean show);

        void showSuccessfulCancellationMessage();
    }

    public interface Presenter {

        void getAppointments(String cpf, String token);

        void onCreateAppointmentClicked();

        void cancelAppointment(String cpf, String token, Appointment appointment);
    }
}
