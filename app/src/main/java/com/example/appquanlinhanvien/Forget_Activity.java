package com.example.appquanlinhanvien;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.Database_For_HumanManage;

public class Forget_Activity extends AppCompatActivity {
    EditText inputForgetEmail, inputForgetPassword, inputForgetRepassword;
    Button btnForget;
    ImageView imgBack;
    Database_For_HumanManage databaseForHumanManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        databaseForHumanManage = new Database_For_HumanManage(this);

        inputForgetEmail = findViewById(R.id.inputForgetEmail);
        inputForgetPassword = findViewById(R.id.inputForgetPassword);
        inputForgetRepassword = findViewById(R.id.inputForgetRePassword);
        btnForget = findViewById(R.id.btnForget);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(v -> finish());

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputForgetEmail.getText().toString();
                String newpassword = inputForgetPassword.getText().toString();
                String newrepassword = inputForgetRepassword.getText().toString();

                if (validateInput(email, newpassword, newrepassword)) {
                    if (databaseForHumanManage.isEmailExists(email)) {
                        boolean isChangePassword = databaseForHumanManage.resetPassword(email, newpassword);
                        if (isChangePassword) {
                            Toast.makeText(Forget_Activity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Forget_Activity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Forget_Activity.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateInput(String email, String newpassword, String newrepassword) {
        if (email.isEmpty() || newpassword.isEmpty() || newrepassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!newpassword.equals(newrepassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (newpassword.length() < 6 ||
                !newpassword.matches(".*[A-Z].*") ||
                !newpassword.matches(".*[0-9].*") ||
                !newpassword.matches(".*[!@#$%^&].*")) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 kí tự, chữ hoa, số, kí tự đặc biệt", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}