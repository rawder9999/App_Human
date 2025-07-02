package com.example.appquanlinhanvien;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.Database_For_HumanManage;
import User.Employees;

public class AddEmployee_Activity extends AppCompatActivity {
    EditText addName, addPhone, addDateOfBirth, addChucVu, addSalary;
    Button btnThem, btnQuayLai;
    Database_For_HumanManage databaseForHumanManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        databaseForHumanManage = new Database_For_HumanManage(this);

        addName = findViewById(R.id.addName);
        addPhone = findViewById(R.id.addPhone);
        addDateOfBirth = findViewById(R.id.addDateOfBirth);
        addChucVu = findViewById(R.id.addChucVu);
        addSalary = findViewById(R.id.addSalary);
        btnThem = findViewById(R.id.btnThem);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        btnQuayLai.setOnClickListener(v -> finish());

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertInfo();
            }
        });
    }
    public boolean insertInfo(){
        String name = addName.getText().toString();
        String phone = addPhone.getText().toString();
        String ngaysinh = addDateOfBirth.getText().toString();
        String chucvu = addChucVu.getText().toString();
        String luong = addSalary.getText().toString();

        if(validateInput(name, phone, ngaysinh, chucvu, luong)){
            if(databaseForHumanManage.insertEmployee(new Employees(name, phone, ngaysinh, chucvu, luong))){
                Toast.makeText(AddEmployee_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        }

        return  true;
    }

    private boolean validateInput(String name, String phone, String ngaysinh, String chucvu, String luong) {
        if (name.isEmpty() || phone.isEmpty() || ngaysinh.isEmpty() || chucvu.isEmpty() || luong.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!name.matches("^[\\p{L} ]+$")) {
            Toast.makeText(this, "Tên không hợp lệ. Không được chứa số hoặc ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!phone.matches("^(07|09|03|08)\\d{8}$")) {
            Toast.makeText(this, "Số điện thoại không hợp lệ. Phải gồm 10 chữ số và bắt đầu bằng số 0.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!ngaysinh.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            Toast.makeText(this, "Ngày sinh không hợp lệ. Định dạng phải là dd/MM/yyyy.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!chucvu.matches("^[\\p{L} ]+$")) {
            Toast.makeText(this, "Chức vụ không hợp lệ. Không được chứa số hoặc ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
            return false;
        }

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

        return true;
    }
}