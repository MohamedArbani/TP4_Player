package ma.ac.emi.tp4_player;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent serviceIntent;

    Button dm;
    Button ar;
    Button qt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qt = findViewById(R.id.buttonQuit);
        ar = findViewById(R.id.buttonArret);
        dm = findViewById(R.id.buttonDemarrer);

        //Start the service and pass the URL as an extra
        serviceIntent = new Intent();
        serviceIntent.setClassName("ma.ac.emi.tp4_player", "ma.ac.emi.tp4_player.ServiceMusique");
        serviceIntent.setAction("ma.ac.action.PLAY_MUSIC");
    }

    public void demarrerChanson(View view) {
        dm.setEnabled(false);
        ar.setEnabled(true);
        qt.setEnabled(true);

        startService(serviceIntent);
    }

    public void arreterChanson(View view) {
        dm.setEnabled(true);
        ar.setEnabled(false);
        serviceIntent.putExtra("destroyIt","no");
        stopService(serviceIntent);
    }

    public void quitterChanson(View view) {
        dm.setEnabled(true);
        qt.setEnabled(false);
        ar.setEnabled(false);
        serviceIntent.putExtra("destroyIt","yes");
        stopService(serviceIntent);
    }

    private static final int REQUEST_ID = 1;

}