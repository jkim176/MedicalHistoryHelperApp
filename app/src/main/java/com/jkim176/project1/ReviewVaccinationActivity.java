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

public class ReviewVaccinationActivity extends AppCompatActivity {

    TextView userTV;
    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_vaccination);
        userTV = findViewById(R.id.review_vac_user);

        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
        userTV.setText("User: " + userName);

        tableLayout = findViewById(R.id.table_vac);

        showVaccinations(userId);
    }

    public void showVaccinations(long userId) {
        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<Vaccination> list = helper.getAllVaccinationsByUserId(userId);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_vac, null);

            TextView name = row.findViewById(R.id.new_vac_name);
            TextView boosterNumber = row.findViewById(R.id.new_vac_booster);
            TextView date = row.findViewById(R.id.new_vac_date);

            Vaccination item = list.get(i);
            name.setText(item.getVaccinationName());
            boosterNumber.setText(item.getBoosterNumber());
            date.setText(item.getDate());

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.review_vac_button_back:
                Intent intent = new Intent(this, AddVaccinationActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
        }
    }
}
