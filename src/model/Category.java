package model;

// ============================================================
//  BÀI 5 (phần mở rộng): Class Category
// ============================================================
public class Category {

    private String categoryCode;
    private String categoryName;
    private static int counter = 1;

    public Category(String categoryName) {
        this.categoryCode = String.format("CAT-%03d", counter++);
        this.categoryName = categoryName;
    }

    public String getCategoryCode() { return categoryCode; }
    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.categoryName = name;
        } else {
            System.out.println("  [!] Tên danh mục không được rỗng.");
        }
    }

    @Override
    public String toString() { return categoryCode + " | " + categoryName; }
}
