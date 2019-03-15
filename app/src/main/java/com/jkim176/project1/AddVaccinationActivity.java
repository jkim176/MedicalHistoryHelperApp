package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddVaccinationActivity extends AppCompatActivity {

    TextView userTV;
    EditText nameET, boosterNumberET, dateET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccination);
        userTV = findViewById(R.id.add_vac_user);
        nameET = findViewById(R.id.add_vac_name);
        boosterNumberET = findViewById(R.id.add_vac_booster_number);
        dateET = findViewById(R.id.add_vac_date);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_vac_button_save:
                if (nameET.getText().length() > 0) {
                    String name = nameET.getText().toString();
                    String boosterNumber = boosterNumberET.getText().toString();
                    String date = dateET.getText().toString();

                    Vaccination vaccination = new Vaccination();
                    vaccination.setFk(userId);
                    vaccination.setVaccinationName(name);
                    vaccination.setBoosterNumber(boosterNumber);
                    vaccination.setDate(date);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addVaccination(vaccination);

                    Toast toast = Toast.makeText(this, "Vaccination: " + name + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    nameET.setText("");
                    boosterNumberET.setText("");
                    dateET.setText("");

                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a vaccination name.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_vac_button_back:
                Intent intent1 = new Intent(this, RecordActivity.class);
                intent1.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent1.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent1);
                break;
            case R.id.add_vac_button_review:
                Intent intent2 = new Intent(this, ReviewVaccinationActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
