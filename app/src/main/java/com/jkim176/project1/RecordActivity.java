package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    TextView userTV;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        userTV = findViewById(R.id.record_user);
        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.record_button_medication:
                Intent intent1 = new Intent(this, AddMedicationActivity.class);
                intent1.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent1.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent1);
                break;
            case R.id.record_button_allergy:
                Intent intent2 = new Intent(this, AddAllergyActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
            case R.id.record_button_hospitalization:
                Intent intent3 = new Intent(this, AddHospitalizationActivity.class);
                intent3.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent3.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent3);
                break;
            case R.id.record_button_vaccination:
                Intent intent4 = new Intent(this, AddVaccinationActivity.class);
                intent4.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent4.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent4);
                break;
            case R.id.record_button_symptom:
                Intent intent5 = new Intent(this, AddSymptomActivity.class);
                intent5.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent5.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent5);
                break;
            case R.id.record_button_prenatal:
                Intent intent6 = new Intent(this, AddPrenatalActivity.class);
                intent6.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent6.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent6);
                break;
            case R.id.record_button_back:
                Intent intent7 = new Intent(this, MainActivity.class);
                startActivity(intent7);
        }
    }
}
