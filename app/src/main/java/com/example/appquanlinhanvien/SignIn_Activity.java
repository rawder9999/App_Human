package com.example.appquanlinhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.Database_For_HumanManage;

public class SignIn_Activity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    TextView txtNonAccount, txtForgot;
    Button btnDN;
    Database_For_HumanManage databaseForHumanManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        databaseForHumanManage = new Database_For_HumanManage(this);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        txtNonAccount = findViewById(R.id.txtNonAccount);
        txtForgot = findViewById(R.id.txtFogot);
        btnDN = findViewById(R.id.btnDN);

        txtNonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignIn_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn_Activity.this, Forget_Activity.class);
                startActivity(intent);
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(validateInput(email, password)){
                    if(databaseForHumanManage.checkUserToLogin(email, password)){
                        String name = databaseForHumanManage.getUserNameByEmail(email);

                        Toast.makeText(SignIn_Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                        intent.putExtra("username", name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn_Activity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateInput(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(SignIn_Activity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
}