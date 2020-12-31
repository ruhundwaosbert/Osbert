package osbo.osbo.osbo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent object = new Intent(context.getApplicationContext(),Alarm.class);
        object.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(object);


    }
}
