package br.com.leonardomiyagi.apppaciente;

import android.app.Application;

import br.com.leonardomiyagi.apppaciente.api.ApiClient;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class PacienteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.setup(this);
    }
}
