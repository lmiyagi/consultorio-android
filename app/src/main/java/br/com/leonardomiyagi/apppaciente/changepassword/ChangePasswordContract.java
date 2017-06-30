package br.com.leonardomiyagi.apppaciente.changepassword;

import br.com.leonardomiyagi.apppaciente.api.model.User;

/**
 * Created by lmiyagi on 6/28/17.
 */

public abstract class ChangePasswordContract {

    public interface View {

        void goToMain();

        void checkPasswordFields();

        void showError(String error);

        void updateUser();
    }

    public interface Presenter {

        void onConfirmClicked();

        void changePassword(String oldPassword, String newPassword, String cpf, String token);
    }
}
