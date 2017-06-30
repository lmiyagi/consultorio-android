package br.com.leonardomiyagi.apppaciente.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.leonardomiyagi.apppaciente.R;
import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import br.com.leonardomiyagi.apppaciente.databinding.ListItemAppointmentBinding;
import br.com.leonardomiyagi.apppaciente.util.DateFormatter;

/**
 * Created by lmiyagi on 6/29/17.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private final Context context;
    private final List<Appointment> appointments;
    private final AppointmentsCallback appointmentsCallback;

    public AppointmentAdapter(Context context, List<Appointment> appointments, AppointmentsCallback appointmentsCallback) {
        this.context = context;
        this.appointments = appointments;
        this.appointmentsCallback = appointmentsCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ListItemAppointmentBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item_appointment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.format(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemAppointmentBinding binding;

        ViewHolder(ListItemAppointmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void format(final Appointment appointment) {
            binding.appointmentDateTextView.setText(context.getString(R.string.appointment_item_date, DateFormatter.getDateOnly(appointment.getDate()), DateFormatter.getTimeOnly(appointment.getDate())));
            binding.appointmentSituationTextView.setText(context.getString(R.string.appointment_item_situation, appointment.getSituation()));
            if (appointment.getSituationType() == Appointment.Situation.PENDING_SCHEDULE || appointment.getSituationType() == Appointment.Situation.SCHEDULED)
                binding.appointmentContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appointmentsCallback.onClick(appointment);
                    }
                });
            switch (appointment.getSituationType()) {
                case CANCELLED:
                    binding.appointmentContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.lightRed));
                    break;
                case SCHEDULED:
                    binding.appointmentContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.lightColorPrimary));
                    break;
            }
        }
    }

    public interface AppointmentsCallback {
        void onClick(Appointment appointment);
    }
}
