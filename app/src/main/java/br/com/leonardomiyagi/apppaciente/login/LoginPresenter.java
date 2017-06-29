package br.com.leonardomiyagi.apppaciente.login;

import br.com.leonardomiyagi.apppaciente.api.ApiClient;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onLoginClicked() {
        view.checkLoginFields();
    }

    @Override
    public void login(String cpf, String password) {
        ApiClient.login(cpf, password, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    view.setCurrentUser(response.body());
                    view.goToMain();
                } else {
                    String error = ApiClient.getError(response.errorBody());
                    if (error.isEmpty()) {
                        error = "Falha no login.";
                    }
                    view.showError(error);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
