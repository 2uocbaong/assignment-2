package model;

// ============================================================
//  BÀI 2: Class Rectangle
// ============================================================
public class Rectangle {

    double width;
    double height;

    public Rectangle() {
        this.width  = 1;
        this.height = 1;
    }

    public Rectangle(double side) {
        this.width  = side;
        this.height = side;
    }

    public Rectangle(double width, double height) {
        this.width  = width;
        this.height = height;
    }


    public Rectangle(Rectangle other) {
        this.width  = other.width;
        this.height = other.height;
    }

    public double  getArea()      { return width * height; }
    public double  getPerimeter() { return 2 * (width + height); }
    public boolean isSquare()     { return width == height; }


    public void scale(double factor) {
        if (factor <= 0) {
            System.out.println("  [!] Hệ số scale phải > 0.");
            return;
        }
        width  *= factor;
        height *= factor;
        System.out.printf("  Sau scale(%.1f): %.2f x %.2f%n", factor, width, height);
    }

    public void displayInfo() {
        System.out.printf("  %.2f x %.2f | Diện tích: %.2f | Chu vi: %.2f | Hình vuông: %b%n",
                width, height, getArea(), getPerimeter(), isSquare());
    }
}
