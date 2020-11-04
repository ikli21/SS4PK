package com.example.ss4pk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.ss4pk.Transform.APP_PREFERENCES;
import static com.example.ss4pk.Transform.SaveUser;
import static com.example.ss4pk.Transform.StringNoNull;
import static com.example.ss4pk.Transform.Vibrate;
import static com.example.ss4pk.Transform.md5Custom;
import static com.example.ss4pk.UserStaticInfo.AGE;
import static com.example.ss4pk.UserStaticInfo.LOGIN;
import static com.example.ss4pk.UserStaticInfo.NAME;
import static com.example.ss4pk.UserStaticInfo.PASSWORD;
import static com.example.ss4pk.UserStaticInfo.PROFILE_ID;
import static com.example.ss4pk.UserStaticInfo.STATE;
import static com.example.ss4pk.UserStaticInfo.USERS_PROFILE_INFO;
import static com.example.ss4pk.UserStaticInfo.USERS_SIGN_IN_INFO;

public class SignActivity extends AppCompatActivity {
    private EditText LoginTextView,PasswordTextView;
    private EditText NewLoginTextView,NewPasswordTextView,NewAgeTextView,NewNameTextView,NewStateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();
        CheckSignInfo();
    }

    private void CheckSignInfo() {
        SharedPreferences sp = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String login = sp.getString(LOGIN,"");
        String password = sp.getString(PASSWORD,"");
        LoginTextView.setText(login);
        PasswordTextView.setText(password);
        SignIn();
    }

    private void Init() {
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tabSignIn);
        tabSpec.setIndicator("Вход");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabSignUp);
        tabSpec.setIndicator("Регистрация");
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);
        NewLoginTextView = findViewById(R.id.NewLoginTextView);
        NewPasswordTextView = findViewById(R.id.NewPasswordTextView);
        NewNameTextView = findViewById(R.id.NewNameTextView);
        NewAgeTextView = findViewById(R.id.NewAgeTextView);
        NewStateTextView = findViewById(R.id.NewStateTextView);
        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
    }

    public void SignIn(View view){
        SignIn();
    }

    public void SignIn(){
        if(EditTextNoNullAnimation(PasswordTextView)&&EditTextNoNullAnimation(LoginTextView)) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String login = getLogin();
                    Object value = dataSnapshot.child(login).child(PASSWORD).getValue();
                    if (value != null) {
                        if (value.toString().equals(getPassword())) {
                            goNext(dataSnapshot.child(PROFILE_ID).toString(), login, getPassword());
                        } else CantSignIn();
                    } else CantSignIn();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(SignActivity.this,getResources().getText(R.string.NullParametersMessage),
                    Toast.LENGTH_SHORT).show();
            Vibrate(SignActivity.this);
        }
    }

    private String getLogin() {
        return LoginTextView.getText().toString();
    }

    private void CantSignIn(){
        Toast.makeText(SignActivity.this, R.string.CantSignInMessage,
                Toast.LENGTH_SHORT).show();
    }
    private String getPassword(){
        return PasswordTextView.getText().toString() ;
    }
    private void goNext(String profileId, String login, String password){
        UserStaticInfo.profileId = profileId;
        SaveUser(getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE),
                login,password);
        Intent intent = new Intent(this, LoadedUserDataActivity.class);
        startActivity(intent);
        finish();
    }
    private String getNewPassword(){
        return NewPasswordTextView.getText().toString();
    }
    private String getNewLogin(){
        return NewLoginTextView.getText().toString();
    }
    private int getNewAge(){
        try{
            return Integer.parseInt(NewAgeTextView.getText().toString());
        }
        catch(Exception NumberFormatException)
        {
            return 0;
        }
    }
    private String getNewName(){
        return NewNameTextView.getText().toString();
    }
    private String getNewState(){
        return NewStateTextView.getText().toString();
    }
    public void SignUp(View view){
        if(EditTextNoNullAnimation(NewLoginTextView)&&EditTextNoNullAnimation(NewPasswordTextView)&&EditTextNoNullAnimation(NewStateTextView)&&EditTextNoNullAnimation(NewNameTextView))
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO).child(getNewLogin());
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.child(PASSWORD).exists())
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String id = database.getReference(USERS_PROFILE_INFO).push().getKey();
                        String login = getNewLogin();
                        database.getReference(USERS_SIGN_IN_INFO).child(login).child(PASSWORD).setValue(md5Custom(getNewPassword()));
                        database.getReference(USERS_SIGN_IN_INFO).child(login).child(PROFILE_ID).setValue(id);
                        database.getReference(USERS_SIGN_IN_INFO).child(id).child(AGE).setValue(getNewAge());
                        database.getReference(USERS_SIGN_IN_INFO).child(id).child(STATE).setValue(getNewState());
                        database.getReference(USERS_SIGN_IN_INFO).child(id).child(NAME).setValue(getNewName());
                        goNext(id, login, getNewPassword());
                    }
                    else
                        Toast.makeText(SignActivity.this, getResources().getText(R.string.UserExistMessage),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        else
        {
            Vibrate(SignActivity.this);
            Toast.makeText(SignActivity.this, getResources().getText(R.string.NullParametersMessage),Toast.LENGTH_SHORT).show();

        }
    }
    private boolean EditTextNoNullAnimation(EditText animationTextView){
        return EditTextNoNullAnimation(animationTextView,animationTextView.getText().toString());

    }
    private boolean EditTextNoNullAnimation(EditText animationTextView, String value){
        boolean NoNullText = StringNoNull(value);
        Animation animation2 = AnimationUtils.loadAnimation(SignActivity.this,
                R.anim.error_edit);
        if(!NoNullText)
            animationTextView.startAnimation(animation2);
        return NoNullText;
    }
}
