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

public class ReviewMedicationActivity extends AppCompatActivity {

    TextView userTV;
    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_medication);
        userTV = findViewById(R.id.review_med_user);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);

        tableLayout = findViewById(R.id.table_med);

        showMedications(userId);
    }

    public void showMedications(long userId) {
        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<Medication> list = helper.getAllMedicationsByUserId(userId);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_med, null);

            TextView name = row.findViewById(R.id.new_med_name);
            TextView dose = row.findViewById(R.id.new_med_dose);
            TextView freq = row.findViewById(R.id.new_med_freq);
            TextView start = row.findViewById(R.id.new_med_start);
            TextView end = row.findViewById(R.id.new_med_end);

            Medication item = list.get(i);
            name.setText(item.getMedicationName());
            dose.setText(item.getDose());
            freq.setText(item.getFrequency());
            start.setText(item.getStartDate());
            end.setText(item.getEndDate());

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.review_med_button_back:
                Intent intent = new Intent(this, AddMedicationActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
        }
    }
}
