package com.example.hyunsukcirllee.footstep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class DisplayMessageActivity extends RetrievePhotoBackgroundActivity {

    private ImageView mdisplayBbackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //if the intent has the name image do setbackground
        if(getIntent().hasExtra("Image")) {
            Bundle iimage = getIntent().getExtras();
            Bitmap b = (Bitmap) iimage.get("image");
            mdisplayBbackground = (ImageView) findViewById(R.id.background_capturedd);
            mdisplayBbackground.setBackground(new BitmapDrawable(getResources(), b));

        }

        //if the intent contains the message-> view
        if (getIntent().hasExtra(RetrievePhotoBackgroundActivity.EXTRA_MESSAGE)){
            Intent footstep_intent = getIntent();
            String message = footstep_intent.getStringExtra(RetrievePhotoBackgroundActivity.EXTRA_MESSAGE);
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(message);
        }
    }
}
