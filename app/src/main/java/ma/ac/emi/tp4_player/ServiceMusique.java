package ma.ac.emi.tp4_player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class ServiceMusique extends Service {

    MediaPlayer player;
    String mp3URL;
    Intent intent;

    public ServiceMusique() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Créé",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean stopService(Intent intent) {
        player.pause();
        Toast.makeText(this, "Service Paused",Toast.LENGTH_LONG).show();
        String destroyIt = intent.getStringExtra("destroyIt");
        if (destroyIt.equals("yes"))
            this.onDestroy();
        return super.stopService(intent);
    }

    @Override
    public void onDestroy() {
        player.pause();
        String destroyIt = this.intent.getExtras().getString("destroyIt");
        Log.i("Service", "Destroy it :"+destroyIt);
        if (destroyIt.equals("yes"))
            Toast.makeText(this, "Service détruit",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Service Paused",Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.intent = intent;
        Toast.makeText(this, "Service démmaré",Toast.LENGTH_LONG).show();

        if (player == null){
            player = MediaPlayer.create(getApplicationContext(),R.raw.reynard);
        }

        player.start();

        return START_STICKY;
    }
}