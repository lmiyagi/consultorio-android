package br.com.leonardomiyagi.apppaciente.changepassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import br.com.leonardomiyagi.apppaciente.R;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import br.com.leonardomiyagi.apppaciente.databinding.ActivityChangePasswordBinding;
import br.com.leonardomiyagi.apppaciente.main.MainActivity;
import br.com.leonardomiyagi.apppaciente.util.PreferenceUtils;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordContract.View {

    ChangePasswordContract.Presenter presenter;
    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        presenter = new ChangePasswordPresenter(this);
        binding.setPresenter(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferenceUtils.getBoolean(this, User.PREFERENCES_USER_DEFAULT_PASSWORD, false)) {
            binding.changeDefaultPasswordHintText.setVisibility(View.VISIBLE);
            binding.oldPasswordEditText.setVisibility(View.GONE);
            binding.oldPasswordEditText.setText(PreferenceUtils.getString(this, User.PREFERENCES_USER_CNS));
        }
    }

    @Override
    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void checkPasswordFields() {
        if (validateFields()) {
            presenter.changePassword(binding.oldPasswordEditText.getText().toString(),
                    binding.newPasswordEditText.getText().toString(),
                    PreferenceUtils.getString(this, User.PREFERENCES_USER_CPF),
                    PreferenceUtils.getString(this, User.PREFERENCES_USER_TOKEN));
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUser() {
        PreferenceUtils.setPreference(this, User.PREFERENCES_USER_DEFAULT_PASSWORD, false);
    }

    private boolean validateFields() {
        boolean valid = true;
        if (TextUtils.isEmpty(binding.oldPasswordEditText.getText().toString().trim())) {
            binding.oldPasswordEditText.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        if (TextUtils.isEmpty(binding.newPasswordEditText.getText().toString().trim())) {
            binding.newPasswordEditText.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        return valid;
    }
}
