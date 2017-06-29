package br.com.leonardomiyagi.apppaciente.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.leonardomiyagi.apppaciente.api.model.User;
import okhttp3.OkHttpClient;
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
}
