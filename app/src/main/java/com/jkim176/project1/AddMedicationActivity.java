package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMedicationActivity extends AppCompatActivity {

    TextView userTV;
    EditText nameET, doseET, frequencyET, startET, endET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        userTV = findViewById(R.id.add_med_user);
        nameET = findViewById(R.id.add_med_name);
        doseET = findViewById(R.id.add_med_dose);
        frequencyET = findViewById(R.id.add_med_freq);
        startET = findViewById(R.id.add_med_start);
        endET = findViewById(R.id.add_med_end);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_med_button_save:
                if(nameET.getText().length() > 0) {
                    String name = nameET.getText().toString();
                    String dose = doseET.getText().toString();
                    String frequency = frequencyET.getText().toString();
                    String start = startET.getText().toString();
                    String end = endET.getText().toString();

                    Medication medication = new Medication();
                    medication.setFk(userId);
                    medication.setMedicationName(name);
                    medication.setDose(dose);
                    medication.setFrequency(frequency);
                    medication.setStartDate(start);
                    medication.setEndDate(end);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addMedication(medication);

                    Toast toast = Toast.makeText(this, "Medication: " + name + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    nameET.setText("");
                    doseET.setText("");
                    frequencyET.setText("");
                    startET.setText("");
                    endET.setText("");

                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a medication name.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_med_button_back:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.add_med_button_review:
                Intent intent2 = new Intent(this, ReviewMedicationActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
