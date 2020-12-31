package osbo.osbo.osbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    private TextView battreyLevel;
    private ProgressBar batProgress;
    private int progress = 90;
    private BatteryAnim anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        battreyLevel = findViewById(R.id.batt);
        batProgress = findViewById(R.id.battProg);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        updateProgressBar();

    }

    public void intents(View view) {
        startActivity(new Intent(getApplicationContext(),Intents.class));
    }

    public void scrollViews(View view) {
        startActivity(new Intent(getApplicationContext(),ScrollViews.class));
    }

    public void alarms(View view) {
        startActivity(new Intent(getApplicationContext(), Set_Alarm.class));
    }

    public void musical(View view) {
        startActivity(new Intent(getApplicationContext(),Musicals.class));
    }

    public void storage(View view) {
        startActivity(new Intent(getApplicationContext(),Storage.class));
    }

    public void email(View view) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setData(Uri.parse("mailto:"));
        String to[] = {"ruhundwao@gmail.com", "osbertnd@gmail.com"};
        intent2.putExtra(Intent.EXTRA_EMAIL, to);
        intent2.putExtra(Intent.EXTRA_SUBJECT, "Need Help");
        intent2.putExtra(Intent.EXTRA_TEXT, "Type here\n");
        intent2.setType("message/rfc822");
        startActivity(intent2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.fav:
                Toast.makeText(this, "Tapped on Favorite", Toast.LENGTH_SHORT).show();
                break;

            case R.id.save:
                Toast.makeText(this, "Tapped on Save", Toast.LENGTH_SHORT).show();
                break;
                
            default:
                break;

        }

        return true;
    }
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            progress = level;
            battreyLevel.setText(String.valueOf(level) + "%");
        }
    };

    public void map(View view) {
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public class BatteryAnim extends Animation {
        private float from, to;
        private ProgressBar pBar;
        public BatteryAnim(ProgressBar progressBar, float from, float to){
            super();
            this.from = from;
            this.to = to;
            this.pBar = progressBar;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            batProgress.setProgress((int) value);
        }
    }

    private void updateProgressBar() {
        anim = new BatteryAnim(batProgress, 0.0f, (float) progress);
        anim.setDuration(2000);
        batProgress.startAnimation(anim);

    }
}