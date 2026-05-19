package model;

import util.ProductValidator;

// ============================================================
//  BÀI 5: Class Product
// ============================================================
public class Product {

    private String   productCode;
    private String   name;
    private double   price;
    private int      quantity;
    private Category category;      // phần mở rộng
    private boolean  discontinued;  // phần mở rộng

    private static int    counter       = 1;
    private static int    totalProducts = 0;
    private static double totalRevenue  = 0;


    public Product() {
        this("Unknown", 0, 0);
    }

    public Product(String name, double price) {
        this(name, price, 0);
    }

    public Product(String name, double price, int quantity) {
        this.productCode  = String.format("P-%04d", counter++);
        this.name         = ProductValidator.isValidName(name)         ? name     : "Unknown";
        this.price        = ProductValidator.isValidPrice(price)       ? price    : 0;
        this.quantity     = ProductValidator.isValidQuantity(quantity) ? quantity : 0;
        this.discontinued = false;
        totalProducts++;
    }

    public String   getProductCode() { return productCode; }
    public String   getName()        { return name; }
    public double   getPrice()       { return price; }
    public int      getQuantity()    { return quantity; }
    public Category getCategory()    { return category; }
    public boolean  isDiscontinued() { return discontinued; }

    public void setName(String name) {
        if (ProductValidator.isValidName(name)) this.name = name;
        else System.out.println("  [!] Tên không hợp lệ (min 2 ký tự, không rỗng).");
    }

    public void setPrice(double price) {
        if (ProductValidator.isValidPrice(price)) this.price = price;
        else System.out.println("  [!] Giá phải >= 0.");
    }

    public void setQuantity(int quantity) {
        if (ProductValidator.isValidQuantity(quantity)) this.quantity = quantity;
        else System.out.println("  [!] Số lượng phải >= 0.");
    }

    public void setCategory(Category category) { this.category = category; }

    public void sell(int amount) {
        if (discontinued) {
            System.out.println("  [!] '" + name + "' đã ngừng kinh doanh."); return;
        }
        if (amount <= 0) {
            System.out.println("  [!] Số lượng bán phải > 0."); return;
        }
        if (amount > quantity) {
            System.out.println("  [!] Không đủ hàng. Tồn kho hiện tại: " + quantity); return;
        }
        quantity     -= amount;
        totalRevenue += (double) amount * price;
        System.out.printf("  Bán %d x %-20s → Doanh thu: %,.0f VND%n", amount, name, (double) amount * price);
    }

    public void restock(int amount) {
        if (amount <= 0) {
            System.out.println("  [!] Số lượng nhập phải > 0."); return;
        }
        quantity += amount;
        System.out.printf("  Nhập thêm %d x %-20s → Tồn kho: %d%n", amount, name, quantity);
    }

    public void applyPromotion(double discountPercent) {
        if (discountPercent <= 0 || discountPercent >= 100) {
            System.out.println("  [!] Phần trăm giảm giá không hợp lệ."); return;
        }
        double oldPrice = price;
        price = price * (1 - discountPercent / 100.0);
        System.out.printf("  Giảm %.0f%% %-20s: %,.0f → %,.0f%n", discountPercent, name, oldPrice, price);
    }

    public void discontinue() {
        if (discontinued) {
            System.out.println("  [!] '" + name + "' đã được ngừng trước đó."); return;
        }
        discontinued = true;
        System.out.println("  '" + name + "' (" + productCode + ") → NGỪNG KINH DOANH.");
    }

    public void displayInfo() {
        String cat    = (category != null) ? " | " + category.getCategoryName() : "";
        String status = discontinued ? " ⚠ NGỪNG" : "";
        System.out.printf("  %-8s | %-22s | Giá: %,10.0f | Tồn: %3d%s%s%n",
                productCode, name, price, quantity, cat, status);
    }

    // ── Static methods ────────────────────────────────────────
    public static int    getTotalProducts() { return totalProducts; }
    public static double getTotalRevenue()  { return totalRevenue; }

    public static String getStoreReport() {
        return String.format(
                "  Tổng sản phẩm đã tạo : %d%n" +
                        "  Tổng doanh thu        : %,.0f VND",
                totalProducts, totalRevenue);
    }

    public static void applyGlobalPromotion(Product[] products, double discountPercent) {
        System.out.printf("  >> Khuyến mãi toàn cửa hàng: -%.0f%%%n", discountPercent);
        for (Product p : products) {
            if (!p.isDiscontinued()) p.applyPromotion(discountPercent);
        }
    }
}