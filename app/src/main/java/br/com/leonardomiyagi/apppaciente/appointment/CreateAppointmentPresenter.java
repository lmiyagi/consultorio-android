package br.com.leonardomiyagi.apppaciente.appointment;

import java.text.ParseException;
import java.util.Date;

import br.com.leonardomiyagi.apppaciente.api.ApiClient;
import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import br.com.leonardomiyagi.apppaciente.util.DateFormatter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmiyagi on 6/30/17.
 */

public class CreateAppointmentPresenter implements CreateAppointmentContract.Presenter {

    private final CreateAppointmentContract.View view;

    public CreateAppointmentPresenter(CreateAppointmentContract.View view) {
        this.view = view;
    }

    @Override
    public void onScheduleAppointmentClicked() {
        view.checkFields();
    }

    @Override
    public void scheduleAppointment(String cpf, String token, String date, String time) {
        Date scheduleDate;
        try {
            scheduleDate = DateFormatter.mergeDateTime(date, time);
        } catch (ParseException e) {
            view.showError("Erro ao criar data; " + e.getMessage());
            return;
        }
        ApiClient.scheduleAppointment(cpf, token, scheduleDate, new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (response.isSuccessful()) {
                    view.showSuccessMessage();
                    view.closeView();
                } else {
                    String error = ApiClient.getError(response.errorBody());
                    if (error.isEmpty()) {
                        error = "Erro ao agendar consulta.";
                    }
                    view.showError(error);
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
