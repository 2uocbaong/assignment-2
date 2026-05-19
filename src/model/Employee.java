package model;

// ============================================================
//  BÀI 4: Class Employee
// ============================================================
public class Employee {

    private int    id;
    private String name;
    private double salary;

    private static int    employeeCount = 0;
    private static int    nextId        = 1000;
    public  static String companyName   = "TechCorp";
    private static double totalSalary   = 0;

    public Employee(String name, double salary) {
        this.id     = nextId++;       // gán id rồi tăng nextId
        this.name   = name;
        this.salary = salary;
        employeeCount++;
        totalSalary += salary;
    }

    public int    getId()     { return id; }
    public String getName()   { return name; }
    public double getSalary() { return salary; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("  [!] Tên nhân viên không được rỗng.");
        }
    }


    public void setSalary(double newSalary) {
        if (newSalary < 0) {
            System.out.println("  [!] Lương không được âm."); return;
        }
        totalSalary -= this.salary;
        this.salary  = newSalary;
        totalSalary += newSalary;
    }

    public static int    getEmployeeCount() { return employeeCount; }
    public static double getTotalSalary()   { return totalSalary; }

    public static double getAverageSalary() {
        if (employeeCount == 0) {
            System.out.println("  [!] Chưa có nhân viên — không thể tính trung bình."); return 0;
        }
        return totalSalary / employeeCount;
    }

    public static void changeCompanyName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            companyName = newName;
            System.out.println("  Tên công ty mới: " + companyName);
        } else {
            System.out.println("  [!] Tên công ty không được rỗng.");
        }
    }


    public void raiseSalary(double percent) {
        if (percent <= 0) {
            System.out.println("  [!] Phần trăm tăng phải > 0."); return;
        }
        double increase = salary * percent / 100.0;
        totalSalary += increase;
        salary      += increase;
        System.out.printf("  Tăng lương %.0f%% → Lương mới: %,.0f%n", percent, salary);
    }

    public void displayInfo() {
        System.out.printf("  [%s] ID: %d | Tên: %-15s | Lương: %,.0f%n",
                companyName, id, name, salary);
    }
}