package model;

// ============================================================
//  BÀI 3: Class BankAccount
// ============================================================
public class BankAccount {

    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName     = ownerName;
        if (balance < 0) {
            System.out.println("  [!] Số dư không thể âm → tự động gán = 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    public void setOwnerName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.ownerName = name;
        } else {
            System.out.println("  [!] Tên chủ tài khoản không được rỗng.");
        }
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("  Nạp %,.0f VND thành công. Số dư: %,.0f%n", amount, balance);
        } else {
            System.out.println("  [!] Số tiền nạp phải > 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [!] Số tiền rút phải > 0.");
        } else if (amount > balance) {
            System.out.printf("  [!] Số dư không đủ. Hiện còn: %,.0f%n", balance);
        } else {
            balance -= amount;
            System.out.printf("  Rút %,.0f VND thành công. Số dư: %,.0f%n", amount, balance);
        }
    }

    public void transfer(BankAccount other, double amount) {
        if (amount <= 0) {
            System.out.println("  [!] Số tiền chuyển phải > 0."); return;
        }
        if (amount > balance) {
            System.out.println("  [!] Số dư không đủ để chuyển khoản."); return;
        }
        balance       -= amount;
        other.balance += amount;
        System.out.printf("  Chuyển %,.0f VND → %-15s | Còn lại: %,.0f%n",
                amount, other.ownerName, balance);
    }

    public void displayInfo() {
        String masked = "****" + accountNumber.substring(accountNumber.length() - 4);
        System.out.printf("  TK: %-10s | Chủ: %-15s | Số dư: %,.0f VND%n",
                masked, ownerName, balance);
    }
}