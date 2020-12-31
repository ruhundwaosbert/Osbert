package osbo.osbo.osbo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Storage extends AppCompatActivity {
    String apiUrl = "https://firebasestorage.googleapis.com/v0/b/project-car-share.appspot.com/o/eb391e5700f14ef614cfce95e9069c38.jpg?alt=media&token=43e553a3-8071-42e9-b012-1cea54606ee3";
    Button internal, external;
    ImageView imageView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        imageView = findViewById(R.id.iv_source);
        internal = findViewById(R.id.internal);
        external = findViewById(R.id.external);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        Picasso.get().load(apiUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                internal.setEnabled(true);
                external.setEnabled(true);
//                Toast.makeText(Storage.this, "Image fetched!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(Storage.this, "Unable to fetch image from internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void internal(View view) {
        Drawable drawable = getDrawable(R.drawable.unsplash);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "unsplashed"+".jpg");
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        Toast.makeText(Storage.this, "Image saved in internal storage.\n" + savedImageURI, Toast.LENGTH_SHORT).show();
        view.setEnabled(false);
    }

    public void external(View view) {
        Drawable drawable = getDrawable(R.drawable.unsplash);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        String ImagePath = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "unsplashed",
                "demo_image"
        );
        Uri savedImageURI = Uri.parse(ImagePath);
        Toast.makeText(Storage.this, "Image saved in External storage. (Check Gallery)\n" + savedImageURI, Toast.LENGTH_SHORT).show();
        view.setEnabled(false);
    }
}