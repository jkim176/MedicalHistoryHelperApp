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

public class ReviewSymptomActivity extends AppCompatActivity {

    TextView userTV;
    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_symptom);
        userTV = findViewById(R.id.review_sym_user);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);

        tableLayout = findViewById(R.id.table_sym);

        showSymptoms(userId);
    }

    public void showSymptoms(long userId) {
        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<Symptom> list = helper.getAllSymptomsByUserId(userId);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_sym, null);

            TextView name = row.findViewById(R.id.new_sym_name);
            TextView area = row.findViewById(R.id.new_sym_area);
            TextView duration = row.findViewById(R.id.new_sym_duration);
            TextView start = row.findViewById(R.id.new_sym_start);
            TextView end = row.findViewById(R.id.new_sym_end);

            Symptom item = list.get(i);
            name.setText(item.getSymptomName());
            area.setText(item.getArea());
            duration.setText(item.getDuration());
            start.setText(item.getStartDate());
            end.setText(item.getEndDate());

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.review_sym_button_back:
                Intent intent = new Intent(this, AddSymptomActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
        }
    }
}
