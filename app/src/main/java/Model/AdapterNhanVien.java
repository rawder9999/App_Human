package Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appquanlinhanvien.R;

import java.util.ArrayList;

import User.Employees;

public class AdapterNhanVien extends BaseAdapter {
    Context context;
    ArrayList<Employees> list;

    public AdapterNhanVien(Context context, ArrayList<Employees> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);
        TextView txtName = row.findViewById(R.id.txtName);
        TextView txtChucvu = row.findViewById(R.id.txtChucVu);

        Employees nhanVien = list.get(i);
        txtName.setText(nhanVien.employeeName);
        txtChucvu.setText(nhanVien.employeeChucVu);

        return row;
    }
}
