package br.com.leonardomiyagi.apppaciente.api;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import br.com.leonardomiyagi.apppaciente.util.DateFormatter;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class ApiClient {

    public static final String BASE_URL = "http://10.0.0.18:3000/";
    public static final int UNPROCESSABLE_ENTITY = 422;

    private static ApiService apiService;

    public static void setup() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static Call<User> login(String cpf, String password, Callback<User> callback) {
        Call<User> call = apiService.login(cpf, password);
        call.enqueue(callback);
        return call;
    }

    public static String getError(ResponseBody errorBody) {
        String error = "";
        try {
            if (errorBody != null) {
                JSONObject object = new JSONObject(errorBody.string());
                error = object.getJSONObject("errors").getString("params");
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return error;
    }

    public static Call<User> changePassword(String oldPassword, String newPassword, String cpf, String token, Callback<User> callback) {
        Call<User> call = apiService.changePassword(oldPassword, newPassword, cpf, token);
        call.enqueue(callback);
        return call;
    }

    public static Call<Appointment> scheduleAppointment(String cpf, String token, Date date, Callback<Appointment> callback) {
        Call<Appointment> call = apiService.scheduleAppointment(cpf, token, DateFormatter.formatToApiDate(date));
        call.enqueue(callback);
        return call;
    }

    public static Call<ResponseBody> cancelAppointment(String cpf, String token, int appointmentId, Callback<ResponseBody> callback) {
        Call<ResponseBody> call = apiService.cancelAppointment(cpf, token, appointmentId);
        call.enqueue(callback);
        return call;
    }

    public static Call<ArrayList<Appointment>> getAppointments(String cpf, String token, Callback<ArrayList<Appointment>> callback) {
        Call<ArrayList<Appointment>> call = apiService.getAppointments(cpf, token);
        call.enqueue(callback);
        return call;
    }
}
