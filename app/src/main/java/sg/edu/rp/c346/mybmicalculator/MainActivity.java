package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalc;
    Button btnReset;
    TextView tvBmi;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCalc = findViewById(R.id.buttonCalc);
        btnReset = findViewById(R.id.buttonReset);
        tvBmi = findViewById(R.id.textViewBMI);
        tvDate = findViewById(R.id.textViewDate);

        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        final String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);



        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                float bmi = weight / (height * height);

//                Date currentTime = Calendar.getInstance().getTime();

                tvDate.setText(getString(R.string.calcdate) + datetime);
                tvBmi.setText(getString(R.string.calcbmi) + Float.toString(bmi));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etHeight.setText("0");
                etWeight.setText("0");
                tvDate.setText(getString(R.string.calcdate));
                tvBmi.setText(getString(R.string.calcbmi));
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        String strWeight = etWeight.getText().toString();
        String strHeight = etHeight.getText().toString();
        float weight = Float.parseFloat(strWeight);
        float height = Float.parseFloat(strHeight);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putFloat("strWeight",weight);
        prefEdit.putFloat("strHeight",height);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        float floatWeight = prefs.getFloat("strWeight",0);
        float floatHeight = prefs.getFloat("strHeight",0);

        etWeight.setText(Float.toString(floatWeight));
        etHeight.setText(Float.toString(floatHeight));
    }

}
