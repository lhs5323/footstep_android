package com.example.hyunsukcirllee.footstep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.ByteArrayOutputStream;

public class RetrievePhotoBackgroundActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.hyunsukcirllee.footstep.MESSAGE";
    private ImageView mdisplayBackground;
    public static final int SEND_MESSAGE = 1;
    int nowText = -1;
    String myTextSwitcher[] = {"감정카테고리를 선택하세요!", "이별", "슬픔", "행복", "걱정", "기쁨"};
    int count_text = myTextSwitcher.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_photo_background);
        final TextSwitcher switch_text = (TextSwitcher)findViewById(R.id.textSwitcher);
        //ImageButton emotion_selector = (ImageButton) findViewById(R.id.imageButton2);
        switch_text.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                Typeface font = ResourcesCompat.getFont(RetrievePhotoBackgroundActivity.this, R.font.hongchawang);

                TextView myTextSwitcher = new TextView(RetrievePhotoBackgroundActivity.this);

                myTextSwitcher.setTypeface(font);
                myTextSwitcher.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myTextSwitcher.setTextSize(30);
                //myTextSwitcher.setHint(myTextSwitcher[0]);
                //myTextSwitcher.font
                //myTextSwitcher.getFontFeatureSettings(@font/)
                return myTextSwitcher;

            }
        });
        switch_text.setOnClickListener(new View.OnClickListener() {
            //myTextSwitcher.setHint(myTextSwitcher[0]);
            @Override
            public void onClick(View v) {
                //myTextSwitcher.setHint("감정 카테고리를 선택하세요");
                nowText++;
                if (nowText == count_text) {
                    nowText = 0;

                }
                switch_text.setText(myTextSwitcher[nowText]);
            }
        });



        //Bundle to get intent
        Bundle extras = getIntent().getExtras();

        //If Bundle is not empty proceed
        if(extras != null){
            //get the imagepass key from the previous activity and set it to bitmap
            Bitmap retrieveImage = (Bitmap) extras.get("imagepass");

            //If the bitmap is not empty
            if(retrieveImage != null){
                //Set it to following imageView
                mdisplayBackground = (ImageView) findViewById(R.id.background_captured);
                mdisplayBackground.setBackground(new BitmapDrawable(getResources(), retrieveImage));
                mdisplayBackground.getBackground().setAlpha(80);

            }
        }
    }



    //onClick Send button
    public void sendMessage(View view){
        //Creating an explicit intent to display the sent message
        Intent footstep_intent = new Intent(this, DisplayMessageActivity.class);

        //EditText Id
        EditText editText = (EditText) findViewById(R.id.editText);

        //User editText
        String message = editText.getText().toString();

        //This part is what I want to implement!!!
        //mdisplayBackground = (ImageView) findViewById(R.id.background_captured);
        //mdisplayBackground.setImageResource(R.drawable.myimage);
        //Put both Bitmap and String Message to the intent
        //footstep_intent.putExtra("Image", image_pass_again);
        footstep_intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(footstep_intent);
    }
}
