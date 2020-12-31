package osbo.osbo.osbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GetMessage extends AppCompatActivity {
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_message);

        message = findViewById(R.id.message);

        Intent intent = getIntent();
        String getMessage = intent.getStringExtra("MESSAGE");
        if(getMessage.equals("")){
            message.setText("Found no message");
        }
        else {
            message.setText(getMessage);
        }
    }
}