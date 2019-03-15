package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddSymptomActivity extends AppCompatActivity {

    TextView userTV;
    EditText nameET, areaET, durationET, startET, endET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptom);
        userTV = findViewById(R.id.add_sym_user);
        nameET = findViewById(R.id.add_sym_name);
        areaET = findViewById(R.id.add_sym_area);
        durationET = findViewById(R.id.add_sym_duration);
        startET = findViewById(R.id.add_sym_start);
        endET = findViewById(R.id.add_sym_end);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_sym_button_save:
                if(nameET.getText().length() > 0) {
                    String name = nameET.getText().toString();
                    String area = areaET.getText().toString();
                    String duration = durationET.getText().toString();
                    String start = startET.getText().toString();
                    String end = endET.getText().toString();

                    Symptom symptom = new Symptom();
                    symptom.setFk(userId);
                    symptom.setSymptomName(name);
                    symptom.setArea(area);
                    symptom.setDuration(duration);
                    symptom.setStartDate(start);
                    symptom.setEndDate(end);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addSymptom(symptom);

                    Toast toast = Toast.makeText(this, "Symptom: " + name + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    nameET.setText("");
                    areaET.setText("");
                    durationET.setText("");
                    startET.setText("");
                    endET.setText("");

                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a symptom name.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_sym_button_back:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.add_sym_button_review:
                Intent intent2 = new Intent(this, ReviewSymptomActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
