package model;

// ============================================================
//  BÀI 1: Class Book
// ============================================================
public class Book {

    String title;
    String author;
    int    year;
    double price;

    public Book() {
        this.title  = "Unknown";
        this.author = "Unknown";
        this.year   = 2000;
        this.price  = 0;
    }

    public Book(String title, String author, int year, double price) {
        this.title  = title;
        this.author = author;
        this.year   = year;
        this.price  = price;
    }

    public Book(String title, String author) {
        this.title  = title;
        this.author = author;
        this.year   = 2026;
        this.price  = 100_000;
    }

    public void displayInfo() {
        System.out.println("  Sách: " + title
                + " - Tác giả: " + author
                + " - Năm: "     + year
                + " - Giá: "     + price);
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("  [!] Phần trăm không hợp lệ (0 – 100).");
            return;
        }
        price = price * (1 - percent / 100.0);
        System.out.printf("  Giảm %.0f%% → Giá mới: %.0f%n", percent, price);
    }
}
