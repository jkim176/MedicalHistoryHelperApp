package com.jkim176.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    EditText userNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Intent intent = getIntent();

        userNameET = findViewById(R.id.add_user_et);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_user_button_save:
                Intent intent = new Intent(this, RecordActivity.class);
                String userName = userNameET.getText().toString();
                if(userName.length() > 0) {
                    MyDatabaseHelper helper = MyDatabaseHelper.getInstance(this);
                    intent.putExtra(MainActivity.EXTRA_USERNAME, userName);
                    User user = new User();
                    user.setUserName(userName);
                    // get userID from addOrUpdateUser(user)
                    long userId = helper.addOrUpdateUser(user);
                    intent.putExtra(MainActivity.EXTRA_USERID, userId);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(this, "Empty. Please enter a username.", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.add_user_button_back:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
