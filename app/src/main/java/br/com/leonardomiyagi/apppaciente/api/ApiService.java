package br.com.leonardomiyagi.apppaciente.api;

import br.com.leonardomiyagi.apppaciente.api.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
}
