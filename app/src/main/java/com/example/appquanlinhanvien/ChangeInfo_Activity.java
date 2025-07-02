package com.example.appquanlinhanvien;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Database.Database_For_HumanManage;
import User.Employees;

public class ChangeInfo_Activity extends AppCompatActivity {
    EditText changeName, changePhone, changeDate, changeChucVu, changeLuong;
    Button btnUpdate;
    Database_For_HumanManage databaseForHumanManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        databaseForHumanManage = new Database_For_HumanManage(this);

        changeName = findViewById(R.id.changeName);
        changePhone = findViewById(R.id.changePhone);
        changeDate = findViewById(R.id.changeDate);
        changeChucVu = findViewById(R.id.changeChucVu);
        changeLuong = findViewById(R.id.changeLuong);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originalName = getIntent().getStringExtra("originalName");
                String name = changeName.getText().toString();
                String phone = changePhone.getText().toString();
                String ngaysinh = changeDate.getText().toString();
                String chucvu = changeChucVu.getText().toString();
                String luong = changeLuong.getText().toString();

                if(validateInput(name, phone, ngaysinh, chucvu, luong)){
                    if(databaseForHumanManage.updateEmployee(new Employees(name, phone, ngaysinh, chucvu, luong))){
                        Toast.makeText(ChangeInfo_Activity.this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ChangeInfo_Activity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean validateInput(String name, String phone, String ngaysinh, String chucvu, String luong) {
        if (name.isEmpty() && phone.isEmpty() && ngaysinh.isEmpty() && chucvu.isEmpty() && luong.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ít nhất một trường để cập nhật.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!name.isEmpty() && !name.matches("^[\\p{L} ]+$")) {
            Toast.makeText(this, "Tên không hợp lệ. Không được chứa số hoặc ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!phone.isEmpty() && !phone.matches("^(07|09|03|08)\\d{8}$")) {
            Toast.makeText(this, "Số điện thoại không hợp lệ. Phải gồm 10 chữ số và bắt đầu bằng số 0.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!ngaysinh.isEmpty() && !ngaysinh.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            Toast.makeText(this, "Ngày sinh không hợp lệ. Định dạng phải là dd/MM/yyyy.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!chucvu.isEmpty() && !chucvu.matches("^[\\p{L} ]+$")) {
            Toast.makeText(this, "Chức vụ không hợp lệ. Không được chứa số hoặc ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!luong.isEmpty()) {
            try {
                double luongSo = Double.parseDouble(luong);
                if (luongSo <= 0) {
                    Toast.makeText(this, "Lương phải là số dương.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Lương phải là một số hợp lệ.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}