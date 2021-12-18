package com.example.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private int CAMERA_CAPTURE;
    private String videoPath = "";
    Button btn;
    VideoView vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnTakeVideo);
        vid = findViewById(R.id.vid);

        btn.setOnClickListener(view -> {
            try {
                Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            }
            catch (ActivityNotFoundException cant){
                String errorMessage = "Камера не поддерживается";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        Uri uri = (Uri) data.getData();
        vid.setVideoURI(uri);
        vid.setZOrderOnTop(true);
        vid.start();
    }
}