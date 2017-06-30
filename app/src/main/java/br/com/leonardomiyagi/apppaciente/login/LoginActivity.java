package br.com.leonardomiyagi.apppaciente.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import br.com.leonardomiyagi.apppaciente.MainActivity;
import br.com.leonardomiyagi.apppaciente.R;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import br.com.leonardomiyagi.apppaciente.changepassword.ChangePasswordActivity;
import br.com.leonardomiyagi.apppaciente.databinding.ActivityLoginBinding;
import br.com.leonardomiyagi.apppaciente.util.PreferenceUtils;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceUtils.getBoolean(this, User.LOGGED_IN, false)) {
            login();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.activity_login_label);
        }
        presenter = new LoginPresenter(this);
        binding.setPresenter(presenter);
    }

    @Override
    public void checkLoginFields() {
        if (validateFields()) {
            presenter.login(binding.cpfEditText.getText().toString().trim(),
                    binding.passwordEditText.getText().toString());
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCurrentUser(User user) {
        User.setCurrentUser(this, user);
    }

    @Override
    public void login() {
        PreferenceUtils.setPreference(this, User.LOGGED_IN, true);
        if (PreferenceUtils.getBoolean(this, User.PREFERENCES_USER_DEFAULT_PASSWORD, false)) {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    private boolean validateFields() {
        boolean valid = true;
        if (TextUtils.isEmpty(binding.cpfEditText.getText().toString().trim())) {
            binding.cpfEditText.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        if (TextUtils.isEmpty(binding.passwordEditText.getText().toString().trim())) {
            binding.passwordEditText.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        return valid;
    }
}
