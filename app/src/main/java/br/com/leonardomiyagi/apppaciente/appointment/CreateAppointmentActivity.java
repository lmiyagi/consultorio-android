package br.com.leonardomiyagi.apppaciente.appointment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.com.leonardomiyagi.apppaciente.R;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import br.com.leonardomiyagi.apppaciente.databinding.ActivityCreateAppointmentBinding;
import br.com.leonardomiyagi.apppaciente.util.PreferenceUtils;

public class CreateAppointmentActivity extends AppCompatActivity implements CreateAppointmentContract.View {

    private ActivityCreateAppointmentBinding binding;
    private CreateAppointmentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_appointment);
        presenter = new CreateAppointmentPresenter(this);
        binding.setPresenter(presenter);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setupDialogs();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDialogs() {
        final Calendar today = Calendar.getInstance();
        binding.datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog(today);
            }
        });
        binding.timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeDialog(today);
            }
        });
    }

    private void openDateDialog(Calendar today) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.datePicker.setText(getString(R.string.activity_create_appointment_date_format, dayOfMonth, month, year));
            }
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void openTimeDialog(Calendar today) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                binding.timePicker.setText(getString(R.string.activity_create_appointment_time_format, hourOfDay, minute));
            }
        }, today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void checkFields() {
        if (validateFields()) {
            presenter.scheduleAppointment(
                    PreferenceUtils.getString(this, User.PREFERENCES_USER_CPF),
                    PreferenceUtils.getString(this, User.PREFERENCES_USER_TOKEN),
                    binding.datePicker.getText().toString().trim(),
                    binding.timePicker.getText().toString().trim());
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, R.string.activity_create_appointment_succesful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeView() {
        finish();
    }

    private boolean validateFields() {
        boolean valid = true;
        if (TextUtils.isEmpty(binding.datePicker.getText().toString().trim())) {
            binding.datePicker.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        if (TextUtils.isEmpty(binding.timePicker.getText().toString().trim())) {
            binding.datePicker.setError(getString(R.string.global_invalid_field));
            valid = false;
        }
        return valid;
    }
}
