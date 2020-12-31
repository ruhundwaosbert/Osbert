package osbo.osbo.osbo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Alarm extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_alarm);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), notification);
        mediaPlayer.start();

    }

    public void stop(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(Alarm.this).create();
        alertDialog.setTitle("Alarm");
        alertDialog.setMessage("Tap yes to dismiss alarm");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                (dialog, which) -> {
                    mediaPlayer.stop();
                    dialog.dismiss();
                    finish();
                    Intent intent1 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent1);
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                (dialog, which) -> {
                    dialog.dismiss();
                });
        alertDialog.show();
    }
}