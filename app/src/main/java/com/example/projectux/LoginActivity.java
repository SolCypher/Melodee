package com.example.projectux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private TextView errorMsg;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeItems();
        logintBtnListener();
        
    }

    private void logintBtnListener() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                validate();

            }
        });

    }

    private void validate() {

        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        Intent toHomePage = new Intent(LoginActivity.this, HomeActivity.class);

        if(username.isEmpty() ){
            errorMsg.setText("Username must be filled!");
            errorMsg.setVisibility(View.VISIBLE);
        }else if(password.isEmpty() ){
            errorMsg.setText("Password must be filled!");
            errorMsg.setVisibility(View.VISIBLE);
        }else if(!password.matches("^[a-zA-Z0-9]+$")){
            errorMsg.setText("Password must be alphanumeric!");
            errorMsg.setVisibility(View.VISIBLE);
        }
        else{
            errorMsg.setVisibility(View.INVISIBLE);
            toHomePage.putExtra("USERNAME_KEY", username);
            startActivity(toHomePage);
        }

    }

    private void initializeItems() {

        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.errorMsg = findViewById(R.id.errorMsg);

    }
}