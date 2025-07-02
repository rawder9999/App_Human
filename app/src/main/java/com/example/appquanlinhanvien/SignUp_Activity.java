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
import User.Users;

public class SignUp_Activity extends AppCompatActivity {
    Database_For_HumanManage databaseForHumanManage;
    EditText edtName, edtEmail, edtPassword, edtRepassword;
    Button btnDK;
    TextView txtSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseForHumanManage = new Database_For_HumanManage(this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRepassword = findViewById(R.id.edtRepassword);
        btnDK = findViewById(R.id.btnDK);
        txtSignin = findViewById(R.id.txtSignin);

        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String repassword = edtRepassword.getText().toString().trim();

                if (validateInput(name, email, password, repassword)){
                    if(databaseForHumanManage.isEmailExists(email)){
                        Toast.makeText(SignUp_Activity.this, "Email đã tồn tại ", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean inserted = databaseForHumanManage.insertUsers(new Users(name, password, email));
                        if(inserted){
                            Toast.makeText(SignUp_Activity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignUp_Activity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    private boolean validateInput(String userName, String email, String password, String repassword) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!password.equals(repassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[!@#$%^&].*")) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 kí tự, chữ hoa, số, kí tự đặc biệt", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



}