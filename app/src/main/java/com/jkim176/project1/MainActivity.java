package com.jkim176.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.jkim176.project1.USERNAME";
    public static final String EXTRA_USERID = "com.jkim176.project1.USERID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.main_button_choose_user:
                Intent intent = new Intent(this, ChooseUserActivity.class);
                startActivity(intent);
                break;

            case R.id.main_button_add:
                Intent intent2 = new Intent(this, AddUserActivity.class);
                startActivity(intent2);
                break;
            case R.id.debug_delete_all:
                // delete everything
                MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                helper.deleteAllTableRows();
                break;
        }
    }
}
