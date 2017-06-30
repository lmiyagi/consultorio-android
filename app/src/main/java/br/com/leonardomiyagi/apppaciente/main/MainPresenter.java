package br.com.leonardomiyagi.apppaciente.main;

import java.util.ArrayList;

import br.com.leonardomiyagi.apppaciente.api.ApiClient;
import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmiyagi on 6/29/17.
 */

class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getAppointments(String cpf, String token) {
        view.showLoading(true);
        ApiClient.getAppointments(cpf, token, new Callback<ArrayList<Appointment>>() {
            @Override
            public void onResponse(Call<ArrayList<Appointment>> call, Response<ArrayList<Appointment>> response) {
                view.showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        view.showEmptyList(response.body().isEmpty());
                        view.setAppointments(response.body());
                    }
                } else {
                    String error = ApiClient.getError(response.errorBody());
                    if (error.isEmpty()) {
                        error = "Falha ao carregar consultas.";
                    }
                    view.showError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Appointment>> call, Throwable t) {
                view.showLoading(false);
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void onCreateAppointmentClicked() {
        view.goToCreateAppointment();
    }

    @Override
    public void cancelAppointment(final String cpf, final String token, Appointment appointment) {
        ApiClient.cancelAppointment(cpf, token, appointment.getId(), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    getAppointments(cpf, token);
                    view.showSuccessfulCancellationMessage();
                } else {
                    String error = ApiClient.getError(response.errorBody());
                    if (error.isEmpty()) {
                        error = "Falha ao cancelar consulta.";
                    }
                    view.showError(error);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
