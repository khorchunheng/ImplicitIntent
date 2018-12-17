package com.example.heng.implicitintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        private EditText mWebsiteEditText;
        private EditText mLocationEditText;
        private EditText mShareEditText;
        private EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText=findViewById(R.id.website_editText);
        mLocationEditText=findViewById(R.id.location_editText);
        mShareEditText=findViewById(R.id.share_editText);
        phone=findViewById(R.id.dial_editText);
    }

    public void openWebsite(View view) {
        String url=mWebsiteEditText.getText().toString();
        Uri webpage=Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents","Cannot handle this");
        }
    }

    public void openLocation(View view) {
        String loc=mLocationEditText.getText().toString();
        Uri addressUri=Uri.parse("geo:0,0?q="+loc);
        Intent intent=new Intent(Intent.ACTION_VIEW,addressUri);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents","Cannot handle this");
        }
    }

    public void shareText(View view) {
        String text=mShareEditText.getText().toString();
        String mimeType="text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Share This Test with : ").setText(text).startChooser();
    }

    public void call(View view) {
        String phoneNo=phone.getText().toString();
        Uri dialNumber=Uri.parse("tel:"+phoneNo);
        Intent intent=new Intent(Intent.ACTION_DIAL,dialNumber);
        /*Intent intent=new Intent(Intent.ACTION_VIEW);
        Uri address=Uri.parse("mailto:kelvinkhor1003@gmail.com");
        intent.setData(address);*/
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents","Cannot handle this");
        }

    }
}
