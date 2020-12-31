package osbo.osbo.osbo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class Set_Alarm extends AppCompatActivity {
    int seconds;
    String translation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
    }
    public void thirty(View view) {
        seconds = 30;
        translation = "30 Seconds";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void forty5(View view) {
        seconds = 45;
        translation = "45 Seconds";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void one(View view) {
        seconds = 60;
        translation = "1 Minute";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void three(View view) {
        seconds = 180;
        translation = "3 Minutes";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void ten(View view) {
        seconds = 600;
        translation = "10 Minutes";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }
    public void setAlarm(long mill){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);
        PendingIntent fakeAlarm = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,mill,fakeAlarm);

        AlertDialog alertDialog = new AlertDialog.Builder(Set_Alarm.this).create();
        alertDialog.setTitle("Alarm");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Alarm set to ring in "+translation);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                    Intent intent1 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent1);
                });
        alertDialog.show();

    }
}