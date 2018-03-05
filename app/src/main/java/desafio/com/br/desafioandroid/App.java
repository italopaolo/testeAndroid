package desafio.com.br.desafioandroid;


import android.app.Application;

import desafio.com.br.desafioandroid.utils.PreferencesManager;

public class App extends Application {

    private static App instance;
    private static PreferencesManager preferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        PreferencesManager.initializeInstance(this);
        preferencesManager = PreferencesManager.getInstance();
    }

    public static App get() {
        return instance;
    }

    public static PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }
}
