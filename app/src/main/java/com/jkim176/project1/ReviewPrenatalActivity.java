package com.jkim176.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class ReviewPrenatalActivity extends AppCompatActivity {

    TextView userTV;
    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_prenatal);
        userTV = findViewById(R.id.review_pre_user);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);

        tableLayout = findViewById(R.id.table_pre);

        showPrenatals(userId);
    }

    public void showPrenatals(long userId) {
        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<Prenatal> list = helper.getAllPrenatalsByUserId(userId);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_pre, null);

            TextView weight = row.findViewById(R.id.new_pre_weight);
            TextView week = row.findViewById(R.id.new_pre_week);

            Prenatal item = list.get(i);
            weight.setText(item.getWeight());
            week.setText(item.getWeek());

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.review_pre_button_back:
                Intent intent = new Intent(this, AddPrenatalActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
        }
    }
}
