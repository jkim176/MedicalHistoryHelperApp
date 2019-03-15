package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPrenatalActivity extends AppCompatActivity {

    TextView userTV;
    EditText weightET, weekET;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prenatal);
        userTV = findViewById(R.id.add_pre_user);
        weightET = findViewById(R.id.add_pre_weight);
        weekET = findViewById(R.id.add_pre_week);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_pre_button_save:
                if(weightET.getText().length() > 0) {
                    String weight = weightET.getText().toString();
                    String week = weekET.getText().toString();

                    Prenatal prenatal = new Prenatal();
                    prenatal.setFk(userId);
                    prenatal.setWeight(weight);
                    prenatal.setWeek(week);
                    // INSERT
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    helper.addPrenatal(prenatal);

                    Toast toast = Toast.makeText(this, "Weight: " + weight + " Week: " + week + " saved.", Toast.LENGTH_LONG);
                    toast.show();

                    weightET.setText("");
                    weekET.setText("");

                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a weight.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_pre_button_back:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.add_pre_button_review:
                Intent intent2 = new Intent(this, ReviewPrenatalActivity.class);
                intent2.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent2.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent2);
                break;
        }
    }
}
