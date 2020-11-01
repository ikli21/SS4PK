package com.example.ss4pk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.ss4pk.Transform.parseIntOrDefault;
import static com.example.ss4pk.UserStaticInfo.POSITION;
import static com.example.ss4pk.UserStaticInfo.users;

public class UserActivity extends AppCompatActivity {

    private User activeUser;
    private TextView StateTextView;
    private TextView NameTextView;
    private TextView AgeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra(POSITION, 0);
        activeUser = users.get(position);
        Init();
        setUserInfo();
        setContentView(R.layout.activity_user);
    }

    private void Init() {
         NameTextView = findViewById(R.id.NameTextView);
         AgeTextView = findViewById(R.id.AgeTextView);
         StateTextView = findViewById(R.id.StateTextView);
    }
    private void setUserInfo() {
        NameTextView.setText(activeUser.getName());
        AgeTextView.setText(String.valueOf(activeUser.getAge()));
        StateTextView.setText(activeUser.getState());
    }

    public void Back(View view)
    {
        onBackPressed();
    }

    public void Save(View view) {
        activeUser.setName(NameTextView.getText().toString());

        String age = AgeTextView.getText().toString();
        activeUser.setState(StateTextView.getText().toString());
        activeUser.setAge(parseIntOrDefault(age,activeUser.getAge()));
        MainActivity.UpdateListAndUserPanel(activeUser);
        finish();
    }

}
