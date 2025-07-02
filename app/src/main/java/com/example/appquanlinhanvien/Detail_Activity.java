package com.example.appquanlinhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Database.Database_For_HumanManage;

public class Detail_Activity extends AppCompatActivity {
    TextView txtDetailName, txtDetailPhone, txtDetailNgaysinh, txtDetailChucVu, txtDetailLuong;
    ImageView btn_back_employeedetail;
    ImageButton imgbtn_edit, imgbtn_delete;
    Database_For_HumanManage databaseForHumanManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        databaseForHumanManage = new Database_For_HumanManage(this);

        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailPhone = findViewById(R.id.txtDetailPhone);
        txtDetailNgaysinh = findViewById(R.id.txtDetailNgaysinh);
        txtDetailChucVu = findViewById(R.id.txtDetailChucVu);
        txtDetailLuong = findViewById(R.id.txtDetailLuong);
        btn_back_employeedetail = findViewById(R.id.btn_back_employeedetail);
        imgbtn_edit = findViewById(R.id.imgbtn_edit);
        imgbtn_delete = findViewById(R.id.imgbtn_delete);

        imgbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Activity.this, ChangeInfo_Activity.class);
                startActivity(intent);
            }
        });

        imgbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameToDelete = getIntent().getStringExtra("originalName"); // hoặc ID nếu dùng ID

                boolean deleted = databaseForHumanManage.deleteEmployeeByName(nameToDelete);
                if (deleted) {
                    Toast.makeText(Detail_Activity.this, "Xóa nhân viên thành công!", Toast.LENGTH_SHORT).show();
                    finish(); // Đóng màn sau khi xóa
                } else {
                    Toast.makeText(Detail_Activity.this, "Xóa thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back_employeedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getData();
    }
    private void getData(){
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String ngaysinh = getIntent().getStringExtra("ngaysinh");
        String chucvu = getIntent().getStringExtra("chucvu");
        String luong = getIntent().getStringExtra("luong");


        txtDetailName.setText("Họ tên: " + name);
        txtDetailPhone.setText("Phone: " + phone);
        txtDetailNgaysinh.setText("Ngày Sinh: " + ngaysinh);
        txtDetailChucVu.setText("Chức vụ: " + chucvu);
        txtDetailLuong.setText("Lương: " + luong);
    }
}