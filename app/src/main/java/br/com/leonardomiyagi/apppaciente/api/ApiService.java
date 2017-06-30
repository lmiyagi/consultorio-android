package br.com.leonardomiyagi.apppaciente.api;

import java.util.ArrayList;

import br.com.leonardomiyagi.apppaciente.api.model.Appointment;
import br.com.leonardomiyagi.apppaciente.api.model.User;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lmiyagi on 6/28/17.
 */

public interface ApiService {

    @POST("api/v1/users/sign_in")
    @FormUrlEncoded
    Call<User> login(@Field("cpf") String cpf, @Field("password") String password);

    @PUT("api/v1/users/me/change_password")
    @FormUrlEncoded
    Call<User> changePassword(@Field("old_password") String oldPassword, @Field("new_password") String newPassword, @Field("cpf") String cpf, @Field("token") String token);

    @GET("api/v1/users/me/appointments")
    Call<ArrayList<Appointment>> getAppointments(@Query("cpf") String cpf, @Query("token") String token);

    @POST("api/v1/users/me/appointments")
    @FormUrlEncoded
    Call<Appointment> scheduleAppointment(@Field("cpf") String cpf, @Field("token") String token, @Field("date") String date);

    @POST("api/v1/users/me/appointments/{appointment_id}/cancel")
    @FormUrlEncoded
    Call<ResponseBody> cancelAppointment(@Field("cpf") String cpf, @Field("token") String token, @Path("appointment_id") int appointmentId);
}
