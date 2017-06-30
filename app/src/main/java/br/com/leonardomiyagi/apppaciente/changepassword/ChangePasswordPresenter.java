package br.com.leonardomiyagi.apppaciente.changepassword;

import br.com.leonardomiyagi.apppaciente.api.ApiClient;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {

    private final ChangePasswordContract.View view;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void onConfirmClicked() {
        view.checkPasswordFields();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String cpf, String token) {
        ApiClient.changePassword(oldPassword, newPassword, cpf, token, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    view.updateUser();
                    view.goToMain();
                } else {
                    String error = ApiClient.getError(response.errorBody());
                    if (error.isEmpty()) {
                        error = "Falha ao tentar modificar a senha.";
                    }
                    view.showError(error);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
