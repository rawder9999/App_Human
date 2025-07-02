package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import User.Employees;
import User.Users;

public class Database_For_HumanManage  extends SQLiteOpenHelper {
    private static final String DB_NAME = "USERS";
    private static final int DB_VERSION = 10;
    private static final String TABLE_USER = "TABLE_USER";
    private static final String TABLE_USER_ID = "TABLE_USER_ID";
    private static final String TABLE_USER_NAME = "TABLE_USER_NAME";
    private static final String TABLE_USER_EMAIL = "TABLE_USER_EMAIL";
    private static final String TABLE_USER_PASSWORD = "TABLE_USER_PASSWORD";

    public static final String TABLE_EMPLOYEE = "TABLE_EMPLOYEE";
    private static final String TABLE_EMPLOYEE_ID = "TABLE_EMPLOYEE_ID";
    public static final String TABLE_EMPLOYEE_NAME = "TABLE_EMPLOYEE_NAME";
    private static final String TABLE_EMPLOYEE_PHONE = "TABLE_EMPLOYEE_PHONE";
    public static final String TABLE_EMPLOYEE_CHUCVU = "TABLE_EMPLOYEE_CHUCVU";
    private static final String TABLE_EMPLOYEE_NGAYSINH = "TABLE_EMPLOYEE_NGAYSINH";
    private static final String TABLE_EMPLOYEE_LUONG = "TABLE_EMPLOYEE_LUONG";

    public Database_For_HumanManage(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbUser = "CREATE TABLE " + TABLE_USER + " (" +
                TABLE_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_USER_NAME + " TEXT NOT NULL, " +
                TABLE_USER_EMAIL + " TEXT NOT NULL, " +
                TABLE_USER_PASSWORD + " TEXT NOT NULL)";

        String tbEmployee = "CREATE TABLE " + TABLE_EMPLOYEE + " (" +
                TABLE_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_EMPLOYEE_NAME + " TEXT NOT NULL, " +
                TABLE_EMPLOYEE_PHONE + " TEXT NOT NULL, " +
                TABLE_EMPLOYEE_NGAYSINH + " TEXT NOT NULL, " +
                TABLE_EMPLOYEE_CHUCVU + " TEXT NOT NULL, " +
                TABLE_EMPLOYEE_LUONG + " TEXT NOT NULL) ";

        sqLiteDatabase.execSQL(tbUser);
        sqLiteDatabase.execSQL(tbEmployee);

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EMPLOYEE + " VALUES (null, 'Rosian', '123456', '2005', 'GiamDoc', '3000')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase open(){
        return this.getReadableDatabase();
    }
    // Hàm Insert dữ liệu vô database
    public boolean insertUsers(Users users){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_USER_NAME, users.userName);
        contentValues.put(TABLE_USER_EMAIL, users.email);
        contentValues.put(TABLE_USER_PASSWORD, users.passWord);

        long result = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        return result != -1;
    }

    // Hàm kiểm tra Email có tồn tại
    public boolean isEmailExists(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USER, null, TABLE_USER_EMAIL + " = ?", new String[]{email}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Hàm check thông tin từ database để thực hiện đăng nhập
    public boolean checkUserToLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                TABLE_USER,
                null,
                TABLE_USER_EMAIL + " = ? AND " + TABLE_USER_PASSWORD + " = ?",
                new String[]{email, password},
                null,
                null,
                null
        );

        boolean result = (cursor != null && cursor.moveToFirst());
        if (cursor != null) cursor.close();
        return result;
    }

    // Hàm reset, update mật khẩu
    public boolean resetPassword(String email, String newPassword){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_USER_PASSWORD, newPassword);

        long result = sqLiteDatabase.update(TABLE_USER, contentValues, TABLE_USER_EMAIL + " = ?", new String[]{email});
        return result > 0;
    }

    // Hàm lấy dữ liệu truyền lên trang home
    public String getUserNameByEmail(String email) {
        String name = "";
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT " + TABLE_USER_NAME + " FROM " + TABLE_USER + " WHERE " + TABLE_USER_EMAIL + " = ?",
                new String[]{email}
        );
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(0);
            cursor.close();
        }
        return name;
    }

    // Hàm Insert thông tin nhân viên vào database
    public boolean insertEmployee(Employees employees){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_EMPLOYEE_NAME, employees.employeeName);
        contentValues.put(TABLE_EMPLOYEE_PHONE, employees.employeePhone);
        contentValues.put(TABLE_EMPLOYEE_NGAYSINH, employees.employeeDateOfBirth);
        contentValues.put(TABLE_EMPLOYEE_CHUCVU, employees.employeeChucVu);
        contentValues.put(TABLE_EMPLOYEE_LUONG, employees.employeeLuong);

        long result = sqLiteDatabase.insert(TABLE_EMPLOYEE, null, contentValues);
        return result != -1;
    }

}
