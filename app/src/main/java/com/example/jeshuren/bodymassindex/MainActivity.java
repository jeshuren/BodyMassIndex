package com.example.jeshuren.bodymassindex;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateClickHandler(View view) {

        if (view.getId() == R.id.button) {
            EditText heightBox = (EditText) findViewById(R.id.height);
            EditText weightBox = (EditText) findViewById(R.id.weight);
            float height = Float.parseFloat(heightBox.getText().toString())/100;
            float weight = Float.parseFloat(weightBox.getText().toString());
            float bmiValue = calculateBMI(weight, height);
            String bmi = Float.toString(bmiValue);
            Intent intent = new Intent(this, DisplayActivity.class);
            intent.putExtra("bmiValue",bmi);
            startActivity(intent);
        }
    }

    private float calculateBMI(float weight, float height) {
        return (float) (weight / (height * height));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
