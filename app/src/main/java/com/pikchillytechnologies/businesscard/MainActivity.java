package com.pikchillytechnologies.businesscard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variable to store the next image count
    Integer nextImageCount = 0;

    // Declare ImageView variable containing business Images
    ImageView imageViewLogo;

    // Declare ImageButton variable for Facebook Link
    ImageButton buttonFacebook;

    // Declare ImageButton variable for Twitter Link
    ImageButton buttonTwitter;

    // Declare TextView variable for Business website Link
    TextView textviewWebsite;

    // Declare ImageButton variable containing business phone
    ImageButton buttonPhone;

    // Declare ImageButton variable for Sharing Business Card
    ImageButton buttonShare;

    String businessPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ImageView instance for showing business images
        imageViewLogo = findViewById(R.id.imageView_Logo);

        // Initialize the ImageButton for Facebook Link
        buttonFacebook = findViewById(R.id.button_Facebook);

        // Initialize the ImageButton for Twitter Link
        buttonTwitter = findViewById(R.id.button_Twitter);

        // Initialize the ImageButton for Calling business phone
        buttonPhone = findViewById(R.id.button_Phone);

        // Initialize the ImageButton for sharing business card
        buttonShare = findViewById(R.id.button_Share);

        // Initialize the ImageButton for sharing business card
        textviewWebsite = findViewById(R.id.textView_Website);

        // Business Phone number
        businessPhone = getResources().getString(R.string.business_phone);

        // Open Web link for Business Facebook Page when facebook icon is clicked
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.facebook_link)));
                    startActivity(browserIntent);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "No Application found to handle the request.",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        // Open Web link for Business Twitter Page when Twitter icon is clicked
        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"We are Creating Twitter Page for you. Will update Soon",Toast.LENGTH_LONG).show();
            }
        });

        // Open Web site for Business website link is clicked
        textviewWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.business_website)));
                    startActivity(browserIntent);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "No Application found to handle the request.",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        // Call Business Phone when Phone button is clicked
        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent callPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + businessPhone));
                    startActivity(callPhoneIntent);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "No Application found to handle the request.",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        // Share Business Card Information when share button is clicked
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Variables to store Business Information like Name, AboutUs, website
                String appName, businessName, subject, businessAboutUs, businessWebsite,shareBusinessData;

                appName = getResources().getString(R.string.app_name);
                businessName = getResources().getString(R.string.business_name);
                businessAboutUs = "About Us:\n" + getResources().getString(R.string.aboutus_desc);
                businessWebsite = "Website: " + getResources().getString(R.string.business_website);

                // variable to store subject when sharing business data in Email
                subject = appName + " for " + businessName;

                // Variable to store business data when sharing
                shareBusinessData = businessName + "\n\n";
                shareBusinessData += businessAboutUs + "\n\n";
                shareBusinessData += businessWebsite + "\n\n";
                shareBusinessData += "Phone: " + businessPhone;

                // Intent to share business data in other Apps like Email / Whatsapp etc
                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, shareBusinessData);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "No Application found to handle the request.",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }




    /**
     * Function to show Previous Image
     * */
    public void callPreviousImage(View view) {

        nextImageCount--;

        switch (nextImageCount){
            case 4:
                imageViewLogo.setImageResource(R.mipmap.img_3);
                break;
            case 3:
                imageViewLogo.setImageResource(R.mipmap.img_2);
                break;
            case 2:
                imageViewLogo.setImageResource(R.mipmap.img_1);
                break;
            default:
                imageViewLogo.setImageResource(R.mipmap.img_4);
                nextImageCount = 4;
                break;
        }

    }

    /**
     * Function to show Next Image
     * */
    public void callNextImage(View view) {

        nextImageCount++;

        switch (nextImageCount){
            case 1:
                imageViewLogo.setImageResource(R.mipmap.img_2);
                break;
            case 2:
                imageViewLogo.setImageResource(R.mipmap.img_3);
                break;
            case 3:
                imageViewLogo.setImageResource(R.mipmap.img_4);
                break;
            default:
                imageViewLogo.setImageResource(R.mipmap.img_1);
                nextImageCount = 0;
                break;
        }

    }

}
