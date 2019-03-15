package com.jkim176.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.List;

public class ChooseUserActivity extends AppCompatActivity {

    TableLayout tableLayout;
    private String userName;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        tableLayout = findViewById(R.id.table_choose_user);

        MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
        List<User> list = helper.getAllUsers();
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // populate table using user list
        for(int i = 0; i < list.size(); i++) {
            View row = li.inflate(R.layout.new_row_user, null);
            User user = list.get(i);
            Button userBTN = row.findViewById(R.id.new_user_button);
            userBTN.setText(user.getUserName());
            // saves user object to dynamicly created button
            userBTN.setTag(user);

            tableLayout.addView(row);
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.new_user_button:
                // get userId and name from Button tag
                User user = (User) view.getTag();
                userId = user.getId();
                userName = user.getUserName();

                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                intent.putExtra(MainActivity.EXTRA_USERID, userId);
                startActivity(intent);
                break;
            case R.id.choose_user_button_back:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
