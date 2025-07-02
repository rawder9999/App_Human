package com.example.appquanlinhanvien;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.Database_For_HumanManage;
import Adapter.AdapterNhanVien;
import User.Employees;

public class Home_Activity extends AppCompatActivity {
    Database_For_HumanManage databaseForHumanManage;
    ListView lv;
    ArrayList<Employees> list;
    AdapterNhanVien adapterNhanVien;
    FloatingActionButton btnAdd;
    TextView txtAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseForHumanManage = new Database_For_HumanManage(this);

        txtAccount = findViewById(R.id.txtAccount);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            txtAccount.setText("Xin chào, " + username);
        }

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, AddEmployee_Activity.class);
                startActivity(intent);
            }
        });

        addControls();
        readData();
    }

    private void addControls(){
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        adapterNhanVien = new AdapterNhanVien(this, list);
        lv.setAdapter(adapterNhanVien);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Employees selected = list.get(position);

            Intent intent = new Intent(Home_Activity.this, Detail_Activity.class);
            intent.putExtra("originalName", selected.employeeName);
            intent.putExtra("name", selected.employeeName);
            intent.putExtra("phone", selected.employeePhone);
            intent.putExtra("ngaysinh", selected.employeeDateOfBirth);
            intent.putExtra("chucvu", selected.employeeChucVu);
            intent.putExtra("luong", selected.employeeLuong);
            startActivity(intent);
        });

    }


    private void readData() {
        Cursor cursor = databaseForHumanManage.getReadableDatabase().rawQuery(
                "SELECT " +
                        Database_For_HumanManage.TABLE_EMPLOYEE_NAME + ", " +
                        Database_For_HumanManage.TABLE_EMPLOYEE_PHONE + ", " +
                        Database_For_HumanManage.TABLE_EMPLOYEE_NGAYSINH + ", " +
                        Database_For_HumanManage.TABLE_EMPLOYEE_CHUCVU + ", " +
                        Database_For_HumanManage.TABLE_EMPLOYEE_LUONG +
                        " FROM " + Database_For_HumanManage.TABLE_EMPLOYEE,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                String ngaysinh = cursor.getString(2);
                String chucvu = cursor.getString(3);
                String luong = cursor.getString(4);

                list.add(new Employees(name, phone, ngaysinh, chucvu, luong));
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapterNhanVien.notifyDataSetChanged();

    }
}