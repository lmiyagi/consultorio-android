package br.com.leonardomiyagi.apppaciente.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.leonardomiyagi.apppaciente.R;
import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import br.com.leonardomiyagi.apppaciente.appointment.CreateAppointmentActivity;
import br.com.leonardomiyagi.apppaciente.databinding.ActivityMainBinding;
import br.com.leonardomiyagi.apppaciente.login.LoginActivity;
import br.com.leonardomiyagi.apppaciente.main.adapter.AppointmentAdapter;
import br.com.leonardomiyagi.apppaciente.util.DateFormatter;
import br.com.leonardomiyagi.apppaciente.util.PreferenceUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View, AppointmentAdapter.AppointmentsCallback {

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private AppointmentAdapter adapter;
    private List<Appointment> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);
        binding.setPresenter(presenter);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(PreferenceUtils.getString(this, User.PREFERENCES_USER_NAME));
        }
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAppointments(PreferenceUtils.getString(this, User.PREFERENCES_USER_CPF),
                PreferenceUtils.getString(this, User.PREFERENCES_USER_TOKEN));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                presenter.getAppointments(
                        PreferenceUtils.getString(this, User.PREFERENCES_USER_CPF),
                        PreferenceUtils.getString(this, User.PREFERENCES_USER_TOKEN));
                return true;
            case R.id.logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        appointments = new ArrayList<>();
        adapter = new AppointmentAdapter(this, appointments, this);
        binding.appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.appointmentsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments.clear();
        adapter.notifyDataSetChanged();
        this.appointments.addAll(appointments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToCreateAppointment() {
        startActivity(new Intent(this, CreateAppointmentActivity.class));
    }

    @Override
    public void showEmptyList(boolean show) {
        binding.appointmentsRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.emptyMessageTextView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        binding.appointmentsProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showSuccessfulCancellationMessage() {
        Toast.makeText(this, "Solicitação de cancelamento enviada. Aguarde a resposta.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logout() {
        User.logoutCurrentUser(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onClick(final Appointment appointment) {
        new AlertDialog.Builder(this)
                .setTitle("Cancelar Consulta")
                .setMessage("Deseja realmente cancelar essa consulta?" +
                        "\n" + DateFormatter.getDateOnly(appointment.getDate()) +
                        "\n" + DateFormatter.getTimeOnly(appointment.getDate()))
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.cancelAppointment(
                                PreferenceUtils.getString(MainActivity.this, User.PREFERENCES_USER_CPF),
                                PreferenceUtils.getString(MainActivity.this, User.PREFERENCES_USER_TOKEN),
                                appointment);
                    }
                })
                .create()
                .show();
    }
}
