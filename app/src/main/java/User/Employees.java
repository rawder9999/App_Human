package User;

public class Employees {
    public String employeeName;
    public String employeePhone;
    public String employeeDateOfBirth;
    public String employeeChucVu;
    public String employeeLuong;

    public Employees(String employeeName, String employeeChucVu) {
        this.employeeName = employeeName;
        this.employeeChucVu = employeeChucVu;
    }

    public Employees(String employeeName, String employeePhone, String employeeDateOfBirth, String employeeChucVu, String employeeLuong) {
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeChucVu = employeeChucVu;
        this.employeeLuong = employeeLuong;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public String getEmployeeChucVu() {
        return employeeChucVu;
    }

    public void setEmployeeChucVu(String employeeChucVu) {
        this.employeeChucVu = employeeChucVu;
    }

    public String getEmployeeLuong() {
        return employeeLuong;
    }

    public void setEmployeeLuong(String employeeLuong) {
        this.employeeLuong = employeeLuong;
    }
}
