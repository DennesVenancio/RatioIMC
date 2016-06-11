package kurgomobi.ratioimc;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import kurgomobi.ratioimc.util.RatioIMC;

/**
 * Created by dennes on 11/06/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RatioIMC.initInstance();

    }
}
