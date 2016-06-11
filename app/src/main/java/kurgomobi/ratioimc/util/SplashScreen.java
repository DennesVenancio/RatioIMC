package kurgomobi.ratioimc.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kurgomobi.ratioimc.features.view.MainActivity;


public class SplashScreen extends AppCompatActivity {

    private static final Long SPLASH_TIME = 1500l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        sleepAndFinish();
    }

    private void setFullScreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void sleepAndFinish() {
        Intent intent = null;

        try {
            Thread.sleep(SPLASH_TIME);
        } catch (InterruptedException e) {
        } finally {
            intent = new Intent(this, MainActivity.class);

            startActivity(intent);
            finish();
        }
    }

}
