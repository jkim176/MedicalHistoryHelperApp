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

public class ReviewAllergyActivity extends AppCompatActivity {

    TextView userTV;
    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_allergy);
        userTV = findViewById(R.id.review_all_user);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);

        tableLayout = findViewById(R.id.table_all);

        showAllergies(userId);
    }

    public void showAllergies(long userId) {


        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<Allergy> list = helper.getAllAllergiesByUserId(userId);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_all, null);

            TextView name = row.findViewById(R.id.new_all_name);
            TextView area = row.findViewById(R.id.new_all_area);
            TextView severity = row.findViewById(R.id.new_all_severity);
            TextView start = row.findViewById(R.id.new_all_start);
            TextView end = row.findViewById(R.id.new_all_end);

            Allergy item = list.get(i);
            name.setText(item.getAllergyName());
            area.setText(item.getArea());
            severity.setText(item.getSeverity());
            start.setText(item.getStartDate());
            end.setText(item.getEndDate());

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.review_all_button_back:
                Intent intent = new Intent(this, AddAllergyActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
        }
    }
}
