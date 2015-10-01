package br.com.cast.turmaformacao.agenda;

import android.app.Application;

import br.com.cast.turmaformacao.agenda.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaManagerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}

