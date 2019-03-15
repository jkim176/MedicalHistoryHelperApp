package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAllergyActivity extends AppCompatActivity {

    TextView userTV;
    EditText nameET, areaET, severityET, startET, endET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_allergy);
        userTV = findViewById(R.id.add_all_user);
        nameET = findViewById(R.id.add_all_name);
        areaET = findViewById(R.id.add_all_area);
        severityET = findViewById(R.id.add_all_severity);
        startET = findViewById(R.id.add_all_start);
        endET = findViewById(R.id.add_all_end);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_all_button_save:
                if(nameET.getText().length() > 0) {
                    String name = nameET.getText().toString();
                    String area = areaET.getText().toString();
                    String severity = severityET.getText().toString();
                    String start = startET.getText().toString();
                    String end = endET.getText().toString();

                    Allergy allergy = new Allergy();
                    allergy.setFk(userId);
                    allergy.setAllergyName(name);
                    allergy.setArea(area);
                    allergy.setSeverity(severity);
                    allergy.setStartDate(start);
                    allergy.setEndDate(end);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addAllergy(allergy);

                    Toast toast = Toast.makeText(this, "Allergy: " + name + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    nameET.setText("");
                    areaET.setText("");
                    severityET.setText("");
                    startET.setText("");
                    endET.setText("");
                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter an allergy name.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_all_button_back:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.add_all_button_review:
                Intent intent2 = new Intent(this, ReviewAllergyActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
