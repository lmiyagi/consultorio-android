package br.com.leonardomiyagi.apppaciente.appointment;

/**
 * Created by lmiyagi on 6/30/17.
 */

public abstract class CreateAppointmentContract {

    public interface View {

        void checkFields();

        void showError(String message);

        void showSuccessMessage();

        void closeView();
    }

    public interface Presenter {

        void onScheduleAppointmentClicked();

        void scheduleAppointment(String cpf, String token, String date, String time);
    }
}
