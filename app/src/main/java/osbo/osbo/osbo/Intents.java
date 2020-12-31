package osbo.osbo.osbo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Intents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
    }

    public void call(View view) {
        Intent call = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", "+256700453192", null));
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions();
        }
        else {
            startActivity(call);
        }
    }

    public void message(View view) {
        EditText message= (EditText)findViewById(R.id.message);
        Toast.makeText(this, "Sending message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),GetMessage.class);
        intent.putExtra("MESSAGE",message.getText().toString());
        startActivity(intent);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(Intents.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}