import model.Book;
import model.Rectangle;
import model.BankAccount;
import model.Employee;
import model.Category;
import model.Product;

public class Main {

    static void header(String title) {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.printf ("  %-44s%n", title);
        System.out.println("══════════════════════════════════════════════");
    }
    static void section(String s) {
        System.out.println("\n  ▶ " + s);
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        //  BÀI 1 — CLASS BOOK
        // ══════════════════════════════════════════════════════
        header("BÀI 1 — CLASS BOOK");

        section("Constructor mặc định");
        Book book1 = new Book();
        book1.displayInfo();

        section("Constructor 4 tham số");
        Book book2 = new Book("Lập Trình Java Cơ Bản", "Nguyễn Văn A", 2023, 250_000);
        book2.displayInfo();

        section("Constructor mở rộng (title + author) — năm=2026, giá=100000");
        Book book3 = new Book("Clean Code", "Robert C. Martin");
        book3.displayInfo();

        section("applyDiscount(10%) cho book2");
        book2.applyDiscount(10);
        book2.displayInfo();

        section("applyDiscount(-5) → không hợp lệ");
        book2.applyDiscount(-5);


        // ══════════════════════════════════════════════════════
        //  BÀI 2 — CLASS RECTANGLE
        // ══════════════════════════════════════════════════════
        header("BÀI 2 — CLASS RECTANGLE");

        section("Không tham số → hình vuông 1x1");
        Rectangle r1 = new Rectangle();
        r1.displayInfo();

        section("1 tham số side=5 → hình vuông 5x5");
        Rectangle r2 = new Rectangle(5);
        r2.displayInfo();

        section("2 tham số 4x7 → hình chữ nhật");
        Rectangle r3 = new Rectangle(4, 7);
        r3.displayInfo();

        section("Copy constructor từ r3 → r4 độc lập");
        Rectangle r4 = new Rectangle(r3);
        r4.displayInfo();

        section("scale(2) trên r3 — r4 không bị ảnh hưởng");
        r3.scale(2);
        System.out.print("  r3 sau scale: "); r3.displayInfo();
        System.out.print("  r4 (bản sao): "); r4.displayInfo();

        section("scale(-1) → không hợp lệ");
        r1.scale(-1);


        // ══════════════════════════════════════════════════════
        //  BÀI 3 — CLASS BANKACCOUNT
        // ══════════════════════════════════════════════════════
        header("BÀI 3 — CLASS BANKACCOUNT");

        section("Tạo tài khoản với số dư âm → cảnh báo, gán = 0");
        BankAccount acc1 = new BankAccount("123456781234", "Nguyen Van A", -500);
        acc1.displayInfo();

        section("Tạo tài khoản hợp lệ");
        BankAccount acc2 = new BankAccount("987654329876", "Tran Thi B", 5_000_000);
        acc2.displayInfo();

        section("Nạp và rút tiền hợp lệ");
        acc1.deposit(1_000_000);
        acc1.withdraw(300_000);
        acc1.displayInfo();

        section("Các trường hợp lỗi");
        acc1.withdraw(5_000_000);   // rút quá số dư
        acc1.deposit(-100_000);     // số tiền âm

        section("Đổi tên chủ tài khoản");
        acc1.setOwnerName("Nguyen Van An");
        acc1.setOwnerName("   ");   // rỗng → từ chối
        acc1.displayInfo();

        section("Chuyển khoản acc2 → acc1: 2,000,000 VND");
        acc2.transfer(acc1, 2_000_000);
        System.out.print("  acc1: "); acc1.displayInfo();
        System.out.print("  acc2: "); acc2.displayInfo();

        section("Chuyển khoản vượt số dư");
        acc2.transfer(acc1, 99_999_999);


        // ══════════════════════════════════════════════════════
        //  BÀI 4 — CLASS EMPLOYEE
        // ══════════════════════════════════════════════════════
        header("BÀI 4 — CLASS EMPLOYEE");

        section("Gọi getAverageSalary() khi chưa có nhân viên");
        Employee.getAverageSalary();   // sẽ in cảnh báo, trả về 0

        section("Tạo 3 nhân viên (ID phải là 1000, 1001, 1002)");
        Employee e1 = new Employee("Nguyen Van A", 15_000_000);
        Employee e2 = new Employee("Tran Thi B",  20_000_000);
        Employee e3 = new Employee("Le Van C",     18_000_000);
        e1.displayInfo(); e2.displayInfo(); e3.displayInfo();
        System.out.printf("  ID: %d / %d / %d%n", e1.getId(), e2.getId(), e3.getId());

        section("Thống kê");
        System.out.printf("  Số nhân viên  : %d%n",     Employee.getEmployeeCount());
        System.out.printf("  Tổng lương    : %,.0f%n",  Employee.getTotalSalary());
        System.out.printf("  Lương TB      : %,.0f%n",  Employee.getAverageSalary());

        section("Đổi lương e2 → 25,000,000 và kiểm tra totalSalary");
        e2.setSalary(25_000_000);
        System.out.printf("  Tổng lương mới: %,.0f (phải = 15M + 25M + 18M = 58M)%n",
                Employee.getTotalSalary());

        section("Tăng lương e1 thêm 10%");
        e1.raiseSalary(10);
        System.out.printf("  Tổng lương mới: %,.0f%n", Employee.getTotalSalary());

        section("Đổi tên công ty → VietTech JSC");
        Employee.changeCompanyName("VietTech JSC");
        e1.displayInfo(); e2.displayInfo(); e3.displayInfo();


        // ══════════════════════════════════════════════════════
        //  BÀI 5 — CLASS PRODUCT + PACKAGE
        // ══════════════════════════════════════════════════════
        header("BÀI 5 — CLASS PRODUCT + PACKAGE");

        section("Tạo danh mục (phần mở rộng: Category)");
        Category electronics = new Category("Điện tử");
        Category books       = new Category("Sách");
        Category food        = new Category("Thực phẩm");
        System.out.println("  " + electronics);
        System.out.println("  " + books);
        System.out.println("  " + food);

        section("Tạo 4 sản phẩm bằng 3 loại constructor khác nhau");
        Product p1 = new Product();                                 // không tham số
        Product p2 = new Product("iPhone 16 Pro", 29_990_000);     // 2 tham số
        Product p3 = new Product("Lập Trình Java", 250_000, 50);   // 3 tham số
        Product p4 = new Product("Laptop Gaming",  35_000_000, 10);// 3 tham số

        p1.setCategory(food);
        p2.setCategory(electronics);
        p3.setCategory(books);
        p4.setCategory(electronics);

        p1.displayInfo(); p2.displayInfo(); p3.displayInfo(); p4.displayInfo();

        section("Mã sản phẩm tự động (P-0001, P-0002, P-0003, P-0004)");
        System.out.println("  " + p1.getProductCode() + " / "
                + p2.getProductCode() + " / "
                + p3.getProductCode() + " / "
                + p4.getProductCode());

        section("Nhập hàng");
        p1.restock(100);
        p2.restock(20);

        section("Bán hàng hợp lệ");
        p2.sell(5);
        p3.sell(10);
        p4.sell(2);

        section("Bán hàng các trường hợp lỗi");
        p3.sell(200);   // quá tồn kho
        p4.sell(-1);    // số lượng âm

        section("applyPromotion(15%) cho p4");
        p4.applyPromotion(15);

        section("discontinue() p1 → thử bán lại");
        p1.discontinue();
        p1.sell(5);

        section("applyGlobalPromotion(10%) — bỏ qua sản phẩm đã ngừng");
        Product[] allProducts = { p1, p2, p3, p4 };
        Product.applyGlobalPromotion(allProducts, 10);

        section("Danh sách sản phẩm cuối cùng");
        for (Product p : allProducts) p.displayInfo();

        section("Thử setter không hợp lệ (ProductValidator)");
        p2.setName("X");        // tên quá ngắn
        p2.setPrice(-1000);     // giá âm
        p2.setQuantity(-5);     // số lượng âm

        section("Báo cáo cửa hàng");
        System.out.println("  ───────────────────────────────────");
        System.out.println(Product.getStoreReport());
        System.out.println("  ───────────────────────────────────");
    }
}
