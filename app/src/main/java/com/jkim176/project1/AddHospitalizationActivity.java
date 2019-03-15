package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddHospitalizationActivity extends AppCompatActivity {

    TextView userTV;
    EditText nameET, complicationET, startET, endET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospitalization);
        userTV = findViewById(R.id.add_hos_user);
        nameET = findViewById(R.id.add_hos_name);
        complicationET = findViewById(R.id.add_hos_complication);
        startET = findViewById(R.id.add_hos_start);
        endET = findViewById(R.id.add_hos_end);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_hos_button_save:
                if(nameET.getText().length() > 0) {
                    String name = nameET.getText().toString();
                    String complication = complicationET.getText().toString();
                    String start = startET.getText().toString();
                    String end = endET.getText().toString();

                    Hospitalization hospitalization = new Hospitalization();
                    hospitalization.setFk(userId);
                    hospitalization.setHospitalizationName(name);
                    hospitalization.setComplication(complication);
                    hospitalization.setStartDate(start);
                    hospitalization.setEndDate(end);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addHospitalization(hospitalization);

                    Toast toast = Toast.makeText(this, "Hospitalization: " + name + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    nameET.setText("");
                    complicationET.setText("");
                    startET.setText("");
                    endET.setText("");

                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a hospitalization name.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_hos_button_back:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.add_hos_button_review:
                Intent intent2 = new Intent(this, ReviewHospitalizationActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
