package br.com.leonardomiyagi.apppaciente.login;

import br.com.leonardomiyagi.apppaciente.api.model.User;

/**
 * Created by lmiyagi on 6/28/17.
 */

public abstract class LoginContract {

    public interface View {

        void checkLoginFields();

        void showError(String message);

        void setCurrentUser(User user);

        void goToMain();
    }

    public interface Presenter {

        void onLoginClicked();

        void login(String cpf, String password);
    }
}
