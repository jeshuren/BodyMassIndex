package com.example.jeshuren.bodymassindex;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    TextView bmiValueText;
    TextView bmiDescriptionText;
    String bmiStatus=null;
    float bmiVal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        bmiValueText = (TextView) findViewById(R.id.bmiValue);
        bmiDescriptionText = (TextView) findViewById(R.id.bmiStatus);
        bmiValueText.setText(intent.getStringExtra("bmiValue") + "");
        bmiVal = Float.parseFloat(intent.getStringExtra("bmiValue"));
        int bmiInter = interpretBMI(bmiVal);
        bmiDescriptionText.setText(getResources().getString(bmiInter));
        bmiStatus = getResources().getString(bmiInter);
        int bmiColor = colorBMI(bmiInter);
        bmiValueText.setTextColor(getResources().getColor(bmiColor));
        bmiDescriptionText.setTextColor(getResources().getColor(bmiColor));
    }

    public void whatsappClickHandler(View view) {
        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "Hey Everyone!!\nMy BMI is:"+bmiVal+" and I am"+bmiStatus;

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private int interpretBMI(float bmiValue) {
        if (bmiValue < 16) {
            return R.string.bmiSUnder;
        } else if (bmiValue < 18.5) {
            return R.string.bmiUnder;
        } else if (bmiValue < 25) {
            return R.string.bmiNormal;
        } else if (bmiValue < 30) {
            return R.string.bmiOver;
        } else {
            return R.string.bmiObese;
        }
    }

    private int colorBMI(float bmiValue) {
        if (bmiValue < 16) {
            return R.color.colorRed;
        } else if (bmiValue < 18.5) {
            return R.color.colorPurple;
        } else if (bmiValue < 25) {
            return R.color.colorGreen;
        } else if (bmiValue < 30) {
            return R.color.colorPurple;
        } else {
            return R.color.colorRed;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
