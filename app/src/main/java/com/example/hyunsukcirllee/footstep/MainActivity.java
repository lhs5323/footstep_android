package com.example.hyunsukcirllee.footstep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    //path for saving photos to gallery
    String mCurrentPhotoPath;

    //creating file into external storage of the android phone(file_creator)
    private File createImageFileWith() throws IOException {
        // Create an image file name dataformat "yearsmonthdate//hour minute second"
        mCurrentPhotoPath = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        //store image file it into external storage
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //create file name
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();

        //return image
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up button
        Button takePhoto_button = (Button) findViewById(R.id.take_photo_button);
        Button myFootstep_button = (Button) findViewById(R.id.my_footstep);
        Button followMy_button = (Button) findViewById(R.id.followMy_footstep);
        Button others_button = (Button) findViewById(R.id.others_footstep);

    }

    public void showMy_footstep(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    //흔적남기기 method implicit intent(load camera app)
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photofile = null;
        try{
            photofile = createImageFileWith();
        } catch (IOException ex){
            //debug
            Log.e("File not created", ex.getMessage());
        }
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //retrieve the captured image to mdisplayBackground
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            //mdisplayBackground.setImageBitmap(photos);
            Intent imagePass = new Intent(this, RetrievePhotoBackgroundActivity.class);
            imagePass.putExtra("imagepass", bitmap);
            startActivity(imagePass);
        }
    }

    //method to save the captured photo into the gallery
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


}
